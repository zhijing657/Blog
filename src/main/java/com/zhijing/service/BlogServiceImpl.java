package com.zhijing.service;

import com.zhijing.mapper.BlogMapper;
import com.zhijing.mapper.UserMapper;
import com.zhijing.pojo.Blog;
import com.zhijing.pojo.Tag;
import com.zhijing.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired

    TagService tagService;

    @Autowired
    TypeService typeService;

    @Autowired
    UserMapper userMapper;

    @Override
    public Blog getBlog(Long id) {
        Blog blog = blogMapper.getBlog(id);
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtml(content));
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }


    @Override
    public List<Blog> getLimitBlogs(Map<String, Integer> map) {
        return blogMapper.getLimitBlogs(map);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogMapper.saveBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int updateBlogViews(Blog blog) {
        return blogMapper.updateBlogViews(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> getBlogByCondition(Map<String, Object> map) {
        return blogMapper.getBlogByCondition(map);
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = converToList(text);
        for (Long along : longs){
            tags.add(tagService.getTag(along));
        }
        return tags;
    }

    @Override
    public List<Blog> getBlogByUtAndIP() {
        return blogMapper.getBlogByUtAndIP();
    }

    @Override
    public List<Blog> getBlogByTypeId(Long id) {
        return blogMapper.getBlogByTypeId(id);
    }

    @Override
    public Map<String, List<Blog>> archives() {
        Map<String, List<Blog>> map= new HashMap<>();
        List<String> years = blogMapper.getBlogYearGroup();
        for (String year : years){
            List<Blog> blogs = blogMapper.getBlogByYear(year);
            for (Blog blog : blogs){
                blog.setType(typeService.getType(blog.getType_id()));
                blog.setTags(getTagByString(blog.getTag_ids()));
                blog.setUser(userMapper.selectUserById(blog.getUser_id()));
            }
            map.put(year,blogs);
        }
        return map;
    }

    @Override
    public Long getBlogCount() {
        return blogMapper.getBlogCount();
    }

    private List<Long> converToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) &&ids!=null){
            String[] split = ids.split(",");
            for (int i = 0;i<split.length;i++){
                list.add(new Long(split[i]));
            }
        }
        return list;
    }
}
