package com.f4.user.service.impl;

import com.f4.user.domain.User;
import com.f4.user.repository.UserRepository;
import com.f4.user.service.UserService;
import com.f4.user.service.dto.UserDTO;
import com.f4.user.service.dto.RedisUserDTO;
import com.f4.user.service.mapper.UserMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.redisson.api.RedissonClient;
import org.redisson.api.RBucket;
import org.redisson.api.RBucketAsync;
import org.redisson.api.RFuture;
import org.redisson.api.RBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service Implementation for managing {@link com.f4.user.domain.User}.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
            RedissonClient redissonClient, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.redissonClient = redissonClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        LOG.debug("Request to save User : {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        LOG.debug("Request to update User : {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public Optional<UserDTO> partialUpdate(UserDTO userDTO) {
        LOG.debug("Request to partially update User : {}", userDTO);

        return userRepository
                .findById(userDTO.getId())
                .map(existingUser -> {
                    userMapper.partialUpdate(existingUser, userDTO);

                    return existingUser;
                })
                .map(userRepository::save)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        LOG.debug("Request to get all Users");
        return userRepository.findAll().stream().map(userMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findOne(UUID id) {
        LOG.debug("Request to get User : {}", id);
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        LOG.debug("Request to delete User : {}", id);
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long syncAllUsersToRedis() {
        LOG.debug("Request to sync all users to Redis");

        long totalCount = userRepository.count();
        LOG.info("Starting sync of {} users to Redis", totalCount);

        int pageSize = 1000; // Process 1000 users at a time
        int batchSize = 100; // Redis batch operations
        long syncedCount = 0;
        int pageNumber = 0;

        while (true) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<User> userPage = userRepository.findAll(pageable);

            if (userPage.isEmpty()) {
                break;
            }

            List<User> users = userPage.getContent();

            // Process users in smaller batches for Redis
            for (int i = 0; i < users.size(); i += batchSize) {
                int endIndex = Math.min(i + batchSize, users.size());
                List<User> batchUsers = users.subList(i, endIndex);

                try {
                    RBatch batch = redissonClient.createBatch();

                    for (User user : batchUsers) {
                        try {
                            UserDTO userDTO = userMapper.toDto(user);
                            RedisUserDTO redisUserDTO = new RedisUserDTO(userDTO);
                            String jsonValue = objectMapper.writeValueAsString(redisUserDTO);
                            String redisKey = "user:" + user.getId().toString();

                            batch.getBucket(redisKey).setAsync(jsonValue);
                        } catch (Exception e) {
                            LOG.error("Failed to prepare user {} for Redis batch: {}", user.getId(), e.getMessage());
                        }
                    }

                    batch.execute();
                    syncedCount += batchUsers.size();

                } catch (Exception e) {
                    LOG.error("Failed to execute Redis batch: {}", e.getMessage());
                }
            }

            pageNumber++;

            if (syncedCount % 10000 == 0) {
                double progress = (double) syncedCount / totalCount * 100;
                LOG.info("Synced {}/{} users to Redis ({:.2f}%)", syncedCount, totalCount, progress);
            }

            if (!userPage.hasNext()) {
                break;
            }
        }

        LOG.info("Successfully synced {} out of {} users to Redis", syncedCount, totalCount);
        return syncedCount;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RedisUserDTO> findOneFromRedis(UUID id) {
        LOG.debug("Request to get User from Redis: {}", id);

        String redisKey = "user:" + id.toString();

        try {
            RBucket<String> bucket = redissonClient.getBucket(redisKey);
            String jsonValue = bucket.get();

            if (jsonValue != null) {
                LOG.debug("User {} found in Redis", id);
                RedisUserDTO redisUserDTO = objectMapper.readValue(jsonValue, RedisUserDTO.class);
                return Optional.of(redisUserDTO);
            } else {
                LOG.debug("User {} not found in Redis, falling back to database", id);
                // Fallback to database
                Optional<UserDTO> userFromDb = userRepository.findById(id).map(userMapper::toDto);

                // If found in database, cache it in Redis for future requests
                if (userFromDb.isPresent()) {
                    try {
                        RedisUserDTO redisUserDTO = new RedisUserDTO(userFromDb.orElseThrow());
                        String jsonToCache = objectMapper.writeValueAsString(redisUserDTO);
                        bucket.set(jsonToCache);
                        LOG.debug("Cached user {} in Redis", id);
                        return Optional.of(redisUserDTO);
                    } catch (Exception e) {
                        LOG.error("Failed to cache user {} in Redis: {}", id, e.getMessage());
                        return Optional.of(new RedisUserDTO(userFromDb.orElseThrow()));
                    }
                }

                return Optional.empty();
            }
        } catch (Exception e) {
            LOG.error("Error getting user {} from Redis, falling back to database: {}", id, e.getMessage());
            return userRepository.findById(id).map(userMapper::toDto).map(RedisUserDTO::new);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RedisUserDTO> findMultipleFromRedis(List<UUID> ids) {
        LOG.debug("Request to get {} users from Redis", ids.size());

        List<RedisUserDTO> result = new ArrayList<>();
        List<UUID> missingIds = new ArrayList<>();

        try {
            // Batch get from Redis
            RBatch batch = redissonClient.createBatch();
            Map<UUID, RFuture<String>> futures = new HashMap<>();

            for (UUID id : ids) {
                String redisKey = "user:" + id.toString();
                RBucketAsync<String> bucket = batch.getBucket(redisKey);
                RFuture<String> future = bucket.getAsync();
                futures.put(id, future);
            }

            batch.execute();

            // Process Redis results
            for (UUID id : ids) {
                try {
                    String jsonValue = futures.get(id).get();
                    if (jsonValue != null) {
                        RedisUserDTO redisUserDTO = objectMapper.readValue(jsonValue, RedisUserDTO.class);
                        result.add(redisUserDTO);
                        LOG.debug("User {} found in Redis", id);
                    } else {
                        missingIds.add(id);
                        LOG.debug("User {} not found in Redis", id);
                    }
                } catch (Exception e) {
                    LOG.error("Error parsing user {} from Redis: {}", id, e.getMessage());
                    missingIds.add(id);
                }
            }

        } catch (Exception e) {
            LOG.error("Error during batch Redis operation: {}", e.getMessage());
            missingIds.addAll(ids);
        }

        // Fallback to database for missing users
        if (!missingIds.isEmpty()) {
            LOG.debug("Fetching {} missing users from database", missingIds.size());
            List<User> usersFromDb = userRepository.findAllById(missingIds);

            for (User user : usersFromDb) {
                UserDTO userDTO = userMapper.toDto(user);
                RedisUserDTO redisUserDTO = new RedisUserDTO(userDTO);
                result.add(redisUserDTO);

                // Cache in Redis for future requests
                try {
                    String jsonToCache = objectMapper.writeValueAsString(redisUserDTO);
                    String redisKey = "user:" + user.getId().toString();
                    RBucket<String> bucket = redissonClient.getBucket(redisKey);
                    bucket.setAsync(jsonToCache);
                } catch (Exception e) {
                    LOG.error("Failed to cache user {} in Redis: {}", user.getId(), e.getMessage());
                }
            }
        }

        LOG.debug("Retrieved {} users total ({} from Redis, {} from database)",
                result.size(), ids.size() - missingIds.size(), missingIds.size());

        return result;
    }

}
