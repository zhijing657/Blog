package com.zhijing.service;

import com.zhijing.pojo.Tag;
import com.zhijing.pojo.Type;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    List<Type> getTopType();

    Type getTypeByName(String name);

    Type getLastType();

    List<Type> getAllType();

    List<Type> getLimitType(Map<String,Integer> map);

    int updateType(Long id,String typename);

    void deletedType(Long id);

}
