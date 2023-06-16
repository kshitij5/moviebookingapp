package com.moviebookingapp.techacadeemy.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.techacadeemy.entities.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        String movies = RawData.getMovieList();
        ObjectMapper mapper = new ObjectMapper();
        List<Movie> movieList = Arrays.asList(mapper.readValue(movies, Movie[].class));
        movieRepository.saveAll(movieList);
    }

    @AfterEach
    void tearDown() {
//        movieRepository.deleteAll();
    }

    @Test
    void findByMovieNameContainingIgnoreCase() {
        List<Movie> result = movieRepository.findByMovieNameContainingIgnoreCase("Kisi Ka Bhai Kisi Ka Jaan");
        Movie expected = new Movie("KKBKKJ","Kisi Ka Bhai Kisi Ka Jaan","\"https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/kisi-ka-bhai-kisi-ki-jaan-et00339118-1681730385.jpg","2h 25m","Actiion, Comedy, Drama","Hindi","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",6.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        assertThat(result.get(0).getMovieId(), is(expected.getMovieId()));
    }

    @Test
    void findByMovieId() {
        Movie result = movieRepository.findByMovieId("KKBKKJ");
        Movie expected = new Movie("KKBKKJ","Kisi Ka Bhai Kisi Ka Jaan","\"https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/kisi-ka-bhai-kisi-ki-jaan-et00339118-1681730385.jpg","2h 25m","Actiion, Comedy, Drama","Hindi","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet metus sed finibus lacinia. Ut orci libero, porttitor sit amet imperdiet id, finibus ut ex",6.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        assertThat(result.getMovieName(), is(expected.getMovieName()));
    }
}