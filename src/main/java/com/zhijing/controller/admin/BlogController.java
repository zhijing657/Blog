package com.zhijing.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhijing.pojo.Blog;
import com.zhijing.pojo.Tag;
import com.zhijing.pojo.Type;
import com.zhijing.pojo.User;
import com.zhijing.service.BlogService;
import com.zhijing.service.TagService;
import com.zhijing.service.TypeService;
import com.zhijing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    private static Integer index = 0;
    private static Integer page = 0;
    private static Integer pageSize = 10;

    @GetMapping("/blogs")
    public String blogs(Model model){
        HashMap<String, Integer> map = new HashMap<>();
        page = 0;
        map.put("startIndex",page);
        map.put("pageSize",pageSize);
        List<Blog> limitBlogs = blogService.getLimitBlogs(map);
        List<Type> allTypes = typeService.getAllType();
        for (Blog blog : limitBlogs){
            blog.setType(typeService.getType(blog.getType_id()));
        }
        model.addAttribute("blogs",limitBlogs);
        model.addAttribute("types",allTypes);
        model.addAttribute("blogPage",0);
        return "admin/blogs";
    }

    @GetMapping("/deleteBlog/{id}")
    public String deleteBlog(
            @PathVariable("id") Long id,
            RedirectAttributes attributes){
        blogService.deleteBlog(id);

        attributes.addFlashAttribute("result","成功删除博客");
        return "redirect:/admin/blogs";
    }

    @GetMapping("/previousBlogPage")
    public String previousTagPage(Model model,RedirectAttributes attributes){
        page = page -10;
        if (page < 10){
            page=0;
            return "redirect:/admin/blogs";
        }else{
            HashMap<String, Integer> map = new HashMap<>();
            map.put("startIndex",page);
            map.put("pageSize",pageSize);
            List<Blog> limitBlogs = blogService.getLimitBlogs(map);
            List<Type> allTypes = typeService.getAllType();
            for (Blog blog : limitBlogs){
                blog.setType(typeService.getType(blog.getType_id()));
            }
            model.addAttribute("blogs",limitBlogs);
            model.addAttribute("types",allTypes);
            model.addAttribute("blogPage",page/10);
            return "admin/blogs";
        }
    }

    @GetMapping("/nextBlogPage")
    public String nextBlogPage(Model model,RedirectAttributes attributes){
        page = page + 10;
        if (page/10<1){

        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("startIndex",page);
        map.put("pageSize",pageSize);
        List<Blog> limitBlogs = blogService.getLimitBlogs(map);
        List<Type> allTypes = typeService.getAllType();
        for (Blog blog : limitBlogs){
            blog.setType(typeService.getType(blog.getType_id()));
        }
        model.addAttribute("blogs",limitBlogs);
        model.addAttribute("types",allTypes);
        model.addAttribute("blogPage",page/10);
        return "admin/blogs";
    }

    @RequestMapping("/blogs/search")
    public String search(
                         @RequestParam String title,
                         @RequestParam Long type_id,
                         @RequestParam Boolean recommend,
                         Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("title",title);
        map.put("type_id",type_id);
        map.put("recommend",recommend);
        System.out.println(map);
        List<Blog> blogs = blogService.getBlogByCondition(map);
        model.addAttribute("blogs",blogs);
        model.addAttribute("blogPage",0);
        return "admin/blogs";
    }

    @GetMapping("/toAddBlogPage")
    public String toAddBlogPage(Model model){
        List<Type> allType = typeService.getAllType();
        List<Tag> allTag = tagService.getAllTag();


        model.addAttribute("types",allType);
        model.addAttribute("tags",allTag);
        return "admin/blogs-input";
    }

    @PostMapping("/addBlog")
    public String addBlog(Blog blog, HttpSession session,RedirectAttributes attributes){
        blog.setViews(0);
        blog.setCreate_time(new Date());
        blog.setUpdate_time(new Date());
        blog.setType_id(blog.getType_id());
        blog.setUser_id(new Long(1));
        blog.setUser(userService.selectUserById(blog.getUser_id()));
        blog.setType(typeService.getType(blog.getType_id()));
        blog.setTags(blogService.getTagByString(blog.getTag_ids()));
        System.out.println(blog);
        int i = blogService.saveBlog(blog);
        if (i>0){
            attributes.addFlashAttribute("result","操作成功");
        }else{
            attributes.addFlashAttribute("result","操作失败");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/updateBlog/{id}")
    public String updateBlog(@PathVariable("id") Long id,Model model){
        Blog blog = blogService.getBlog(id);
        Type type = typeService.getType(blog.getType_id());
        blog.setUser_id(new Long(1));
        blog.setUser(userService.selectUserById(blog.getUser_id()));
        List<Tag> tagByString = blogService.getTagByString(blog.getTag_ids());
        List<Type> allType = typeService.getAllType();
        List<Tag> allTag = tagService.getAllTag();

        model.addAttribute("types",allType);
        model.addAttribute("tags",allTag);
        model.addAttribute("blog",blog);
        model.addAttribute("blogId",id);
        model.addAttribute("blogType",type);
        model.addAttribute("blogTags",tagByString);
        return "admin/blogs-update";
    }

    @PostMapping("/updateBlog")
    public String updateBlog(Blog blog,RedirectAttributes attributes){
        System.out.println(blog);
        blog.setUpdate_time(new Date());
        int i = blogService.updateBlog(blog);
        if (i > 0) {
            attributes.addFlashAttribute("result","修改成功");
        }else{
            attributes.addFlashAttribute("result","修改失败");
        }
        return "redirect:/admin/blogs";
    }
}
