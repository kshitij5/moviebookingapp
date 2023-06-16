package com.moviebookingapp.techacadeemy.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.moviebookingapp.techacadeemy.entities.Booking;
import com.moviebookingapp.techacadeemy.exception.AccessForbiddenException;
import com.moviebookingapp.techacadeemy.exception.BookingNotFoundException;
import com.moviebookingapp.techacadeemy.services.BookingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/moviebooking/bookings")
public class BookingController {

	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	/**
	 * 
	 * 
	 * @return bookingList
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Booking>> viewAllBookings() throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Logging");
		 */
		logger.info("-------List Of Bookings Fetched Successfully---------");
		return ResponseEntity.ok(bookingService.viewBookingList());
	}

	@GetMapping("/view/{bookingId}")
	public ResponseEntity<Booking> viewBookingsById(@RequestParam String bookingId) throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Logging");
		 */
		logger.info("-------List Of Bookings Fetched Successfully---------");
		return ResponseEntity.ok(bookingService.viewBookingById(bookingId));
	}

	/**
	 * 
	 * @param t
	 * @return booking details
	 * @throws AccessForbiddenException
	 * @throws BookingNotFoundException
	 */

	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> addTicketBooking(@RequestBody Booking t)
			throws AccessForbiddenException, BookingNotFoundException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Login");
		 */
		 logger.info("-------Booking For Customer " + t.getUserId() + "Added---------");
		return ResponseEntity.ok(bookingService.addBooking(t));
	}

	/**
	 * ADMIN updates booking status
	 *
	 * @param t
	 * @return updated booking details
	 * @throws BookingNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booking> updateTicketBooking(@RequestBody Booking t)
			throws BookingNotFoundException, AccessForbiddenException {
		/*
		 * if (!loginController.loginStatus()) throw new
		 * AccessForbiddenException("Not Logged In/Invalid Loggin");
		 */
		logger.info("-------Booking Updated Successfully---------");
		return ResponseEntity.ok(bookingService.updateBooking(t));
	}

}