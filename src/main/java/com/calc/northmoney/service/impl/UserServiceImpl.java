package com.calc.northmoney.service.impl;

import com.calc.northmoney.domain.User;
import com.calc.northmoney.mapper.UserMapper;
import com.calc.northmoney.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.getUserByName(username);
    }
}
