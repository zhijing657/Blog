package com.zhijing;

import com.zhijing.mapper.BlogMapper;
import com.zhijing.mapper.TypeMapper;
import com.zhijing.pojo.Blog;
import com.zhijing.pojo.Tag;
import com.zhijing.pojo.Type;
import com.zhijing.service.BlogService;
import com.zhijing.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @Test
    void contextLoads() {
        Long id = new Long(2);
        List<Blog> allBlog = blogService.getAllBlog();
        for (Blog blog : allBlog){
            if (blog.getTag_ids().equals("")){
                continue;
            }else{
                String[] split = blog.getTag_ids().split(",");
                for (String s : split){
                    Long sl = new Long(s);
                    if (id == sl){

                    }
                }
            }
        }
    }

    @Autowired
    BlogMapper blogMapper;
    @Test
    void myTest() {
        Map<String, List<Blog>> archives = blogService.archives();

    }

}
