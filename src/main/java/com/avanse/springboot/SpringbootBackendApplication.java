package com.avanse.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SpringbootBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

}
