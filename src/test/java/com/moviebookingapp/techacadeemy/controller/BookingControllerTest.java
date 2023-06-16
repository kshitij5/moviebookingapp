package com.moviebookingapp.techacadeemy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.techacadeemy.entities.*;
import com.moviebookingapp.techacadeemy.exception.ApplicationExceptionHandler;
import com.moviebookingapp.techacadeemy.services.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class BookingControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private  BookingController bookingController;
    private ObjectMapper objectMapper = new ObjectMapper();

    List<Booking> bookings = new ArrayList<>();
    Booking booking;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).setControllerAdvice(new ApplicationExceptionHandler()).build();
        booking = new Booking(4, 400.0, EBookingstatus.BOOKED, "demo", "demo");
        bookings.add(booking);
    }

    @Test
    public void viewAllBookings() throws Exception {
        when(bookingService.viewBookingList()).thenReturn(bookings);
        String uri = "/api/v1.0/moviebooking/bookings/all";
        this.mockMvc.perform(get(uri)
                        .header("Authorization", "token"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addTicketBooking() throws Exception {
        when(bookingService.addBooking(any(Booking.class))).thenReturn(booking);

        String uri = "/api/v1.0/moviebooking/bookings/add";

        System.out.println(objectMapper.writeValueAsString(booking));

        MvcResult mvcResult = this.mockMvc.perform(post(uri)
                        .content(objectMapper.writeValueAsString(booking))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                    .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    public void updateTicketBooking() throws Exception {
        when(bookingService.updateBooking(any(Booking.class))).thenReturn(booking);

        String uri = "/api/v1.0/moviebooking/bookings/update";
        MvcResult mvcResult = this.mockMvc.perform(put(uri)
                        .content(objectMapper.writeValueAsString(booking))
                        .header("Authorization", "token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }
}