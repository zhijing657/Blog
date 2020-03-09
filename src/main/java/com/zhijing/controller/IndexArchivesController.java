package com.zhijing.controller;

import com.zhijing.pojo.Blog;
import com.zhijing.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class IndexArchivesController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String about(Model model){
        Map<String, List<Blog>> archives = blogService.archives();
        Long blogCount = blogService.getBlogCount();

        model.addAttribute("count",blogCount);
        model.addAttribute("archives",archives);
        return "archives";
    }
}
