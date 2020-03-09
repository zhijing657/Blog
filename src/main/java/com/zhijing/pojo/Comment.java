package com.zhijing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
}
