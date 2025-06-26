package com.f4.user.service.impl;

import com.f4.user.domain.User;
import com.f4.user.repository.UserRepository;
import com.f4.user.service.UserService;
import com.f4.user.service.dto.UserDTO;
import com.f4.user.service.mapper.UserMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.redisson.api.RedissonClient;
import org.redisson.api.RBucket;
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
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
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
        int batchSize = 100;  // Redis batch operations
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
                            String jsonValue = objectMapper.writeValueAsString(userDTO);
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

}

