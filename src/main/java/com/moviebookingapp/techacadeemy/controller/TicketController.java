package com.moviebookingapp.techacadeemy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Ticket;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.exception.TicketNotFoundException;
import com.moviebookingapp.techacadeemy.services.MoviesService;
import com.moviebookingapp.techacadeemy.services.TicketService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1.0/moviebooking/tickets")
public class TicketController {

	Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private TicketService ticketService;

	@Autowired
	private MoviesService moviesService;

	/**
	 * Return's the List of Ticket from the Database
	 * 
	 * @return List<Ticket>
	 * @throws TicketNotFoundException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Ticket>> getAllTickets() throws TicketNotFoundException {

		logger.info("-------Ticket List Fetched---------");
		return ResponseEntity.ok(ticketService.getAllTickets());
	}

//	@PostMapping("/add")
//	public ResponseEntity<?> addTicket(@PathVariable String moviename, @RequestBody Ticket tickets)
//			throws MovieNotFoundException {
//		return moviesService.bookTicketByMovieName(moviename, tickets);
//	}

}