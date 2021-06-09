package com.calc.northmoney.service;

import com.calc.northmoney.domain.User;

public interface IUserService {
    User getUserByUsername(String username);
}
