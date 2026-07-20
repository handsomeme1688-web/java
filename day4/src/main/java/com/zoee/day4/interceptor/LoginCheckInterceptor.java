package com.zoee.day4.interceptor;

import com.zoee.day4.common.UserContext;
import com.zoee.day4.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component //拦截器必须成为 Component 对象
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // LoginCheckInterceptor 的 preHandle 第一行加：
        System.out.println(">>> 拦截到: " + request.getRequestURI());

        // 取请求头的token
        String token = request.getHeader("token");
        //JwtUtils解析
        if(!StringUtils.hasLength(token)){ //没带令牌，拒绝

            //失败响应401返回false
            response.setStatus(401);
            return false;
        }

        // 有令牌，尝试解析
        try {
            /**
             * [ claims 对象 (实质上是个 Map) ]
             *    │
             *    ├── 标准 JWT 声明（自带字段）:
             *    │     ├── "iss" (Issuer): "明华软件公司" (签发者)
             *    │     ├── "exp" (Expiration): 1781282022 (令牌过期时间戳)
             *    │     └── "iat" (Issued At): 1781270000 (令牌签发时间戳)
             *    │
             *    └── 自定义自定义声明（你塞进去的业务数据）:
             *          ├── "id" : 14325       <-- 你的代码执行 claims.get("id") 拿到的就是它！
             *          ├── "username" : "zoee"
             *          └── "role" : "admin"
             */
            Claims claims = JwtUtils.parseJwt(token);
            Long userId=Long.valueOf(claims.get("id").toString());
            UserContext.setUserId(userId);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContext.remove();
    }
}
