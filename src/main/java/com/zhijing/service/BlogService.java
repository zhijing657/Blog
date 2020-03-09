package com.zhijing.service;

import com.zhijing.pojo.Blog;
import com.zhijing.pojo.Tag;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    List<Blog> getAllBlog();

    List<Blog> getLimitBlogs(Map<String,Integer> map);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int updateBlogViews(Blog blog);

    int deleteBlog(Long id);

    List<Blog> getBlogByCondition(Map<String,Object> map);

    List<Tag> getTagByString(String text);

    List<Blog> getBlogByUtAndIP();

    List<Blog> getBlogByTypeId(Long id);

    Map<String,List<Blog>> archives();

    Long getBlogCount();
}
