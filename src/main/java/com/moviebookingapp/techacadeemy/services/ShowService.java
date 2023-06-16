package com.moviebookingapp.techacadeemy.services;

import java.util.List;

import com.moviebookingapp.techacadeemy.entities.ETicketStatus;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;

public interface ShowService {

	public Show addShow(Show show, String theatreId);

	
	public Show updateShow(Show show, String theatreId);

	public Show removeShow(String showid);

	public Show viewShowByShowId(String showid);
	
	List<Show> viewShowByTheatreId(String theatreId);

	public List<Show> viewAllShows();


	public Show addMovieToShow(String movie, String showId) throws MovieNotFoundException;

    Show updateTicketStatus(String showId, ETicketStatus movie);

	public List<Show> viewShowByMovieId(String movieId) throws MovieNotFoundException;

//	public List<Show> viewShowByHallId(String hallId);
}