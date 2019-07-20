package com.tropika.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.tropika.app.persistence.StayDAO;

import entities.Stay;

@SpringBootApplication
@ComponentScan({"entities","com.tropika.app"})
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(StayDAO stayDAO) {
		return (args) -> {
			// save a couple of customers
			//repository.save(new Stay());

			// fetch all customers
			log.info("Stays found with findAll():");
			log.info("-------------------------------");
			for (Stay stay : stayDAO.findAll()) {
				log.info(stay.toString());
			}
		};
	}
}
