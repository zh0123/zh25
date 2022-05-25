package com.zh.service;

import com.zh.domain.PubUser;
import org.springframework.stereotype.Service;


public interface PubuserService {
    public void savePubuser(PubUser pubUser);
    public PubUser queryOne(String username,String email);
    public PubUser query(String username,String password);
}
