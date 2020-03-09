package com.zhijing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Comparable<Type>{
    private long id;
    private String name;
    private List<Blog> blogs;
    private Integer count;

    public Type(long id, String name) {
        count = 0;
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Type o) {
        int i = o.getBlogs().size() - this.getBlogs().size();
        if (i==0){
            return (int) (o.getId() - this.getId());
        }
        return i;
    }
}
