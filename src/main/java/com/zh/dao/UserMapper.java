package com.zh.dao;

import com.zh.domain.PubUser;
import com.zh.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User user);
    User queryOne(String username,String email);
    User query(String username,String password);
}
