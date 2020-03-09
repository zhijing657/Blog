package com.zhijing.mapper;

import com.zhijing.pojo.Tag;
import com.zhijing.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TagMapper {
    int saveTag(Tag tag);

    List<Tag> getAllTag();

    List<Tag> getTopTag();

    Tag getTagByName(@Param("name")String name);

    List<Tag> getLimitTag(Map<String,Integer> map);

    Tag getTag(@Param("id")Long id);

    Tag getLastTag();

    int updateTag(@Param("id")Long id,@Param("tagname")String tagname);

    void deletedTag(@Param("id") Long id);
}
