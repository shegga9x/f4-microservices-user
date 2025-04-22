package com.f4.user.repository;

import com.f4.user.domain.KeycloakUser;
import java.util.UUID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the KeycloakUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KeycloakUserRepository extends JpaRepository<KeycloakUser, UUID> {}
