package com.zy.pagehelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.zy.pagehelper.dao")
public class TestPageHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestPageHelperApplication.class, args);
	}

}
