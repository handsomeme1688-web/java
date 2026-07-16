package com.zoee.day2.service.serviceImpl;

import com.zoee.day2.dao.UserDao;
import com.zoee.day2.dao.daoImpl.UserDaoImpl;
import com.zoee.day2.pojo.User;
import com.zoee.day2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserServiceImpl implements UserService {
    /**
     * @Autowired ： 表示由IOC容器自动装配，不需要再使用new关键字
     *
     */
    @Autowired
    private UserDao userDao ;
    @Override
    public List<User> findAll() {

        List<String> lines = userDao.findAll();

        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            return new User(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }).collect(Collectors.toList());

        return userList;

    }
}
