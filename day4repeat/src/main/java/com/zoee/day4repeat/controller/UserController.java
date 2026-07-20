package com.zoee.day4repeat.controller;


import com.zoee.day4repeat.common.UserContext;
import com.zoee.day4repeat.entity.User;
import com.zoee.day4repeat.result.Result;
import com.zoee.day4repeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user){
        userService.register(user);
        return Result.success(null);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){
        return Result.success(userService.login(user));
    }

    // 用户的id当然不能显示传参！
    @GetMapping("/me")
    public Result<Long> me(){
        return Result.success(UserContext.getUser_ID());
    }
}
