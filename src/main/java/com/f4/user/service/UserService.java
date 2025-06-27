package com.f4.user.service;

import com.f4.user.service.dto.RedisUserDTO;
import com.f4.user.service.dto.UserDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Interface for managing {@link com.f4.user.domain.User}.
 */
public interface UserService {
    /**
     * Save a user.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    UserDTO save(UserDTO userDTO);

    /**
     * Updates a user.
     *
     * @param userDTO the entity to update.
     * @return the persisted entity.
     */
    UserDTO update(UserDTO userDTO);

    /**
     * Partially updates a user.
     *
     * @param userDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserDTO> partialUpdate(UserDTO userDTO);

    /**
     * Get all the users.
     *
     * @return the list of entities.
     */
    List<UserDTO> findAll();

    /**
     * Get the "id" user.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDTO> findOne(UUID id);

    /**
     * Delete the "id" user.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);

    /**
     * Sync all users to Redis.
     *
     * @return the number of users synced to Redis.
     */
    long syncAllUsersToRedis();

    /**
     * Get user from Redis by ID with fallback to database.
     *
     * @param id the id of the user.
     * @return the user from Redis or database as RedisUserDTO.
     */
    Optional<RedisUserDTO> findOneFromRedis(UUID id);

    /**
     * Get multiple users from Redis by IDs with fallback to database.
     *
     * @param ids the list of user IDs.
     * @return the list of users from Redis or database as RedisUserDTO.
     */
    List<RedisUserDTO> findMultipleFromRedis(List<UUID> ids);
}
