package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moviebookingapp.techacadeemy.entities.Hall;
import com.moviebookingapp.techacadeemy.entities.Movie;

public interface HallRepository extends MongoRepository<Hall, String> {

}
