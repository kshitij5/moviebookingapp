package com.moviebookingapp.techacadeemy.services;

import java.util.List;

import com.moviebookingapp.techacadeemy.entities.Booking;
import com.moviebookingapp.techacadeemy.exception.BookingNotFoundException;

public interface BookingService {
	public Booking addBooking(Booking booking) throws BookingNotFoundException;

	public List<Booking> viewBookingList() throws BookingNotFoundException;


	public Booking viewBookingById(String bookingId) throws BookingNotFoundException;

	public Booking updateBooking(Booking booking) throws BookingNotFoundException;

	public List<Booking> showAllBookingsByMovieId(String movieid) throws BookingNotFoundException;

}