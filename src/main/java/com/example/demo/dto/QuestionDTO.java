package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private User user;

}
