package com.zh.dao;

import com.zh.domain.PubUser;
import com.zh.domain.Task;
import com.zh.domain.UserTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    int insert(Task task);
    Task query(String name);
    List<Task> queryAll();
    List<UserTask> find(String username,String taskname);
    void insertTask(String username,String taskname,String path);
    void updateTag(String tag,String path);
    UserTask findTag(String path);


}
