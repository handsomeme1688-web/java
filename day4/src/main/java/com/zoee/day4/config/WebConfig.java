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
 * config 类只在启动时执行一次，把"拦截器+拦截规则"注册进框架。
 * 请求来的时候没人再去读 config
 * DispatcherServlet 查路由命中方法后，直接按已注册的规则组装出"拦截器链+目标方法"的执行链，然后依次跑 preHandle。
 * 路由没命中就直接 404，拦截器不执行（所以 404 优先于 401）。
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
        // 方法名有 s，这里没有
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/users/login","/users/register");
    }

}
