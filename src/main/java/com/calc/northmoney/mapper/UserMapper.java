package com.calc.northmoney.mapper;

import com.calc.northmoney.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where username=#{userName}")
    User getUserByName(String userName);
}
