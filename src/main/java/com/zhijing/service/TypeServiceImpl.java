package com.zhijing.service;

import com.zhijing.mapper.TypeMapper;
import com.zhijing.myexception.NotFoundException;
import com.zhijing.pojo.Type;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;


    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Override
    public List<Type> getTopType() {
        return typeMapper.getTopType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public Type getLastType() {
        return typeMapper.getLastType();
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public List<Type> getLimitType(Map<String, Integer> map) {
        return typeMapper.getLimitType(map);
    }

    @Override
    public int updateType(Long id, String typename) {
        Type type2 = typeMapper.getType(id);
        if (type2 == null){
            throw new NotFoundException("该标签不存在");
        }
        int type1 = typeMapper.updateType(id, typename);
        return type1;
    }

    @Override
    public void deletedType(Long id) {
        typeMapper.deletedType(id);
    }

}
