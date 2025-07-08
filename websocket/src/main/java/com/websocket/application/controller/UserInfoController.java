package com.websocket.application.controller;

import com.websocket.domain.service.UserService;
import com.websocket.external.entity.UserInfoEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserInfoController {
    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserInfoEntity> getAllUsersInfo() {
        return userService.getAllUserInfo();
    }

    @PostMapping("/user")
    public void addUserInfo(@RequestBody UserInfoEntity userInfo) {
        userService.addUser(userInfo);
    }


}
