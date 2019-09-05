package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
  @Insert("insert into question(title,creator,description,create_time,modified,comment_count,view_count,like_count,tag)values(#{title},#{creator},#{description},#{createTime},#{modified},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);
}
