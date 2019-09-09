package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,creator,description,create_time,modified,comment_count,view_count,like_count,tag)values(#{title},#{creator},#{description},#{createTime},#{modified},#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void insert(Question question);
//第一页 （currentpage-1）*5 第二页 5-10 （currentpage-1）*5
    @Select("select * from question limit #{currentPage},#{size}")
    List<Question> select(@Param("currentPage") Integer page, @Param("size") Integer size);

    @Select("select count(1) from question")
    int gettotalCount();
}
