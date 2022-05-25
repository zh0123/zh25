package com.zh.service.impl;

import com.zh.dao.PubuserMapper;
import com.zh.domain.PubUser;
import com.zh.service.PubuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(readOnly = true)
public class PubuserServiceImpl implements PubuserService {
    @Autowired
    private PubuserMapper pubuserMapper;
    @Override
    public void savePubuser(PubUser pubUser) {
        pubuserMapper.insert(pubUser);
    }

    @Override
    public PubUser queryOne(String username,String email) {
        return pubuserMapper.queryOne(username,email);
    }

    @Override
    public PubUser query(String username, String password) {
        return pubuserMapper.query(username,password);
    }
}
