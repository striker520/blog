package com.fkzm.blog.service;

import com.fkzm.blog.apputils.MD5;
import com.fkzm.blog.entities.User;
import com.fkzm.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserMapper userMapper;



    @Override
    public User findUser(String username ,String password) {
        return userMapper.findUser(username, MD5.encrypt(password));
    }
}
