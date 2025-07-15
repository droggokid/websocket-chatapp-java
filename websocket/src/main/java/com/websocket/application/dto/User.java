package com.websocket.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class User {
    private String fullName;
    private Image image;
    private String email;
    private String username;
    private String password;
    private LocalDateTime lastSeen;
    private LocalDateTime createdAt;
}