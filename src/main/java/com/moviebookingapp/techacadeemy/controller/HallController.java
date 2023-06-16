package com.moviebookingapp.techacadeemy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.techacadeemy.entities.Hall;
import com.moviebookingapp.techacadeemy.exception.TheatreNotFoundException;
import com.moviebookingapp.techacadeemy.repository.HallRepository;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1.0/moviebooking/halls")
public class HallController {

	Logger logger = LoggerFactory.getLogger(TheatreController.class);
	@Autowired
	private HallRepository hallRepository;
	
	@GetMapping("/search/{hallId}")
	public ResponseEntity<Hall> findHallByTheatreId(@PathVariable String hallId) throws TheatreNotFoundException {

		logger.info("-------Hall Found with Hall id" + hallId + "---------");
		return ResponseEntity.ok(hallRepository.findById(hallId).get());
	}
}
