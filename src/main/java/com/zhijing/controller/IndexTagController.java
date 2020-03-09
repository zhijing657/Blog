package com.zhijing.controller;

import com.zhijing.pojo.Blog;
import com.zhijing.pojo.Tag;
import com.zhijing.pojo.Type;
import com.zhijing.service.BlogService;
import com.zhijing.service.TagService;
import com.zhijing.service.TypeService;
import com.zhijing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexTagController {
    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @Autowired
    TypeService typeService;

    @GetMapping("/tag/{id}")
    public String tags(@PathVariable Long id, Model model){
        List<Tag> tags1 = new ArrayList<>();
        List<Blog> blogs = blogService.getAllBlog();
        Tag myTag = new Tag();
        for (Blog blog : blogs){
            if (blog.getTag_ids() == null || blog.getTag_ids().equals("")){
                System.out.println("为空");
                continue;
            }else{
                String[] split = blog.getTag_ids().split(",");
                for (String s : split) {
                    myTag = tagService.getTag(new Long(s));
                    myTag.getBlogs().add(blog);
                    tags1.add(myTag);
                }
            }
        }

        HashMap<Object, Integer> map = new HashMap<>();
        for (Tag tag : tags1){
            Integer count = map.get(tag.getId());
            map.put(tag.getId(),(count == null)  ? 1 : count + 1);
        }
        List<Tag> tagList = new ArrayList<>();
        for (Map.Entry<Object,Integer> entry : map.entrySet()){
//            System.out.println(entry.getKey() + "=" + entry.getValue());
            Tag tag = tagService.getTag((Long) entry.getKey());
            tag.setCount(entry.getValue());
            tagList.add(tag);
        }


        //根据id 获取对应blog
        List<Blog> myBlog = new ArrayList<>();
        for (Blog blog : blogs){
            if (blog.getTag_ids().equals("")){
                continue;
            }else{
                String[] split = blog.getTag_ids().split(",");
                for (String s : split){
                    if (id.equals(new Long(s))){
                        myBlog.add(blog);
                    }
                }
            }
        }

        for (Blog blog : myBlog){
            blog.setUser(userService.selectUserById(blog.getUser_id()));
            blog.setTags(blogService.getTagByString(blog.getTag_ids()));
            blog.setType(typeService.getType(blog.getType_id()));
        }
        model.addAttribute("tags",tagList);
        model.addAttribute("blogs",myBlog);
        model.addAttribute("tagID",id);
        return "tags";
    }
}
