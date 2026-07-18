package com.zoee.day4.config;

import com.zoee.day4.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 * 注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 自动注入
     * 拦截器必须成为 Component 对象
     */
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebConfig 的 addInterceptors 第一行加：
        System.out.println(">>> 拦截器已注册");
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/users/login","/users/register");
    }

}
