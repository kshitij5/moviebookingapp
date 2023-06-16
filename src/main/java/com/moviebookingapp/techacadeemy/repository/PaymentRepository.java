package com.moviebookingapp.techacadeemy.repository;

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository  extends MongoRepository<Payment, String> {
}