package com.moviebookingapp.techacadeemy.controller;

import java.io.IOException;
import java.util.List;

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

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.repository.MovieRepository;
import com.moviebookingapp.techacadeemy.services.MoviesService;

/**
 * @author kshitij
 *
 */
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1.0/moviebooking/movies")
public class MovieController {

	Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MoviesService moviesService;
	@Autowired
	private MovieRepository movieRepository;

	/**
	 * Return's the List of Movies from the Database
	 * 
	 * @return List<Movie>
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> getAllMovies() throws MovieNotFoundException {

		logger.info("-------Movie List Fetched---------");
		return ResponseEntity.ok(moviesService.getAllMovies());
	}

	@GetMapping("/view/{movieId}")
	public ResponseEntity<Movie> getMovieById(@PathVariable String movieId) throws MovieNotFoundException {

		logger.info("-------Movie List Fetched---------");
		return ResponseEntity.ok(moviesService.getMoviesByMovieId(movieId));
	}

	/**
	 * Stores a Movie object in the Database.
	 * 
	 * @param movie
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws IOException
	 */
	@PostMapping("/add")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) throws MovieNotFoundException, IOException {
//		if (moviesService.existsById(movie.getMovieId()))
//			return new ResponseEntity<>(movie, HttpStatus.ALREADY_REPORTED);
		movie = moviesService.addMovie(movie);

		logger.info("-------Movie Added Sucfdgsdgsfgwasfg4cessfully---------");
//		logger.info("-------Movie Added Successfully---------");

		return new ResponseEntity<>(movie, HttpStatus.CREATED);
	}

	/**
	 * Updates a existing Movie record in the database.
	 * 
	 * @param movie
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PutMapping("/update")
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) throws MovieNotFoundException {

		ResponseEntity<Movie> response = null;
		if (movie == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			movie = moviesService.updateMovie(movie);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
			logger.info("-------Movie Updated Successfully---------");
		}
		return response;
	}

	/**
	 * Returns the record from the database using identifier - movieId
	 * 
	 * @param movieName
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/searchByMovieName/{movieName}")
	public ResponseEntity<List<Movie>> viewMovieByMovieName(@PathVariable String movieName)
			throws MovieNotFoundException {

		ResponseEntity<List<Movie>> response = null;
		try {
			List<Movie> movie = moviesService.getMoviesByMovieName(movieName);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
			logger.info("-------Movie With Movie Name: " + movieName + " Found---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new MovieNotFoundException("Movie with " + movieName + " id dosen't exist");
		}
		return response;
	}

	/**
	 * Displays List of movies based on the TheatreId.
	 * 
	 * @param theatreId
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/searchByTheatreId/{theatreId}")
	public List<Movie> viewMovieByTheatreId(@PathVariable String theatreId) throws MovieNotFoundException {
		logger.info("-------Movies With TheatreId " + theatreId + " Found---------");
		return moviesService.viewMovieList(theatreId);
	}

	/**
	 * Deleted the record from the database using identifier - movieId
	 * 
	 * @param movieId
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("/{moviename}/delete/{movieId}")
	public Movie deleteMovie(@PathVariable String moviename, @PathVariable String movieId)
			throws MovieNotFoundException {
		return moviesService.deleteMoviesByNameId(moviename, movieId);
	}

}