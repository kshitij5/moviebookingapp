package com.moviebookingapp.techacadeemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.moviebookingapp.techacadeemy.repository.UserRepository;

@SpringBootApplication
public class MovieBookingApp {
    @Autowired
    UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingApp.class, args);
	}

}
