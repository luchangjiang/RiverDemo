package com.river.SpringSecurityDemo.service;

import com.river.SpringSecurityDemo.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-03 16:56
 **/
@Service
public class UserService {

    /*@Autowired
    private UserDao userDao;*/

    public void save(User user) {
        user.setId(1);
//        userDao.save(user);
    }
}

