package com.websocket.application.controller;

import com.websocket.domain.service.UserInfoService;
import com.websocket.external.entity.UserInfoEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/users")
    public List<UserInfoEntity> getAllUsersInfo() {
        return userInfoService.getAllUserInfo();
    }

    @PostMapping("/user")
    public void addUserInfo(@RequestBody UserInfoEntity userInfo) {
        userInfoService.addUser(userInfo);
    }


}
