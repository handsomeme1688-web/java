package com.zoee.day4.controller;


import com.zoee.day4.common.UserContext;
import com.zoee.day4.entity.User;
import com.zoee.day4.result.Result;
import com.zoee.day4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){ //@RequestBody 的主要作用是：告诉 Spring 框架，把前端传过来的 JSON 字符串（或者 XML）自动解析并转换成一个 Java 对象

        return Result.success(userService.login(user));
    }


    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user){
        userService.register(user);
        return Result.success(null);
    }


    /**
     * return Result.success(...) 不是在承诺"这个请求一定成功"，而是"只有成功了才轮得到这一行执行"。
     * 失败的请求走的是另外两扇门，根本到不了这行代码
     *
     * 门 1（拦截器，在 Controller 之前）：/me 的请求没带 token 或验签失败时，preHandle 里已经 setStatus(401) + return false
     *      流程当场终止，me() 方法一个字都不会执行。
     *      所以能进到 me() 方法体的请求，百分之百是已经通过安检、ThreadLocal 里已经有 userId 的。不存在"进来了但没验过"的中间状态。
     * 门 2（异常，绕过 return 那行）：login 也一样。密码错误时 Service 里 throw new UserException(...)
     *      异常一抛，方法立刻中断往上飞，Controller 里 return Result.success(...) 这行被跳过了，
     *      异常落进 GlobalExceptionHandler，由它返回 Result.error("密码错误")。
     * 门 3（正门）：一路平安，才执行到 return Result.success(...)。
     * @return
     */

    /**
     * 前端登录后不知道"我是谁"，它调用 GET /users/me 问后端："token 对应的这个用户，数据库 id 是多少？" 后端回答：7。
     * 前端拿到这个 id 后，后续请求可能需要用它（比如展示"我的订单"、"我的收藏"）。
     * 这不是"取出来没用"，返回它就是用了。
     *
     *
     * /me 本身就是"终点"，不需要 Service 再做任何事情。
     * 它的业务就是"告诉我我是谁"，拿到 id → 返回 id，这就完成了。
     * 就像你去派出所查自己的身份证号，查到了直接告诉你，不需要再拿着这个号去做什么。
     * @return
     */
    @GetMapping("/me")
    public Result<Long> me(){
        return Result.success(UserContext.getUserId());
    }


}
