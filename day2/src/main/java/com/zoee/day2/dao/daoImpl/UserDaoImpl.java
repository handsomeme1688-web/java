package com.zoee.day2.dao.daoImpl;

import cn.hutool.core.io.IoUtil;
import com.zoee.day2.dao.UserDao;
import com.zoee.day2.pojo.User;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * 只写与数据库交互的代码
 */
//public class UserDaoImpl implements UserDao {
//    @Override
//    public List<String> findAll() {
//        InputStream in =this.getClass().getClassLoader().getResourceAsStream("static/user.txt");
//        //左边已经明确写了 ArrayList<String>，右边就不需要重复写一遍了，Java 编译器能自动推断出右边就是 String。
//        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
//        return lines;
//
//    }
//}


/**
 * @Component ：表示把UserDaoImpl类交给Spring容器管理
 *
  */
@Component
public class UserDaoImpl implements UserDao {
    @Override
    public List<String> findAll() {
        InputStream in =this.getClass().getClassLoader().getResourceAsStream("static/user.txt");
        //左边已经明确写了 ArrayList<String>，右边就不需要重复写一遍了，Java 编译器能自动推断出右边就是 String。
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;

    }
}
