package com.tropika.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EntityScan("entities")
@Slf4j
public class Application {

	public static void main(String[] args) {
		log.info("Starting Application");
		SpringApplication.run(Application.class, args);
	}
}
