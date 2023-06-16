package com.moviebookingapp.techacadeemy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import com.moviebookingapp.techacadeemy.repository.UserRepository;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class MovieBookingApp {
	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApp.class, args);
	}

}