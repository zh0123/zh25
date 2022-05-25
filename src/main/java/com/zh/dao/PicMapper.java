package com.zh.dao;

import com.zh.domain.Picture;
import com.zh.domain.Project;
import com.zh.domain.Task;
import com.zh.domain.TaskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PicMapper {
    int insert(Picture picture);
    Task query(String name);
    void insertPathList(Integer tid, List<String> pathList);
    List<TaskInfo> queryInfo();
    List<Picture> queryAll(Integer tid);

}
