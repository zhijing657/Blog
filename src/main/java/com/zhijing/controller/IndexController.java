package com.zhijing.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zhijing.myexception.NotFoundException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class IndexController {
    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;




    @GetMapping({"/","/index","/index.html"})
    public String index(Model model){
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex",0);
        map.put("pageSize",8);
        HashMap<Object, Integer> sortType = new HashMap<>();
        HashMap<Object, Integer> sortTag = new HashMap<>();
        List<Blog> blogs = blogService.getAllBlog();
        List<Blog> publishedBlogs = blogService.getBlogByUtAndIP();
        List<Type> typeList = new ArrayList<>();
        List<Tag> tagList = new ArrayList<>();


        for (Blog blog : blogs){
            blog.setUser(userService.selectUserById(blog.getUser_id()));
            blog.setType(typeService.getType(blog.getType_id()));
            typeList.add(typeService.getType(blog.getType_id()));
            for (Tag tag: blogService.getTagByString(blog.getTag_ids())){
                tagList.add(tag);
            }
        }
        //获取所有Type 不同Type的个数
        for (Type i : typeList){
            Integer count = sortType.get(i);
            sortType.put(i,(count == null) ? 1 :count+1);
        }
        //遍历sortType 取出使用的Type
        List<Type> isType = new ArrayList<>();
        for (Map.Entry<Object, Integer> entry : sortType.entrySet()){
            isType.add((Type) entry.getKey());
        }
        //给每个Type赋值该Type的blogs
        for (Type type : isType){
            type.setBlogs(blogService.getBlogByTypeId(type.getId()));
        }
        //给Type排序  blog.size（） --  id
        Collections.sort(isType);

        //获取相同Tag的个数
        for (Tag i : tagList){
            Integer count = sortTag.get(i);
            sortTag.put(i,(count == null) ? 1 :count+1);
        }
        //获取所有Tag中相同Tag的个数
        List<Tag> isTag = new ArrayList<>();
        List<Integer> tagCount = new ArrayList<>();
        for (Map.Entry<Object, Integer> entry : sortTag.entrySet()){
            isTag.add((Tag) entry.getKey());
            tagCount.add(entry.getValue());
        }
        //
        for (int i = 0;i<isTag.size();i++){
            isTag.get(i).setCount(tagCount.get(i));
        }
        Collections.sort(isTag);
        model.addAttribute("blogs",blogs);
        model.addAttribute("types",isType);
        model.addAttribute("tags",isTag);
        model.addAttribute("PB",publishedBlogs);
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlog(id);
        blog.setUser(userService.selectUserById(blog.getUser_id()));
        blog.setType(typeService.getType(blog.getType_id()));
        blog.setTags(blogService.getTagByString(blog.getTag_ids()));
        blog.setViews(blog.getViews()+1);
        //blogService.updateBlogViews(blog);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        List<Blog> newBlog = blogService.getBlogByUtAndIP();
        List<Blog> blogs = new ArrayList<>();
        for (int i=0;i<3;i++){
            blogs.add(newBlog.get(i));
        }
        model.addAttribute("newblogs",blogs);
        return "_fragments :: newBlogList";
    }
}
