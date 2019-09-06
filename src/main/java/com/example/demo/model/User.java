package com.example.demo.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private  String name;
    private  String accountId;
    private  String token;
    private  Long createTime;
    private  Long modified;
    private  String avatar_url;


}
