package com.zhijing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String avatar;
    private String manager;
    private Integer type;
    private Date createTime;
    private Date updateTime;
    private List<Blog> blogs;
}
