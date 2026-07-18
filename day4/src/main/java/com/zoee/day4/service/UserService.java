package com.zoee.day4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoee.day4.entity.User;




public interface UserService extends IService<User> {
    void register(User user);

    String login(User user);
}
