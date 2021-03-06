package com.example.demo.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private  Long createTime;
    private  Long modified;
    private Integer commentCount;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;


}
