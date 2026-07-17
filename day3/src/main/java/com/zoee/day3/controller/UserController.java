package com.zoee.day3.controller;


import com.zoee.day3.common.Result;
import com.zoee.day3.entity.User;
import com.zoee.day3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserController {

    final UserService userService;

    @GetMapping("/user/{id}")
    public Result<User> getById(@PathVariable Long id){
        User user = userService.getById(id);
        return Result.success(user);
    }
}
