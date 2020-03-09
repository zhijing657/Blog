package com.zhijing.controller.admin;

import com.zhijing.pojo.User;
import com.zhijing.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/toLogin")
    public String toLoginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
        User user = userService.checkUser(username, password);
        if (user!=null){
            session.setAttribute("user",user);
            return "redirect:index";
        }else{
            redirectAttributes.addFlashAttribute("message","用户名或密码错误！");
            return "redirect:/admin";
        }

    }

    @GetMapping("/admin/logout")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/admin";
    }

    @GetMapping({"/admin/index","/admin"})
    public String toIndex(){
        return "admin/index";
    }
}
