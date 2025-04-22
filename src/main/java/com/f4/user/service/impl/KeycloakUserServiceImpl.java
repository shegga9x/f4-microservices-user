package com.f4.user.service.impl;

import com.f4.user.domain.KeycloakUser;
import com.f4.user.repository.KeycloakUserRepository;
import com.f4.user.service.KeycloakUserService;
import com.f4.user.service.dto.KeycloakUserDTO;
import com.f4.user.service.mapper.KeycloakUserMapper;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.f4.user.domain.KeycloakUser}.
 */
@Service
@Transactional
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private static final Logger LOG = LoggerFactory.getLogger(KeycloakUserServiceImpl.class);

    private final KeycloakUserRepository keycloakUserRepository;

    private final KeycloakUserMapper keycloakUserMapper;

    public KeycloakUserServiceImpl(KeycloakUserRepository keycloakUserRepository, KeycloakUserMapper keycloakUserMapper) {
        this.keycloakUserRepository = keycloakUserRepository;
        this.keycloakUserMapper = keycloakUserMapper;
    }

    @Override
    public KeycloakUserDTO save(KeycloakUserDTO keycloakUserDTO) {
        LOG.debug("Request to save KeycloakUser : {}", keycloakUserDTO);
        KeycloakUser keycloakUser = keycloakUserMapper.toEntity(keycloakUserDTO);
        keycloakUser = keycloakUserRepository.save(keycloakUser);
        return keycloakUserMapper.toDto(keycloakUser);
    }

    @Override
    public KeycloakUserDTO update(KeycloakUserDTO keycloakUserDTO) {
        LOG.debug("Request to update KeycloakUser : {}", keycloakUserDTO);
        KeycloakUser keycloakUser = keycloakUserMapper.toEntity(keycloakUserDTO);
        keycloakUser = keycloakUserRepository.save(keycloakUser);
        return keycloakUserMapper.toDto(keycloakUser);
    }

    @Override
    public Optional<KeycloakUserDTO> partialUpdate(KeycloakUserDTO keycloakUserDTO) {
        LOG.debug("Request to partially update KeycloakUser : {}", keycloakUserDTO);

        return keycloakUserRepository
            .findById(keycloakUserDTO.getId())
            .map(existingKeycloakUser -> {
                keycloakUserMapper.partialUpdate(existingKeycloakUser, keycloakUserDTO);

                return existingKeycloakUser;
            })
            .map(keycloakUserRepository::save)
            .map(keycloakUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<KeycloakUserDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all KeycloakUsers");
        return keycloakUserRepository.findAll(pageable).map(keycloakUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KeycloakUserDTO> findOne(UUID id) {
        LOG.debug("Request to get KeycloakUser : {}", id);
        return keycloakUserRepository.findById(id).map(keycloakUserMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        LOG.debug("Request to delete KeycloakUser : {}", id);
        keycloakUserRepository.deleteById(id);
    }
}
