package com.zh.dao;

import com.zh.domain.Video;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoMapper {
    void insert(String vpath,String pubname);
    Video query(String vpath,String pubname);
}
