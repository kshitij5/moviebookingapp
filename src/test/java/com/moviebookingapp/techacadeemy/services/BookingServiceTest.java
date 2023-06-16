package com.moviebookingapp.techacadeemy.services;

import com.moviebookingapp.techacadeemy.entities.*;
import com.moviebookingapp.techacadeemy.exception.BookingNotFoundException;
import com.moviebookingapp.techacadeemy.repository.BookingRepository;
import com.moviebookingapp.techacadeemy.repository.HallRepository;
import com.moviebookingapp.techacadeemy.repository.ShowRepository;
import com.moviebookingapp.techacadeemy.repository.TicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private HallRepository hallRepository;
    @Mock
    private ShowRepository showRepository;
    private BookingService bookingService;

    Booking booking;

    @BeforeEach
    void setUp() {
        bookingService = new BookingServiceImpl(this.bookingRepository, this.ticketRepository, this.hallRepository, this.showRepository);
        booking = new Booking(4, 400.0, EBookingstatus.BOOKED, "demo", "demo");
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addBooking() throws BookingNotFoundException {
        mock(Booking.class);
        mock(BookingRepository.class);
        mock(HallRepository.class);
        mock(ShowRepository.class);

        booking.setHallId("demoId");

        List<Seat> seats = new ArrayList<>();
        Seat seat = new Seat("demoId", 5, 3, ESeatStatus.BOOKED, 250.0);
        Movie movie = new Movie();
        Theatre theatre = new Theatre();
        Show show = new Show("demo","Morning", 15, ETicketStatus.BOOK_ASAP, movie, theatre);
        seats.add(seat);
        show.setNoAvailableTicket(10);
        Hall hall = new Hall("demoId", 5, 10, seats, show);
        Ticket ticket = new Ticket("demoId", seat, booking);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        booking.setTicket(tickets);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(hallRepository.findById(anyString())).thenReturn(Optional.of(hall));

        when(showRepository.findById(anyString())).thenReturn(Optional.of(show));

        assertThat(bookingService.addBooking(booking)).isEqualTo(booking);
    }

    @Test
    void viewBookingList() throws BookingNotFoundException {
        mock(Booking.class);
        mock(BookingRepository.class);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookings);

        assertThat(bookingService.viewBookingList()).isEqualTo(bookings);
    }

    @Test
    void updateBooking() throws BookingNotFoundException {
        mock(Booking.class);
        mock(BookingRepository.class);

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        assertThat(bookingService.updateBooking(booking)).isEqualTo(booking);
    }

    @Test
    void showAllBookingsByMovieId_found() throws BookingNotFoundException {
        mock(Booking.class);
        mock(BookingRepository.class);

        Movie movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        booking.setMovie(movie);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        when(bookingRepository.findAll()).thenReturn(bookings);

        assertThat(bookingService.showAllBookingsByMovieId(movie.getMovieId())).isEqualTo(bookings);
    }


    @Test
    void showAllBookingsByMovieId_notfound() throws BookingNotFoundException {
        mock(Booking.class);
        mock(BookingRepository.class);

        Movie movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        booking.setMovie(movie);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        when(bookingRepository.findAll()).thenReturn(bookings);

        assertThat(bookingService.showAllBookingsByMovieId("demo1")).isNotEqualTo(bookings);
    }


}