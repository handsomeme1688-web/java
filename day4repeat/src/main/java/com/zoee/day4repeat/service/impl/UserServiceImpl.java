package com.zoee.day4repeat.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoee.day4repeat.entity.User;
import com.zoee.day4repeat.mapper.UserMapper;
import com.zoee.day4repeat.service.UserService;
import com.zoee.day4repeat.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    // 注册,判重名
    public void register(User user) {
        User existuser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
        if(existuser!=null){
            throw new RuntimeException("用户名已存在");
        }

        String hashpwed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashpwed);
        userMapper.insert(user);
    }

    // 登录，检测密码,签发令牌

    public String login(User user) {
        User existUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
        if (existUser == null) throw new RuntimeException("用户不存在,请注册后再登录！");
        if (!BCrypt.checkpw(user.getPassword(),existUser.getPassword())){
            throw new RuntimeException("密码错误！");
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",existUser.getId());
        String token = JwtUtils.generateJwt(claims);
        return token;
    }


}
