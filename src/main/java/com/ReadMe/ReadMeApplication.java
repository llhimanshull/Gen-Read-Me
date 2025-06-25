package com.ReadMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.ReadMe.Repository")
public class ReadMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadMeApplication.class, args);
	}

}
