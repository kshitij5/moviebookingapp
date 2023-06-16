package com.moviebookingapp.techacadeemy.services;

import java.util.List;
import java.util.Set;

import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.TheatreNotFoundException;

public interface TheatreService {
	public List<Theatre> getAllTheatres() throws TheatreNotFoundException;

	public Theatre findTheatres(String theatreId);

	public Theatre addTheatre(Theatre t) throws TheatreNotFoundException;

	public Theatre updateTheatre(Theatre t);
	
	public Set<Theatre> findTheatresByMovie(String movieId) throws TheatreNotFoundException;

	Object deleteTheatreById(String theatreId);

}