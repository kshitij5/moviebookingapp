package com.moviebookingapp.techacadeemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.moviebookingapp.techacadeemy.entities.Tickets;

public interface TicketRepository extends MongoRepository<Tickets, String>{

}
