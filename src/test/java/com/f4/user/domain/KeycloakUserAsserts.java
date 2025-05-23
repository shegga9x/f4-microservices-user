package com.f4.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class KeycloakUserAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertKeycloakUserAllPropertiesEquals(KeycloakUser expected, KeycloakUser actual) {
        assertKeycloakUserAutoGeneratedPropertiesEquals(expected, actual);
        assertKeycloakUserAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertKeycloakUserAllUpdatablePropertiesEquals(KeycloakUser expected, KeycloakUser actual) {
        assertKeycloakUserUpdatableFieldsEquals(expected, actual);
        assertKeycloakUserUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertKeycloakUserAutoGeneratedPropertiesEquals(KeycloakUser expected, KeycloakUser actual) {
        assertThat(actual)
            .as("Verify KeycloakUser auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertKeycloakUserUpdatableFieldsEquals(KeycloakUser expected, KeycloakUser actual) {
        assertThat(actual)
            .as("Verify KeycloakUser relevant properties")
            .satisfies(a -> assertThat(a.getUsername()).as("check username").isEqualTo(expected.getUsername()))
            .satisfies(a -> assertThat(a.getEmail()).as("check email").isEqualTo(expected.getEmail()))
            .satisfies(a -> assertThat(a.getRealmId()).as("check realmId").isEqualTo(expected.getRealmId()))
            .satisfies(a -> assertThat(a.getUserId()).as("check userId").isEqualTo(expected.getUserId()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertKeycloakUserUpdatableRelationshipsEquals(KeycloakUser expected, KeycloakUser actual) {
        // empty method
    }
}
