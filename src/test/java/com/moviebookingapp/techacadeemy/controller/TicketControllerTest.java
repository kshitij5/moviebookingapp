package com.moviebookingapp.techacadeemy.controller;

import com.moviebookingapp.techacadeemy.entities.Ticket;
import com.moviebookingapp.techacadeemy.exception.ApplicationExceptionHandler;
import com.moviebookingapp.techacadeemy.services.TicketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class TicketControllerTest {
    private MockMvc mockMvc;

    @Mock
    private TicketServiceImpl ticketService;

    @InjectMocks
    private TicketController ticketController;

    private Ticket ticket;

    List<Ticket> tickets = new ArrayList<>();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).setControllerAdvice(new ApplicationExceptionHandler()).build();
        ticket = new Ticket();
        tickets.add(ticket);
    }

    @Test
    public void getAllTickets() throws Exception {
        when(ticketService.getAllTickets()).thenReturn(tickets);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/tickets/all"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
}