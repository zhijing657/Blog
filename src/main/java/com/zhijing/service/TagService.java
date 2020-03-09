package com.zhijing.service;

import com.zhijing.pojo.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TagService {
    int saveTag(Tag tag);

    List<Tag> getAllTag();

    Tag getTagByName(String name);

    List<Tag> getTopTag();

    List<Tag> getLimitTag(Map<String,Integer> map);

    Tag getTag(Long id);

    Tag getLastTag();

    int updateTag(Long id,String tagname);

    void deletedTag(Long id);
}
