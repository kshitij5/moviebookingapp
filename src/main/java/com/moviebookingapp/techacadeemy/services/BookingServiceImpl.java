package com.moviebookingapp.techacadeemy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import com.moviebookingapp.techacadeemy.config.KafkaSender;
import com.moviebookingapp.techacadeemy.entities.*;
import com.moviebookingapp.techacadeemy.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.techacadeemy.exception.BookingNotFoundException;
import com.moviebookingapp.techacadeemy.repository.BookingRepository;
import com.moviebookingapp.techacadeemy.repository.HallRepository;
import com.moviebookingapp.techacadeemy.repository.TicketRepository;

@Service
public class BookingServiceImpl implements BookingService {

//	@Autowired
//	private ShowRepository showRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	HallRepository hallRepository;

//	@Autowired
//	KafkaSender kafkaSender;

    public BookingServiceImpl(BookingRepository bookingRepository, TicketRepository ticketRepository, HallRepository hallRepository, ShowRepository showRepository) {
		this.bookingRepository = bookingRepository;
		this.ticketRepository = ticketRepository;
		this.hallRepository = hallRepository;
//		this.showRepository = showRepository;
    }

    @Override
	public List<Booking> viewBookingList() throws BookingNotFoundException {
		List<Booking> bk = bookingRepository.findAll();
		/*
		 * if (bk.size() == 0) throw new BookingNotFoundException("No bookings found");
		 */
		return bk;
	}


	/**
	 * @param bookingId
	 * @return
	 * @throws BookingNotFoundException
	 */
	@Override
	public Booking viewBookingById(String bookingId) throws BookingNotFoundException {
		return bookingRepository.findById(bookingId).get();
	}

	@Override
	public Booking addBooking(Booking booking) throws BookingNotFoundException {
//		ticketRepository.saveAll(booking.getTicket());
//
//		Hall hall = hallRepository.findById(booking.getHallId()).get();
//		String showId = hall.getShow().getShowId();
//
//		Show show = showRepository.findById(showId).get();
//		// we have prehandled such that request ticket will never be more than available ticket
//		show.setNoAvailableTicket(show.getNoAvailableTicket()-booking.getNoOfTickets());
//
////		kafkaSender.sendMessage(show, "totalTicketAvailable");
//		if(show.getNoAvailableTicket()==0) {
//			show.setTicketStatus(ETicketStatus.SOLD_OUT);
//		}
////		kafkaSender.sendMessage(show, "updateTicketStatus");
//		List<Seat> hallSeats = hall.getSeats();
//		List<Seat> bookedSeat = new ArrayList<>();
//
//		for (Ticket ticket : booking.getTicket()) {
//			bookedSeat.add(ticket.getSeat());
////			kafkaSender.sendMessage(ticket, "totalTicketBooked");
//		}
//
//		List<Seat> seatNew = new ArrayList<>();
//
//		for (Seat seat : hallSeats) {
//			if (bookedSeat.contains(seat))
//				seat.setStatus(ESeatStatus.BOOKED);
//
//			seatNew.add(seat);
//		}
//
//		hall.setSeats(seatNew);
//		hallRepository.save(hall);
		return bookingRepository.save(booking);
	}

	@Override
	public Booking updateBooking(Booking booking) throws BookingNotFoundException {
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> showAllBookingsByMovieId(String movieid) throws BookingNotFoundException {
		List<Booking> bk = new ArrayList<>();
		List<Booking> bookings = bookingRepository.findAll();
		for (Booking booking : bookings) {
			if (Objects.equals(booking.getMovie().getMovieId(), movieid)) {
				bk.add(booking);
			}
		}
		return bk;
	}

}