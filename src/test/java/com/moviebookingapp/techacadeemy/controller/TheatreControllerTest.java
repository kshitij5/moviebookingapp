package com.moviebookingapp.techacadeemy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.ApplicationExceptionHandler;
import com.moviebookingapp.techacadeemy.services.ShowService;
import com.moviebookingapp.techacadeemy.services.TheatreService;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class TheatreControllerTest {

        private MockMvc mockMvc;

        @Mock
        private TheatreService theatreService;

    @Mock
    private ShowService showService;

        @InjectMocks
        private TheatreController theatreControllerTest;

        private ObjectMapper objectMapper = new ObjectMapper();

        private Theatre theatre;

        List<Theatre> theatres = new ArrayList<>();

        @Before
        public void setUp() {
            mockMvc = MockMvcBuilders.standaloneSetup(theatreControllerTest).setControllerAdvice(new ApplicationExceptionHandler()).build();

            theatre = new Theatre("INOX", "INOX", "Kolkata");
            theatres.add(theatre);
        }
    @Test
    public void getAlltheatres() throws Exception {
        when(theatreService.getAllTheatres()).thenReturn(theatres);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/theatres/all"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void addTheatre() throws Exception {
        when(theatreService.addTheatre(any(Theatre.class))).thenReturn(theatre);
        when(showService.addShow(any(Show.class), anyString())).thenReturn(new Show());

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1.0/moviebooking/theatres/add")
                        .content(objectMapper.writeValueAsString(theatre))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void updateTheatre() throws Exception {
            when(theatreService.updateTheatre(any(Theatre.class))).thenReturn(theatre);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/v1.0/moviebooking/theatres/update")
                        .content(objectMapper.writeValueAsString(theatre))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void findTheatreByTheatreId() throws Exception {
        when(theatreService.findTheatres(anyString())).thenReturn(theatre);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/theatres/searchByTheatre/INOX"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void findTheatreByMovieId() throws Exception {
            Set<Theatre> theatres1 = new HashSet<>();
        when(theatreService.findTheatresByMovie(anyString())).thenReturn(theatres1);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/theatres/searchByMovie/demo"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void deleteTheatreById() throws Exception {

        when(theatreService.deleteTheatreById(anyString())).thenReturn(theatre);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1.0/moviebooking/theatres/delete/INOX"))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}