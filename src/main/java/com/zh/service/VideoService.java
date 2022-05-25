package com.zh.service;

import com.zh.domain.Video;

public interface VideoService {
    void insert(String vpath,String pubname);
    Video query(String vpath,String pubname);
}
