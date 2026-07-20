package com.zoee.day4repeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoee.day4repeat.entity.User;

public interface UserService extends IService<User>{
    void register(User user);
    String login(User user);
}
