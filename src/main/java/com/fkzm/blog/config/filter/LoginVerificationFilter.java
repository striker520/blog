package com.fkzm.blog.config.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class LoginVerificationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session=request.getSession();
        if(session.getAttribute("user")!=null){
            chain.doFilter(request, response);
        }else{
            session.setAttribute("message", "对不起没有权限!");
            response.sendRedirect("/blog");

        }
    }
}
