package com.moviebookingapp.techacadeemy.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.TheatreNotFoundException;
import com.moviebookingapp.techacadeemy.services.ShowService;
import com.moviebookingapp.techacadeemy.services.TheatreService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1.0/moviebooking/theatres")
public class TheatreController {
	Logger logger = LoggerFactory.getLogger(TheatreController.class);
	@Autowired
	private TheatreService theatreservice;
	@Autowired
	private ShowService showService;

	/**
	 * 
	 * @return listOfTheatres
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Theatre>> getAlltheatres() throws TheatreNotFoundException {

		logger.info("-------Theatre List Fetched---------");
		return ResponseEntity.ok(theatreservice.getAllTheatres());
	}

	/**
	 * 
	 * @param t
	 * @return inserted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PostMapping("/add")
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre t) throws TheatreNotFoundException {

		if (theatreservice.findTheatres(t.getTheatreId()) != null)
			return new ResponseEntity<>(t, HttpStatus.ALREADY_REPORTED);
		
		Theatre theatre = theatreservice.addTheatre(t);

		logger.info("-------Theatre Added Successfully---------");

		Show s1 = new Show("Morning", LocalDateTime.parse("2007-12-03T09:15:30"),
				LocalDateTime.parse("2007-12-03T12:15:30"));
		Show s2 = new Show("Afternoon", LocalDateTime.parse("2007-12-03T03:15:30"),
				LocalDateTime.parse("2007-12-03T06:15:30"));
		Show s3 = new Show("Evening", LocalDateTime.parse("2007-12-03T06:15:30"),
				LocalDateTime.parse("2007-12-03T09:15:30"));

		List<Show> shows = new ArrayList<>();
		shows.add(showService.addShow(s1, theatre.getTheatreId()));
		shows.add(showService.addShow(s2, theatre.getTheatreId()));
		shows.add(showService.addShow(s3, theatre.getTheatreId()));

//		t.setShow(shows); 

		return new ResponseEntity<>(theatreservice.updateTheatre(t), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param t
	 * @return updated theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PutMapping("/update")
	public ResponseEntity<Theatre> updateTheatre(@RequestBody Theatre t) throws TheatreNotFoundException {

		logger.info("-------Theatre Updated Successfully---------");
		return new ResponseEntity<>(theatreservice.updateTheatre(t), HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param theatreId
	 * @return theatre by theatreId
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/searchByTheatre/{theatreId}")
	public ResponseEntity<Theatre> findTheatreByTheatreId(@PathVariable String theatreId)
			throws TheatreNotFoundException {

		logger.info("-------Theatre Found with Theatre id" + theatreId + "---------");
		return ResponseEntity.ok(theatreservice.findTheatres(theatreId));
	}

	/**
	 * @param movieId
	 * @return List<Theatre>
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/searchByMovie/{movieId}")
	public ResponseEntity<Set<Theatre>> findTheatreByMovieId(@PathVariable String movieId)
			throws TheatreNotFoundException {
		return ResponseEntity.ok(theatreservice.findTheatresByMovie(movieId));
	}

	/**
	 * 
	 * @param theatreId
	 * @return deleted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@DeleteMapping("/delete/{theatreId}")
	public ResponseEntity<?> deleteTheatreById(@PathVariable String theatreId) throws TheatreNotFoundException {

		logger.info("-------Theatre Deleted with Theatre id" + theatreId + "---------");
		theatreservice.deleteTheatreById(theatreId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}