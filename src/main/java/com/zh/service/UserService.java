package com.zh.service;

import com.zh.domain.PubUser;
import com.zh.domain.User;

public interface UserService {
    public void saveUser(User user);
    public User queryOne(String username,String email);
    public User query(String username,String password);

}
