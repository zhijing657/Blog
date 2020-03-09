package com.zhijing.controller;

import com.zhijing.pojo.Blog;
import com.zhijing.pojo.Type;
import com.zhijing.service.BlogService;
import com.zhijing.service.TypeService;
import com.zhijing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexTypeController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;



    @GetMapping("/type/{id}")
    public String types(@PathVariable("id") Long id, Model model){
        List<Type> types = typeService.getAllType();
        List<Blog> blogs = blogService.getBlogByTypeId(id);
        List<Type> listType = new ArrayList<>();
        HashMap<Object, Integer> sortType = new HashMap<>();
        for (Blog blog : blogs){
            blog.setUser(userService.selectUserById(blog.getUser_id()));
            blog.setTags(blogService.getTagByString(blog.getTag_ids()));
            blog.setType(typeService.getType(blog.getType_id()));
        }
        for (Type type : types){
            type.setBlogs(blogService.getBlogByTypeId(type.getId()));
        }


        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("typeID",id);
        return "types";
    }
}
