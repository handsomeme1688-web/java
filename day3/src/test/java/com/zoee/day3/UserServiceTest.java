package com.zoee.day3;


import com.zoee.day3.entity.User;
import com.zoee.day3.mapper.UserMapper;
import com.zoee.day3.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    void getById(){
        User u1 = userService.getById(1L);
        assertEquals("user01",u1.getUsername());
        assertEquals("张伟",u1.getNickname());
    }

    @Test
    void findById(){
        User u2 = userMapper.findById(1L);
        assertEquals("user02",u2.getUsername());
        assertEquals("张伟",u2.getNickname());
    }

    @Test
    void getById_null() {
        assertNull(userService.getById(999L));
    }





}
