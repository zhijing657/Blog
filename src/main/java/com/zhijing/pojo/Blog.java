package com.zhijing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog{
//    private long id;
//    private String title;//标题
//    private String content;//内容
//    private String firstPicture;//首图
//    private String flag;//标签
//    private Integer views;//浏览次数
//    private boolean appreciation;//赞赏
//    private boolean shareStatement;//版权
//    private boolean published;//发布
//    private boolean recommend;//评论
//    private Date createTime;//创建时间
//    private Date updateTime;//修改时间
//    private Type type;//类型
//    private List<Tag> tags;//标签
//    private User user;//用户

    private Long id;
    private String title;//标题
    private String flag;
    private String content;//内容
    private String first_picture;//首图
    private Integer views;//浏览次数
    private boolean appreciation;//赞赏
    private boolean share_statement;//版权
    private boolean commentabled;//开启评论
    private boolean published;//发布
    private boolean recommend;//推荐
    private Date create_time;//创建时间
    private Date update_time;//修改时间
    private Long type_id;
    private Type type;//类型
    private String tag_ids;//标签
    private List<Tag> tags = new ArrayList<>();//标签
    private Long user_id;
    private User user;//用户
    private List<Comment> comments = new ArrayList<>();//评论

    private String description;

}
