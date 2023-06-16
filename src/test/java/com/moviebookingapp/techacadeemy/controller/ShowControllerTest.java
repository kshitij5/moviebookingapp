package com.moviebookingapp.techacadeemy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.techacadeemy.entities.ETicketStatus;
import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.ApplicationExceptionHandler;
import com.moviebookingapp.techacadeemy.services.MoviesServiceImpl;
import com.moviebookingapp.techacadeemy.services.ShowServiceImpl;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class ShowControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ShowServiceImpl showService;

    @InjectMocks
    private ShowController showController;

    private ObjectMapper objectMapper = new ObjectMapper();

    Show show;
    Theatre theatre;Movie movie;
    List<Show> showList = new ArrayList<>();;

    List<Movie> movies = new ArrayList<>();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(showController).setControllerAdvice(new ApplicationExceptionHandler()).build();

        theatre = new Theatre("INOX", "INOX", "Kolkata");
        movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        show = new Show("demo","Morning", 15, ETicketStatus.BOOK_ASAP, movie, theatre);
        showList.add(show);
    }


    @Test
    public void viewShowList() throws Exception {
        when(showService.viewAllShows()).thenReturn(showList);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/shows/all"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void addShow() throws Exception {
//        when(showService.addShow(any(Show.class), anyString())).thenReturn(show);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1.0/moviebooking/shows/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void removeShow() throws Exception {
        when(showService.viewShowByShowId(anyString())).thenReturn(show);
        when(showService.removeShow(anyString())).thenReturn(show);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1.0/moviebooking/shows/delete/demo"))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void updateShow() throws Exception {
//        when(showService.updateShow(any(Show.class), anyString())).thenReturn(show);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/v1.0/moviebooking/shows/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void viewShow() throws Exception {

        when(showService.viewShowByShowId(anyString())).thenReturn(show);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/shows/searchByShowId/demo"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void viewShowByTheatreId() throws Exception {
        when(showService.viewShowByTheatreId(anyString())).thenReturn(showList);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/shows/searchByTheatreId/INOX"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void viewShowByMovieId() throws Exception {
        when(showService.viewShowByMovieId(anyString())).thenReturn(showList);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/shows/searchByMovieId/demo"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
}