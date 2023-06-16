package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.moviebookingapp.techacadeemy.entities.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

}
