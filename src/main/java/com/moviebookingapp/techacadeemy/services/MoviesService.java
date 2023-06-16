package com.moviebookingapp.techacadeemy.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;

public interface MoviesService {
	public List<Movie> getAllMovies();

	public Movie addMovie(Movie movieModel) throws MovieNotFoundException;

	public Movie updateMovie(Movie movie) throws MovieNotFoundException;

	public List<Movie> getMoviesByMovieName(String moviename) throws MovieNotFoundException;

	public List<Movie> viewMovieList(String theatreid) throws MovieNotFoundException;

	public Movie getMoviesByMovieId(String movieId) throws MovieNotFoundException;

	public Movie deleteMoviesByNameId(String moviename, String id) throws MovieNotFoundException;

}