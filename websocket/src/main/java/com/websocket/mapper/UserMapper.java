package com.websocket.mapper;

import com.websocket.application.dto.User;
import com.websocket.external.entity.UserEntity;
import com.websocket.external.entity.ImageEntity;

import java.util.List;

public class UserMapper {

    public static User userEntityToUserDto(UserEntity userEntity) {
        return User.builder()
                .fullName(userEntity.getFullName())
                .image(ImageMapper.ImageEntityToImageDto(userEntity.getImage()))
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .lastSeen(userEntity.getLastSeen())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }

    public static UserEntity UserDtoToUserEntity(User user) {
        ImageEntity imageEntity = ImageMapper.ImageDtoToImageEntity(user.getImage());

        return UserEntity.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .image(imageEntity)
                .build();
    }

    public static List<User> userEntitiesToUserDtos(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(UserMapper::userEntityToUserDto)
                .toList();
    }
}
