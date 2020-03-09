package com.zhijing.mapper;

import com.zhijing.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BlogMapper {

    Blog getBlog(@Param("id") Long id);

    List<Blog> getAllBlog();

    List<Blog> getLimitBlogs(Map<String,Integer> map);

    List<Blog> getBlogByCondition(Map<String,Object> map);

    int saveBlog(Blog blog);

    int updateBlogViews(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(@Param("id")Long id);

    List<Blog> getBlogByUtAndIP();

    List<Blog> getBlogByTypeId(@PathParam("type_id") Long id);

    List<String> getBlogYearGroup();

    List<Blog> getBlogByYear(@PathParam("year") String year);

    Long getBlogCount();
}
