package com.moviebookingapp.techacadeemy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Tickets;
import com.moviebookingapp.techacadeemy.payload.request.LoginRequest;
import com.moviebookingapp.techacadeemy.payload.response.MessageResponse;
import com.moviebookingapp.techacadeemy.repository.MovieRepository;
import com.moviebookingapp.techacadeemy.repository.TicketRepository;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping("/all")
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovie(@RequestBody Movie movieModel) {
		movieRepository.save(movieModel);
		return ResponseEntity.ok().body(new MessageResponse("Movie added successfully"));

	}

	@GetMapping("/movies/search/{moviename}")
	public ResponseEntity<?> searchMovies(@PathVariable String moviename) {
		if (movieRepository.existsByMovieNameContainingIgnoreCase(moviename)) {
			Movie movieModel = movieRepository
					.findByMovieNameContainingIgnoreCase(moviename);
			return ResponseEntity.ok().body(movieModel);
		} else
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Movie doesn't exist in Database"));
	}

	@PostMapping("/{moviename}/add")
	public ResponseEntity<?> addMovie(@PathVariable String moviename, @RequestBody Tickets tickets) {
		if (!movieRepository.existsByMovieNameContainingIgnoreCase(moviename))
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Movie doesn't exist in Database"));
		
		Movie movieModel = movieRepository.findByMovieNameContainingIgnoreCase(moviename);
		if(!movieModel.getTicketStatus().equals("BOOK ASAP"))
			return ResponseEntity.badRequest().body(new MessageResponse("Error: No tickets available for the movie"));
		
		if(movieModel.getNumberOfTickets() < tickets.getNoOfTickets()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Only "+ movieModel.getNumberOfTickets()+" tickets available for the movie"));
		}
		
		movieModel.setNumberOfTickets((movieModel.getNumberOfTickets()-tickets.getNoOfTickets()));
		
		movieRepository.save(movieModel);
		ticketRepository.save(tickets);
		return ResponseEntity.ok().body(new MessageResponse("Movie booked successfully"));

	}

	@PutMapping("/{moviename}/update/{ticketId}")
	public ResponseEntity<?> updateTicketStatus(@PathVariable String moviename, @PathVariable String ticket) {

		if (!movieRepository.existsByMovieNameContainingIgnoreCase(moviename))
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Movie doesn't exist in Database"));

		Movie movieModel = movieRepository.findByMovieNameContainingIgnoreCase(moviename);
		movieModel.setTicketStatus(ticket);
		

		movieRepository.save(movieModel);
		return ResponseEntity.ok().body(new MessageResponse("Ticket status updated successflly"));

	}

	@DeleteMapping("/{moviename}/delete/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable String moviename, @PathVariable String id) {
		movieRepository.deleteById(id);
		return null;
	}
}
