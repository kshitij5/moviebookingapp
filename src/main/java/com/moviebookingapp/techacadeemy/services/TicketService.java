package com.moviebookingapp.techacadeemy.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviebookingapp.techacadeemy.entities.Ticket;
import com.moviebookingapp.techacadeemy.exception.TicketNotFoundException;

@Service
public interface TicketService {
//	public Ticket addTicket(Ticket ticket, Integer bookingId) throws TicketNotFoundException;
//	public Ticket findTicket(int ticketId);

	List<Ticket> getAllTickets() throws TicketNotFoundException;

}