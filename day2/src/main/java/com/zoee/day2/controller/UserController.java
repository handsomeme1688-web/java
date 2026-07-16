package com.zoee.day2.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.IoUtil;
import com.zoee.day2.pojo.User;
import com.zoee.day2.service.UserService;
import com.zoee.day2.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * 用户信息的控制器
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public List<User> list() throws FileNotFoundException {
        return userService.findAll();
    }

    /**
     * @RequestParam(name = "name") String username
     * //            └──①──┘ └──②──┘ └───③──┘└───④────┘
     * ①：注解的属性 : name =
     * ②：参数名称 : "name"
     * ③：参数类型
     * ④：参数说明：把前端传过来的 “name” 字段的值赋值给我这里的 username
     *
     * Query 参数（查询参数）
     * 是什么：最传统的传参方式，直接把参数拼接在 URL 问号 ? 后面，用 & 连接。
     * 长啥样（URL）：
     * http://localhost:8080/user?name=Tom&age=10
     * @return
     */
    @GetMapping("/list2")
    public String list2(@RequestParam(name="name")String username, Integer age){
        return username+"hello!";
    }

    /**
     * Path 参数（路径参数）
     * 是什么：把参数作为 URL 路径的一部分，通常用于精准定位某一个具体资源（RESTful 风格）。
     * 长啥样（URL）：
     * http://localhost:8080/user/10 （这里的 10 就是下面的方法中 id 字段的值）
     * @return
     */
    @GetMapping("/list3/{id}") //大括号里的 {id} 必须和下面形参的变量名 id 保持一致！
    public String list3(@PathVariable Integer id){
        return "hello! 第"+id+"个用户！";
    }

    /**
     * 表单参数（Form / x-www-form-urlencoded）
     * 是什么：模拟网页 HTML <form> 表单提交的数据。数据不显示在 URL 里，而是放在 POST 请求的 Body（请求体） 中，格式依然是类似 key1=value1&key2=value2 的键值对。
     * 长啥样（HTTP 请求体）：
     *
     * POST /user HTTP/1.1
     * Content-Type: application/x-www-form-urlencoded
     *
     * name=Tom&age=10
     *
     */
    @PostMapping("/list4")
    public String list4(String name, Integer age){
        return name+age+"hello!";
    }


    /**
     * JSON 参数（@RequestBody JSON）
     * 是什么：现代前后端分离项目中最主流的传参方式。前端把复杂的数据结构封装成一个 JSON 字符串，塞进 Body（请求体） 传给后端。
     * 长啥样（HTTP 请求体）：
     *
     * POST /user HTTP/1.1
     * Content-Type: application/json
     *
     * {
     *   "name": "Tom",
     *   "age": 10,
     *   "hobby": ["coding", "gaming"]
     * }
     */
    @PostMapping("/list5") //“GET + JSON”在现实中是个定时炸弹
    public String list5(@RequestBody User user){
        return "json hello!";
    }


    /**
     * 数组/集合参数（Array）
     * 是什么：当你要传递一堆同类型的数据时（比如批量删除 3 个用户，需要传 3 个 ID：1, 2, 3）。
     * 长啥样（两种常见传法）：
     *
     * 方式 A（Query 拼接）：http://localhost:8080/delete?ids=1&ids=2&ids=3 （或者 ids=1,2,3）
     * 方式 B（JSON 数组）：Body 里直接传 [1, 2, 3]
     */
    @GetMapping("/delete") // 方式 A
    public String arrayParam(Integer[] ids) { // 也可以用 @RequestParam List<Integer> ids
        return "OK，数组，方式 A";
    }

    @PostMapping("/delete2") //方式 B，涉及到 Json 都要用 @RequestBody
    public String jsonArrayParam(@RequestBody List<Integer> ids) { // 用 @RequestBody 接收
        return "OK，数组，方式 B";
    }
}






















