package com.zoee.day6.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoee.day6.entity.User;





public interface UserService extends IService<User> {

    void regiter(User user);
    String login(User user);
}
