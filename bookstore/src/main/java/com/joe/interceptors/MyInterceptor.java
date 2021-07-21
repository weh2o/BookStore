package com.joe.interceptors;

import com.joe.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //從session中獲取用戶信息
        User user = (User) request.getSession().getAttribute("user");
        //判斷用戶是否登入
        if (user == null) {
            //沒登入，請求轉發到指定頁面
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
