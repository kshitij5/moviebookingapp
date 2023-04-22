package com.moviebookingapp.techacadeemy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {
	@GetMapping("/home")
	public String home() {
		return "working";
		
	}
	
	@PostMapping("/register")
    public ResponseEntity<?> register() {
		return null;
		
	}
	
	@GetMapping("/login")
    public ResponseEntity<?> login()  {
		return null;
	}

	@GetMapping("/forgot")
    public ResponseEntity<?> forgot()  {
		return null;
	}

	@GetMapping("/all")
    public ResponseEntity<?> all()  {
		return null;
	}

	@GetMapping("/movies/search")
    public ResponseEntity<?> searchMovies()  {
		return null;
	}
	

	@PostMapping("/add")
    public ResponseEntity<?> addMovie() {
		return null;
		
	}

	@PutMapping("/update")
    public ResponseEntity<?> updateTicketStatus() {
		return null;
		
	}

	@DeleteMapping("/update")
    public ResponseEntity<?> deleteMovie() {
		return null;
		
	}
	
}
