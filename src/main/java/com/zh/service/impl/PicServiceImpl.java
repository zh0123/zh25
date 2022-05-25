package com.zh.service.impl;

import com.zh.dao.PicMapper;
import com.zh.dao.TaskMapper;
import com.zh.domain.Picture;
import com.zh.domain.Task;
import com.zh.domain.TaskInfo;
import com.zh.service.PicService;
import com.zh.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicServiceImpl implements PicService {

    @Autowired
    PicMapper picMapper;
    @Override
    public void savePic(Picture picture) {
        picMapper.insert(picture);

    }

    @Override
    public void insertPathList(Integer tid, List<String> pathList) {
        picMapper.insertPathList(tid,pathList);
    }

    @Override
    public List<TaskInfo> queryInfo() {
        return picMapper.queryInfo();
    }

    @Override
    public List<Picture> queryAll(Integer tid) {
        return picMapper.queryAll(tid);
    }


}
