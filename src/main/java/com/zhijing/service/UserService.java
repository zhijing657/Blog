package com.zhijing.service;

import com.zhijing.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    User checkUser(String name,String password);

    User selectUserById(Long id);
}
