package com.mxl.eps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MXL
 * @date 2022/4/15
 **/
@SpringBootApplication
@MapperScan("com.mxl.eps.mapper")
public class EpsApp {
    public static void main(String[] args) {
        SpringApplication.run(EpsApp.class, args);
    }
}
