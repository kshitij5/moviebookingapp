package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moviebookingapp.techacadeemy.entities.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, Integer>{

}
