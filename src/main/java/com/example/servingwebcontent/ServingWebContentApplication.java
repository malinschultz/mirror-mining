package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ServingWebContentApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ServingWebContentApplication.class, args);
	}
}
