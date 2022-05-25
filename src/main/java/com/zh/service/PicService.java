package com.zh.service;

import com.zh.domain.Picture;
import com.zh.domain.Task;
import com.zh.domain.TaskInfo;

import java.util.List;


public interface PicService {
    public void savePic(Picture picture);
    public void insertPathList(Integer tid, List<String> pathList);

  public List<TaskInfo> queryInfo();
    public List<Picture> queryAll(Integer tid);
}
