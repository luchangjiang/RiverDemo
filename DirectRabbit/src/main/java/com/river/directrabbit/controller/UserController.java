package com.river.directrabbit.controller;

import com.river.directrabbit.model.UserEntity;
import com.river.directrabbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMessage")
    public String sendMessage() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(20);
        userEntity.setName("zhangsan");
        userService.saveUser(userEntity);

        return "success";
    }

}
