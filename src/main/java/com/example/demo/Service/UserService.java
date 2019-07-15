package com.example.demo.Service;

import com.example.demo.Bean.User;

/*
用户接口
 */
public interface UserService {
    User getByName(String username,boolean s);
    void update(User user,boolean s,String userId);
}
