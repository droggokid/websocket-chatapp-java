package com.websocket.mapper;

import com.websocket.application.dto.Message;
import com.websocket.external.entity.MessageEntity;

import java.util.List;

public class MessageMapper {

    public static Message messageEntityToMessageDto(MessageEntity messageEntity) {
        return Message.builder()
                .id(messageEntity.getId())
                .content(messageEntity.getContent())
                .user(UserMapper.userEntityToUserDto(messageEntity.getUser()))
                .timestamp(messageEntity.getCreatedAt())
                .type(messageEntity.getType())
                .build();
    }

    public static List<Message> userEntitiesToUserDtos(List<MessageEntity> messageEntities) {
        return messageEntities.stream()
                .map(MessageMapper::messageEntityToMessageDto)
                .toList();
    }
}
