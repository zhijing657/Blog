package com.zhijing.mapper;

import com.zhijing.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TypeMapper {
    int saveType(Type type);

    List<Type> getAllType();

    List<Type> getTopType();

    Type getTypeByName(@Param("name")String name);

    List<Type> getLimitType(Map<String,Integer> map);

    Type getType(@Param("id")Long id);

    Type getLastType();

    int updateType(@Param("id")Long id,@Param("typename")String typename);

    void deletedType(@Param("id") Long id);


}
