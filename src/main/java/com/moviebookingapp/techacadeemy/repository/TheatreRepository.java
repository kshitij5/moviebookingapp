package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.techacadeemy.entities.Theatre;

@Repository
public interface TheatreRepository extends MongoRepository<Theatre, String> {
    Theatre findByTheatreId(String theatreId);
    Boolean existsByTheatreId(String theatreId);
}