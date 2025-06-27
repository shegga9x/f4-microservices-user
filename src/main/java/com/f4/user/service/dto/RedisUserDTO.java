package com.f4.user.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;


@SuppressWarnings("common-java:DuplicatedBlocks")
public class RedisUserDTO implements Serializable {

    @NotNull
    private UUID id;

    @NotNull
    private String username;

    private String userAvatar;

    public RedisUserDTO(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.userAvatar = userDTO.getUserAvatar();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    


}
