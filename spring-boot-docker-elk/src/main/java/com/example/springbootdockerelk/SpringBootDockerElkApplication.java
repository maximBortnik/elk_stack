package com.example.springbootdockerelk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class SpringBootDockerElkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerElkApplication.class, args);
	}

}
