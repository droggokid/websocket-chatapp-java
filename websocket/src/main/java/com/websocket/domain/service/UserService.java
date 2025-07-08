package com.websocket.domain.service;

import com.websocket.external.entity.UserInfoEntity;
import com.websocket.external.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserInfoEntity> getAllUserInfo() {
        return userRepository.findAll();
    }

    public void addUser(@RequestBody UserInfoEntity userInfoEntity) {
        if (userInfoEntity != null) {
            userRepository.save(userInfoEntity);
        }
    }
}
