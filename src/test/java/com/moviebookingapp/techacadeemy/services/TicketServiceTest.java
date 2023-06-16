package com.moviebookingapp.techacadeemy.services;

import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.entities.Ticket;
import com.moviebookingapp.techacadeemy.exception.TicketNotFoundException;
import com.moviebookingapp.techacadeemy.repository.BookingRepository;
import com.moviebookingapp.techacadeemy.repository.TheatreRepository;
import com.moviebookingapp.techacadeemy.repository.TicketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private BookingRepository bookingRepository;
    private TicketService ticketService;
    Ticket ticket;

    @BeforeEach
    void setUp() {
        ticketService = new TicketServiceImpl(ticketRepository, bookingRepository);
        ticket = new Ticket();
    }

    @AfterEach
    void tearDown() {

    }
    @Test
    void getAllTickets() throws TicketNotFoundException {
        mock(Ticket.class);
        mock(TicketRepository.class);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);

        when(ticketRepository.findAll()).thenReturn(tickets);

        assertThat(ticketService.getAllTickets()).isEqualTo(tickets);

    }
}