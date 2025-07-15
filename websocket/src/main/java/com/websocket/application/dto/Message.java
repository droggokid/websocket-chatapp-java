package com.websocket.application.dto;

import com.websocket.external.entity.MessageType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Message {
    private Long id;
    private String content;
    private User user;
    private LocalDateTime timestamp;
    private MessageType type;
    private Long userId;
}
