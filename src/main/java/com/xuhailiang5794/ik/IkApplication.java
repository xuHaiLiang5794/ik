package com.xuhailiang5794.ik;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan(basePackages = "com.xuhailiang5794.ik")
public class IkApplication {

	public static void main(String[] args) {
		SpringApplication.run(IkApplication.class, args);
	}
}
