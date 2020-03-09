package com.zhijing.service;

import com.zhijing.mapper.TagMapper;
import com.zhijing.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagMapper tagMapper;

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> getTopTag() {
        return tagMapper.getTopTag();
    }

    @Override
    public List<Tag> getLimitTag(Map<String, Integer> map) {
        return tagMapper.getLimitTag(map);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Tag getLastTag() {
        return tagMapper.getLastTag();
    }

    @Override
    public int updateTag(Long id, String tagname) {
        return tagMapper.updateTag(id,tagname);
    }

    @Override
    public void deletedTag(Long id) {
        tagMapper.deletedTag(id);
    }
}
