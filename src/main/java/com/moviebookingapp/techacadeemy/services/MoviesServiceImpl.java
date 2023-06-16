package com.moviebookingapp.techacadeemy.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.repository.MovieRepository;
import com.moviebookingapp.techacadeemy.repository.ShowRepository;

@Service
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ShowRepository showRepository;

	public MoviesServiceImpl(MovieRepository movieRepository, ShowRepository showRepository) {
		this.movieRepository = movieRepository;
		this.showRepository = showRepository;
	}
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public List<Movie> getMoviesByMovieName(String moviename) {
		List<Movie> movies = movieRepository.findByMovieNameContainingIgnoreCase(moviename);
		return movies;
	}

	@Override
	public Movie deleteMoviesByNameId(String moviename, String movieId) {

		Movie movie = movieRepository.findById(movieId).get();
		List<Show> shows = showRepository.findAll();
		for (Show show : shows) {
			if (show.getMovie() != null && show.getMovie().getMovieId() == movieId) {
				show.setMovie(null);
			}
		}
		movieRepository.delete(movie);
		return movie;
	}

	@Override
	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Movie getMoviesByMovieId(String movieId) {
		return movieRepository.findByMovieId(movieId);
	}

	@Override
	public Movie updateMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public List<Movie> viewMovieList(String theatreId) {

		List<Movie> movies = new ArrayList<>();
		List<Show> shows = showRepository.findAll();
		Set<String> showIds = new HashSet<>();
		for (Show s : shows) {
			if (s.getTheatre()!=null && Objects.equals(s.getTheatre().getTheatreId(), theatreId)) {
				showIds.add(s.getShowId());
			}
		}
		for (String id : showIds) {
			movies.add(showRepository.findById(id).get().getMovie());
		}

		return movies;
	}

//	@Override
//	public ResponseEntity<?> bookTicketByMovieName(String moviename, Ticket tickets) {
//		if (!movieRepository.existsByMovieNameContainingIgnoreCase(moviename))
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Movie doesn't exist in Database"));
//
//		Movie movieModel = movieRepository.findByMovieNameContainingIgnoreCase(moviename);
//		
//		if (!movieModel.getTicketStatus().equals("BOOK ASAP"))
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: No tickets available for the movie"));
//
//		if (movieModel.getNumberOfTickets() < tickets.getNoOfTickets()) {
//			return ResponseEntity.badRequest().body(new MessageResponse(
//					"Error: Only " + movieModel.getNumberOfTickets() + " tickets available for the movie"));
//		}
//
//		movieModel.setNumberOfTickets((movieModel.getNumberOfTickets() - tickets.getNoOfTickets()));
//
//		movieRepository.save(movieModel);
//		ticketRepository.save(tickets);
//		return ResponseEntity.ok().body(new MessageResponse("Movie booked successfully"));
//
//	}

}