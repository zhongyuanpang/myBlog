package com.pzy.service.impl;

import com.pzy.dao.UserRepository;
import com.pzy.pojo.User;
import com.pzy.service.UserService;;
import com.pzy.util.MD5Utils;
import com.pzy.vo.UserQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public UserQuery getUser(Long id) {
        User user = userRepository.getOne(id);
        if (user == null){
            System.out.println("没有此用户");
        }
        UserQuery u = new UserQuery();
        BeanUtils.copyProperties(user, u);
        return u;
    }

}