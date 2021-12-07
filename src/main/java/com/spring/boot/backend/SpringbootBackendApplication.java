package com.spring.boot.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
	
	/*
	 * public static Properties getProperties() { Properties props = new
	 * Properties(); props.setProperty("spring.config.location",
	 * "/home/config_directory/"); props.setProperty("spring.config.name",
	 * "apiapplication"); return props;
	 * 
	 * }
	 */

}
