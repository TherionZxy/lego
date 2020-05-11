package com.zxyono.lego;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zxyono.lego.mapper")
public class LegoApplication {
	public static void main(String[] args) {
		SpringApplication.run(LegoApplication.class, args);
	}

}
