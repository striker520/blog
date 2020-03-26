package com.fkzm.blog.config.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        if(session.getAttribute("user")!=null){
            return true;
        }else{
            session.setAttribute("message", "对不起没有权限!");
            response.sendRedirect("/admin");
            return false;
        }
    }
}
