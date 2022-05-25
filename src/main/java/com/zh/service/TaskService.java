package com.zh.service;

import com.github.pagehelper.PageInfo;
import com.zh.domain.PubUser;
import com.zh.domain.Task;
import com.zh.domain.UserTask;

import java.util.List;


public interface TaskService {
    public void saveTask(Task task);

    public Task query(String taskname);
    public List<Task> queryAll();
    public List<UserTask> find(String username, String taskname);
    public PageInfo<UserTask> findPage(String username, String taskname);
    void insertTask(String username,String taskname,String path);
    public void updateTag(String tag,String path);
   public UserTask findTag(String path);

}
