package com.moviebookingapp.techacadeemy.repository;

import java.util.List;

import com.moviebookingapp.techacadeemy.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moviebookingapp.techacadeemy.entities.Show;

public interface ShowRepository extends MongoRepository<Show, String> {
	List<Show> getAllByTheatre(String id);

	List<Show> getAllByMovie(Movie movies);

    Show findByShowId(String showid);
}