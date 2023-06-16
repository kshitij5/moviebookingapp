package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.techacadeemy.entities.Seat;

@Repository
public interface SeatRepository extends MongoRepository<Seat, String> {

}
