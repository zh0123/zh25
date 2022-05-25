package com.zh.service.impl;

import com.zh.dao.VideoMapper;
import com.zh.domain.Video;
import com.zh.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;
    @Override
    public void insert(String vpath, String pubname) {
        videoMapper.insert(vpath,pubname);

    }

    @Override
    public Video query(String vpath,String pubname) {
        return videoMapper.query(vpath,pubname);
    }
}
