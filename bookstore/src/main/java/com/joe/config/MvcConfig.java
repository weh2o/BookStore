package com.joe.config;

import com.joe.interceptors.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("/user/register");
        registry.addViewController("/login").setViewName("/user/login");
        registry.addViewController("/userInfo").setViewName("/user/userInfo");
        registry.addViewController("/updateUserInfo").setViewName("/user/updateUserInfo");
        registry.addViewController("/updatePassword").setViewName("/user/updatePassword");
        registry.addViewController("/userOrders").setViewName("/orders/userOrders");
    }

    //攔截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                //指定攔截的路徑
                .addPathPatterns(
                        "/cart/**",
                        "/orders/**",
                        "/ordersDetail/**",
                        "/book/**",
                        "/user/**"
                        )
                //排除攔截的路徑
                .excludePathPatterns(
                        "/book/list",
                        "/book/education/**",
                        "/book/search",
                        "/book/addlist",
                        "/book/list/*",
                        "/user/login",
                        "/user/register"
                );

    }
}
