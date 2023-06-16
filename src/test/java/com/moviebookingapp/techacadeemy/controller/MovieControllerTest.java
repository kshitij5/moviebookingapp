package com.moviebookingapp.techacadeemy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.exception.ApplicationExceptionHandler;
import com.moviebookingapp.techacadeemy.services.MoviesServiceImpl;
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
public class MovieControllerTest {

	private MockMvc mockMvc;

	@Mock
	private MoviesServiceImpl movieService;

	@InjectMocks
	private MovieController movieController;

	private ObjectMapper objectMapper = new ObjectMapper();

	private Movie movie;

    List<Movie> movies = new ArrayList<>();
	
    @Before
	public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).setControllerAdvice(new ApplicationExceptionHandler()).build();
        movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        movies.add(movie);
    }

	@Test
	public void getAllMovies() throws Exception {
		when(movieService.getAllMovies()).thenReturn(movies);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/movies/all"))
				.andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	public void getMovieById() throws Exception {
		when(movieService.getMoviesByMovieId(anyString())).thenReturn(movie);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/movies/view/demo"))
				.andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void addMovie() throws Exception {
		when(movieService.addMovie(any(Movie.class))).thenReturn(movie);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/api/v1.0/moviebooking/movies/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(movie)))
				.andReturn();
		assertEquals(201, result.getResponse().getStatus());
	}

	@Test
	public void updateMovie() throws Exception {
		when(movieService.updateMovie(any(Movie.class))).thenReturn(movie);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/api/v1.0/moviebooking/movies/update")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(movie)))
				.andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void viewMovieByMovieName() throws Exception {
		when(movieService.getMoviesByMovieName(anyString())).thenReturn(movies);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/movies/searchByMovieName/Kisi Ka Bhai Kisi Ka Jaan"))
				.andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void viewMovieByTheatreId() throws Exception {
//		when(movieService.getMoviesByMovieId(anyString())).thenReturn(movie);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/v1.0/moviebooking/movies/searchByMovieName/KKBKKJ"))
				.andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	@Test
	public void deleteMovie() throws Exception {
		when(movieService.deleteMoviesByNameId(anyString(), anyString())).thenReturn(movie);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/v1.0/moviebooking/movies/Kisi Ka Bhai Kisi Ka Jaan/delete/KKBKKJ"))
				.andReturn();

		assertEquals(200, result.getResponse().getStatus());
	}
}