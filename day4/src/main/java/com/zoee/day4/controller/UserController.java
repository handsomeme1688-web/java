package com.zoee.day4.controller;


import com.zoee.day4.common.UserContext;
import com.zoee.day4.entity.User;
import com.zoee.day4.result.Result;
import com.zoee.day4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){

        return Result.success(userService.login(user));
    }


    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user){
        userService.register(user);
        return Result.success(null);
    }


    @GetMapping("/me")
    public Result<Long> me(){
        return Result.success(UserContext.getUserId());
    }


}
