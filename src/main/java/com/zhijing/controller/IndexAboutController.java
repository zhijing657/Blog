package com.zhijing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexAboutController {
    @GetMapping("/about")
    public String about(Model model){

        return "about";
    }
}
