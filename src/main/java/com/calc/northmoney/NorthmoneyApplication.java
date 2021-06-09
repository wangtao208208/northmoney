package com.calc.northmoney;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@MapperScan("com.jrj.cms.mapper")
public class NorthmoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthmoneyApplication.class, args);
    }

}
