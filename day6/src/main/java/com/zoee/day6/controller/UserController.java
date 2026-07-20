package com.zoee.day6.controller;


import com.zoee.day6.common.UserContext;
import com.zoee.day6.entity.User;
import com.zoee.day6.result.Result;
import com.zoee.day6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user){
        userService.regiter(user);
        return Result.success(null);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){
        return Result.success(userService.login(user));
    }


    @GetMapping("/me")
    public Result<Long> me(){
        return Result.success(UserContext.getUserId());
    }
}
