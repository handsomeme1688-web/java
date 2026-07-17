package com.zoee.day3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoee.day3.entity.User;
import com.zoee.day3.mapper.UserMapper;
import com.zoee.day3.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
