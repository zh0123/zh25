package com.zh.service.impl;

import com.zh.dao.PubuserMapper;
import com.zh.dao.UserMapper;
import com.zh.domain.User;
import com.zh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void saveUser(User user) {
        userMapper.insert(user);

    }

    @Override
    public User queryOne(String username, String email) {
        return userMapper.queryOne(username,email);
    }

    @Override
    public User query(String username, String password) {
        return userMapper.query(username,password);
    }
}
