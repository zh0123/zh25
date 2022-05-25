package com.zh.dao;


import com.zh.domain.PubUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PubuserMapper {



    int insert(PubUser pubUser);
    PubUser queryOne(String username,String email);
    PubUser query(String username,String password);


}
