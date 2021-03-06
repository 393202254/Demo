package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where token=#{token}")
     User findByToken(@Param("token") String token) ;
    @Insert("insert into user(name,account_id,token,create_time,modified,avatar_url)values(#{name},#{accountId},#{token},#{createTime},#{modified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where id=#{id}")
    User findByID(Integer id);
}
