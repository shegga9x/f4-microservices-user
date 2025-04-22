package com.f4.user.service.mapper;

import static com.f4.user.domain.UserAsserts.*;
import static com.f4.user.domain.UserTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getUserSample1();
        var actual = userMapper.toEntity(userMapper.toDto(expected));
        assertUserAllPropertiesEquals(expected, actual);
    }
}
