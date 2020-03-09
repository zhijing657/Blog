package com.zhijing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag implements Comparable<Tag>{
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
    private Integer count;
    public Tag(Long id, String name) {
        count = 0;
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Tag o) {
        int i = o.getCount() - this.getCount();
        if (i==0){
            return (int) (o.getId() - this.getId());
        }
        return i;
    }
}
