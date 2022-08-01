package com.project.toystore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"com.project.toystore.*"})
public class ToyStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyStoreApplication.class, args);
	}
}
