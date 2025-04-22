package com.f4.user.service;

import com.f4.user.service.dto.KeycloakUserDTO;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.f4.user.domain.KeycloakUser}.
 */
public interface KeycloakUserService {
    /**
     * Save a keycloakUser.
     *
     * @param keycloakUserDTO the entity to save.
     * @return the persisted entity.
     */
    KeycloakUserDTO save(KeycloakUserDTO keycloakUserDTO);

    /**
     * Updates a keycloakUser.
     *
     * @param keycloakUserDTO the entity to update.
     * @return the persisted entity.
     */
    KeycloakUserDTO update(KeycloakUserDTO keycloakUserDTO);

    /**
     * Partially updates a keycloakUser.
     *
     * @param keycloakUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<KeycloakUserDTO> partialUpdate(KeycloakUserDTO keycloakUserDTO);

    /**
     * Get all the keycloakUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KeycloakUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" keycloakUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KeycloakUserDTO> findOne(UUID id);

    /**
     * Delete the "id" keycloakUser.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);
}
