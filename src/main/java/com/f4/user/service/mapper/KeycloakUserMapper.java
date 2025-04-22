package com.f4.user.service.mapper;

import com.f4.user.domain.KeycloakUser;
import com.f4.user.service.dto.KeycloakUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link KeycloakUser} and its DTO {@link KeycloakUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface KeycloakUserMapper extends EntityMapper<KeycloakUserDTO, KeycloakUser> {}
