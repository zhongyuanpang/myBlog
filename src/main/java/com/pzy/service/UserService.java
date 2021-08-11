package com.pzy.service;

import com.pzy.pojo.User;
import com.pzy.vo.UserQuery;

public interface UserService {
    User checkUser(String username, String password);
    UserQuery getUser(Long id);
}