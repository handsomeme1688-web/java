package com.zoee.day4repeat.interceptor;

import com.zoee.day4repeat.common.UserContext;
import com.zoee.day4repeat.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(!StringUtils.hasLength(token)){
            response.setStatus(401);
            return false;
        }

        try{
            Claims claims = JwtUtils.parseJwt(token);
            Long userId=Long.valueOf(claims.get("id").toString());
            UserContext.setUser_ID(userId);
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
