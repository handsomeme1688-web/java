package com.zoee.day4.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zoee.day4.entity.User;
import com.zoee.day4.exception.UserException;
import com.zoee.day4.mapper.UserMapper;
import com.zoee.day4.service.UserService;
import com.zoee.day4.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    /**
     * 注册校验业务
     * @param user
     */
    // 注册：判重名，BCrypt加密，落库。
    public void register(User user){
        // 获取数据库比对信息
        User existUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));

        // 重名拒绝
        if (existUser!= null){
            throw new RuntimeException("用户名已存在");
        }

        // 未重名落库
        // 落库前加密
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        // 落库
        userMapper.insert(user);
    }

    /**
     * 登录校验业务
     * @param user
     */
    public String login(User user){
        // 判断用户名与密码是否正确,不正确返回错误
        User existUser=userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
        if (existUser==null){
            throw new UserException("用户名不存在，请先注册！");
        }
        if(!BCrypt.checkpw(user.getPassword(),existUser.getPassword())){
            throw new UserException("密码错误，请重新输入！");
        }

        // 正确则签发令牌并返回给Controller
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",existUser.getId());
        return  JwtUtils.generateJwt(claims);



    }
}
