package com.fkzm.blog.service;

import com.fkzm.blog.entities.User;

public interface AdminService {


    User findUser(String username,String password);
}
