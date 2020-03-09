package com.zhijing.service;

import com.zhijing.mapper.UserMapper;
import com.zhijing.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(String name, String password) {
        User user = userMapper.checkUser(name, password);
        return user;
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }
}
