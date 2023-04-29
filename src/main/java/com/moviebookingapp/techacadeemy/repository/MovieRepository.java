package com.moviebookingapp.techacadeemy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.User;

public interface MovieRepository extends MongoRepository<Movie, String> {
	List<Movie> findAll();
	Boolean existsByMovieNameContainingIgnoreCase(String movieName);
	Movie findByMovieNameContainingIgnoreCase(String movieName);
}