package com.moviebookingapp.techacadeemy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.techacadeemy.entities.Booking;
import com.moviebookingapp.techacadeemy.entities.Ticket;
import com.moviebookingapp.techacadeemy.exception.TicketNotFoundException;
import com.moviebookingapp.techacadeemy.repository.BookingRepository;
import com.moviebookingapp.techacadeemy.repository.SeatRepository;
import com.moviebookingapp.techacadeemy.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private BookingRepository bookingRepository;

	public TicketServiceImpl(TicketRepository ticketRepository, BookingRepository bookingRepository) {
		this.ticketRepository = ticketRepository;
		this.bookingRepository = bookingRepository;
	}

	@Override
	public List<Ticket> getAllTickets() throws TicketNotFoundException {
		List<Ticket> ti = ticketRepository.findAll();
		if (ti.size() == 0)
			throw new TicketNotFoundException("No tickets are avaliable");
		return ti;
	}

//	@Override
//	public Ticket addTicket(Ticket ticket, Integer transactionId) throws TicketNotFoundException {
//		Booking booking = new Booking();
//		if (transactionId != null) {
//			booking = bookingRepository.findById(transactionId).get();
//			booking.setTransactionStatus("Completed");
//			ticket.setBooking(booking);
//		}
//		ticketRepository.save(ticket);
//		/*
//		 * for(Seat s:ticket.getSeats()) { s.setTickett(ticket);
//		 * seatRepository.saveAndFlush(s); }
//		 */
//		return ticket;
//	}

//	@Override
//	public Ticket findTicket(int ticketId) {
//		return ticketRepository.findById(ticketId).get();
//	}

}