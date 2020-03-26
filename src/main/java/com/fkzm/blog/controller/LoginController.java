package com.fkzm.blog.controller;

import com.fkzm.blog.entities.User;
import com.fkzm.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String loginPage(){


        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password , HttpSession session, RedirectAttributes redirectAttributes){
        User user = adminService.findUser(username, password);
        if(user != null){
            //session里的密码要清除掉,不然不安全
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/dashboard";
        } else{
                redirectAttributes.addFlashAttribute("message", "用户名或密码错误!");
                return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String toDashBoard(){
        return "admin/dashboard";
    }
}
