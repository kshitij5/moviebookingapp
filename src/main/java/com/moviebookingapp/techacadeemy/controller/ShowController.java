package com.moviebookingapp.techacadeemy.controller;

import java.util.List;

import com.moviebookingapp.techacadeemy.entities.ETicketStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.exception.ShowNotFoundException;
import com.moviebookingapp.techacadeemy.services.ShowService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1.0/moviebooking/shows")

public class ShowController {

	Logger logger = LoggerFactory.getLogger(ShowController.class);
	@Autowired
	private ShowService showService;

	/**
	 * Return's the List of Shows existing from the Database
	 * 
	 * @return List<Show>
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Show>> viewShowList() {

		logger.info("-------List Of Shows Fetched Successfully---------");
		return ResponseEntity.ok(showService.viewAllShows());
	}

	/**
	 * Stores a Show object in the Database /not in project scope
	 * 
	 * @param show
	 * @param theatreId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@PostMapping("/add")
	public ResponseEntity<Show> addShow(@RequestBody Show show, @RequestParam(required = false) String theatreId) {

		showService.addShow(show, theatreId);
		logger.info("-------Show Added Succesfully--------");
		return new ResponseEntity<>(show, HttpStatus.CREATED);
	}

	/**
	 * Removes persisted Show instance from the Database /not in project scope
	 * 
	 * @param showId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("/delete/{showId}")
	public ResponseEntity<Show> removeShow(@PathVariable String showId) {

		ResponseEntity<Show> response = null;
		Show show = showService.viewShowByShowId(showId);
		if (show == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			showService.removeShow(showId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show with ShowId " + showId + " Deleted Successfully---------");
		}
		return response;
	}

	/**
	 * Updates a existing Show record in the database /not in project scope
	 * 
	 * @param show
	 * @param theatreId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@PutMapping("/update")
	public ResponseEntity<Show> updateShow(@RequestBody Show show, @RequestParam(required = false) String theatreId) {

		ResponseEntity<Show> response = null;
		if (show == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
//			showService.updateShow(show, theatreId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show Updated Successfully---------");
		}
		return response;
	}

	/**
	 * Returns the record from the database using identifier - showId
	 * 
	 * @param showId
	 * @return Show
	 * @throws ShowNotFoundException
	 */
	@GetMapping("/searchByShowId/{showId}")
	public ResponseEntity<Show> viewShow(@PathVariable String showId) throws ShowNotFoundException {

		ResponseEntity<Show> response = null;
		try {
			Show show = showService.viewShowByShowId(showId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show with ShowId " + showId + " Found Successfully---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new ShowNotFoundException("Show with " + showId + " id dosen't exist");
		}
		return response;
	}


	@GetMapping("/searchByMovieId/{movieId}")
	public ResponseEntity<List<Show>> viewShowByMovieId(@PathVariable String movieId) throws ShowNotFoundException {

		ResponseEntity<List<Show>> response = null;
		try {
			List<Show> show = showService.viewShowByMovieId(movieId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show with movieId " + movieId + " Found Successfully---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new ShowNotFoundException("Movie with " + movieId + " id dosen't exist");
		}
		return response;
	}

	/**
	 * 
	 * @param theatreId
	 * @return Show
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/searchByTheatreId/{theatreId}")
	public ResponseEntity<List<Show>> viewShowByTheatreId(@PathVariable String theatreId) {

		logger.info("-------List Of Shows With TheatreId " + theatreId + " Fetched Successfully---------");
		return ResponseEntity.ok(showService.viewShowByTheatreId(theatreId));
	}


//	@GetMapping("/searchByHallId/{hallId}")
//	public ResponseEntity<List<Show>> viewShowByHallId(@PathVariable String hallId) {
//
//		logger.info("-------List Of Shows With TheatreId " + hallId + " Fetched Successfully---------");
//		return ResponseEntity.ok(showService.viewShowByHallId(hallId));
//	}

	/**
	 * Helper method to add movie to any show playing in any theatre
	 * 
	 * @param movieId
	 * @param showId
	 * @return
	 * @throws MovieNotFoundException
	 */
	@GetMapping("/map/{movieId}/{showId}")
	public ResponseEntity<Show> addToShow(@PathVariable String movieId,
			@PathVariable String showId) throws MovieNotFoundException {
		ResponseEntity<Show> response = null;
		Show show = new Show();
		if (movieId == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			show = showService.addMovieToShow(movieId, showId);
			response = new ResponseEntity<>(show, HttpStatus.OK);
			logger.info("-------Show mapped to Movie Successfully---------");
		}
		return response;
	}

	/**
	 *
	 * Helper method for admin to update shows booking status
	 *
	 * @param showId
	 * @param status
	 * @return
	 * @throws MovieNotFoundException
	 */
	@GetMapping("/updateTicket/{showId}/{status}")
	public Show updateTicketStatus(@PathVariable String showId, @PathVariable ETicketStatus status)
			throws MovieNotFoundException {
		return showService.updateTicketStatus(showId, status);
	}
}