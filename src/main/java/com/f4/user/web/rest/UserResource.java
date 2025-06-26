package com.f4.user.web.rest;

import com.f4.user.repository.UserRepository;
import com.f4.user.service.UserService;
import com.f4.user.service.dto.UserDTO;
import com.f4.user.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.f4.user.domain.User}.
 */
@RestController
@RequestMapping("/api/users")
public class UserResource {

    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    private static final String ENTITY_NAME = "msUserUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserService userService;

    private final UserRepository userRepository;

    public UserResource(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * {@code POST  /users} : Create a new user.
     *
     * @param userDTO the userDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userDTO, or with status {@code 400 (Bad Request)} if the user has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        LOG.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", ENTITY_NAME, "idexists");
        }
        userDTO = userService.save(userDTO);
        return ResponseEntity.created(new URI("/api/users/" + userDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, userDTO.getId().toString()))
            .body(userDTO);
    }

    /**
     * {@code PUT  /users/:id} : Updates an existing user.
     *
     * @param id the id of the userDTO to save.
     * @param userDTO the userDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDTO,
     * or with status {@code 400 (Bad Request)} if the userDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody UserDTO userDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update User : {}, {}", id, userDTO);
        if (userDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        userDTO = userService.update(userDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userDTO.getId().toString()))
            .body(userDTO);
    }

    /**
     * {@code PATCH  /users/:id} : Partial updates given fields of an existing user, field will ignore if it is null
     *
     * @param id the id of the userDTO to save.
     * @param userDTO the userDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDTO,
     * or with status {@code 400 (Bad Request)} if the userDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserDTO> partialUpdateUser(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody UserDTO userDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update User partially : {}, {}", id, userDTO);
        if (userDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserDTO> result = userService.partialUpdate(userDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /users} : get all the users.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of users in body.
     */
    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        LOG.debug("REST request to get all Users");
        return userService.findAll();
    }

    /**
     * {@code GET  /users/:id} : get the "id" user.
     *
     * @param id the id of the userDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") UUID id) {
        LOG.debug("REST request to get User : {}", id);
        Optional<UserDTO> userDTO = userService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDTO);
    }

    /**
     * {@code DELETE  /users/:id} : delete the "id" user.
     *
     * @param id the id of the userDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        LOG.debug("REST request to delete User : {}", id);
        userService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code POST  /users/sync-to-redis} : Sync all users to Redis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and sync result in body.
     */
    @PostMapping("/sync-to-redis")
    public ResponseEntity<Map<String, Object>> syncUsersToRedis() {
        LOG.debug("REST request to sync all users to Redis");
        
        // For 1M records, start async processing
        CompletableFuture.supplyAsync(() -> {
            try {
                return userService.syncAllUsersToRedis();
            } catch (Exception e) {
                LOG.error("Error during Redis sync: {}", e.getMessage());
                return 0L;
            }
        }).thenAccept(count -> {
            LOG.info("Async Redis sync completed. Synced {} users", count);
        });
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Redis sync started in background. Check logs for progress.");
        result.put("status", "STARTED");
        
        return ResponseEntity.ok(result);
    }

    /**
     * {@code POST  /users/sync-to-redis/sync} : Synchronously sync users to Redis (for smaller datasets).
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and sync result in body.
     */
    @PostMapping("/sync-to-redis/sync")
    public ResponseEntity<Map<String, Object>> syncUsersToRedisSync() {
        LOG.debug("REST request to synchronously sync users to Redis");
        
        long syncedCount = userService.syncAllUsersToRedis();
        
        Map<String, Object> result = new HashMap<>();
        result.put("syncedCount", syncedCount);
        result.put("message", "Successfully synced " + syncedCount + " users to Redis");
        result.put("status", "COMPLETED");
        
        return ResponseEntity.ok(result);
    }
}
