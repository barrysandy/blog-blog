package com.xgb.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages="com.xgb.blog.dao")
@EnableTransactionManagement
public class BlogApplication {
	

	public static void main(String[] args) {
		
		SpringApplication.run(BlogApplication.class, args);
		
	}
}
