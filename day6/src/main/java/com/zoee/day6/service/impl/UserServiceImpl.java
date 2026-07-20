package com.zoee.day6.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoee.day6.entity.User;
import com.zoee.day6.exception.UserException;
import com.zoee.day6.mapper.UserMapper;
import com.zoee.day6.service.UserService;
import com.zoee.day6.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User>implements UserService {

    private final UserMapper userMapper;

    @Override
    public void regiter(User user) {
        User existUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
        if(existUser!=null){
            throw new RuntimeException("用户名已存在");
        }
        String hashpwed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashpwed);
        userMapper.insert(user);
    }

    @Override
    public String login(User user) {
        User existUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
        if(existUser==null){
            throw  new UserException("请先注册！");
        }
        if(!BCrypt.checkpw(user.getPassword(),existUser.getPassword())){
            throw new UserException("密码错误");
        };
        //签发令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",existUser.getId());
        return JwtUtils.generateJwt(claims);
    }
}
