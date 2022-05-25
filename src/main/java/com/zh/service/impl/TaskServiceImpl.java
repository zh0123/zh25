package com.zh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.dao.TaskMapper;
import com.zh.domain.PubUser;
import com.zh.domain.Task;
import com.zh.domain.UserTask;
import com.zh.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;
    @Override
    public void saveTask(Task task) {
        taskMapper.insert(task);

    }

    @Override
    public Task query(String taskname) {
        return taskMapper.query(taskname);
    }

    @Override
    public List<Task> queryAll() {
        return taskMapper.queryAll();
    }

    @Override
    public List<UserTask> find(String username, String taskname) {

//        PageHelper.startPage(1,4);
        List<UserTask> list=taskMapper.find(username,taskname);
        return list;
    }

    @Override
    public PageInfo<UserTask> findPage(String username, String taskname) {
        return null;
    }

    @Override
    public void insertTask(String username, String taskname, String path) {
        taskMapper.insertTask(username,taskname,path);
    }

    @Override
    public void updateTag(String tag,String path) {
        taskMapper.updateTag(tag,path);

    }

    @Override
    public UserTask findTag(String path) {
        return taskMapper.findTag(path);
    }
}
