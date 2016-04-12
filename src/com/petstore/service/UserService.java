package com.petstore.service;

import com.petstore.entity.User;
import com.petstore.persistence.UserDao;

public class UserService {
    public boolean add(User user){
        return UserDao.getInstance().add(user);
    }

    public User getUser(String username, String pwd){
        return UserDao.getInstance().getUser(username, pwd);
    }

    public boolean update(User user){
        return UserDao.getInstance().update(user);
    }

    public boolean checkUsername(String username){
        return UserDao.getInstance().checkUsername(username);
    }
}
