package com.zhijing.mapper;

import com.zhijing.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User checkUser(@Param("username") String username,
                   @Param("password") String password);

    User selectUserById(@Param("id") Long id);
}
