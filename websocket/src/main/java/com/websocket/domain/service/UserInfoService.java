package com.websocket.domain.service;

import com.websocket.external.entity.UserInfoEntity;
import com.websocket.external.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfoEntity> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    public void addUser(@RequestBody UserInfoEntity userInfoEntity) {
        if (userInfoEntity != null) {
            userInfoRepository.save(userInfoEntity);
        }
    }
}
