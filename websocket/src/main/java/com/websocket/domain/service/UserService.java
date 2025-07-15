package com.websocket.domain.service;

import com.websocket.application.dto.User;
import com.websocket.external.entity.UserEntity;
import com.websocket.external.repository.UserRepository;
import com.websocket.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUserInfo() {
        return UserMapper.userEntitiesToUserDtos(userRepository.findAll());
    }

    @Transactional
    public User saveUser(User user) {
        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }

        UserEntity dbUser = UserMapper.UserDtoToUserEntity(user);
        UserEntity savedUser = userRepository.save(dbUser);
        return UserMapper.userEntityToUserDto(savedUser);
    }
}