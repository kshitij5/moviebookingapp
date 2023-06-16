package com.moviebookingapp.techacadeemy.services;

import com.moviebookingapp.techacadeemy.entities.ETicketStatus;
import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.repository.MovieRepository;
import com.moviebookingapp.techacadeemy.repository.ShowRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ShowRepository showRepository;
    private MoviesService moviesService;

    Movie movie;

    @BeforeEach
    void setUp() {
        moviesService = new MoviesServiceImpl(movieRepository, showRepository);
        movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
    }

    @Test
    void getAllMovies() {
        mock(Movie.class);
        mock(MovieRepository.class);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);

        when(movieRepository.findAll()).thenReturn(movies);

        assertThat(moviesService.getAllMovies()).isEqualTo(movies);
    }

    @Test
    void addMovie() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        assertThat(moviesService.addMovie(movie)).isEqualTo(movie);
    }

    @Test
    void updateMovie() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        assertThat(moviesService.updateMovie(movie)).isEqualTo(movie);
    }

    @Test
    void getMoviesByMovieName_found() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);

        when(movieRepository.findByMovieNameContainingIgnoreCase(anyString())).thenReturn(movies);

        assertThat(moviesService.getMoviesByMovieName(movie.getMovieName())).isEqualTo(movies);
    }


    @Test
    void getMoviesByMovieName_notfound() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);

        when(movieRepository.findByMovieNameContainingIgnoreCase(anyString())).thenReturn(null);

        assertNull(moviesService.getMoviesByMovieName("demo"));
    }

    @Test
    void viewMovieList() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);
        mock(ShowRepository.class);
        Theatre theatre = new Theatre("INOX", "INOX", "Kolkata");
        Show show = new Show("demo","Morning", 15, ETicketStatus.BOOK_ASAP, movie, theatre);
        List<Show> shows = new ArrayList<>();
        shows.add(show);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        when(showRepository.findAll()).thenReturn(shows);
        when(showRepository.findById(anyString())).thenReturn(Optional.of(show));

        assertThat(moviesService.viewMovieList(theatre.getTheatreId())).isEqualTo(movies);
    }

    @Test
    void getMovieByMovieId_found() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);

        when(movieRepository.findByMovieId(anyString())).thenReturn(movie);

        assertThat(moviesService.getMoviesByMovieId(movie.getMovieId())).isEqualTo(movie);
    }

    @Test
    void getMoviesByMovieId_notfound() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);

        when(movieRepository.findByMovieId(anyString())).thenReturn(null);

        assertThat(moviesService.getMoviesByMovieId(movie.getMovieId())).isEqualTo(null);
    }

//    @Test
//    void getMoviesByMovieId_exception() {
//        mock(Movie.class);
//        mock(MovieRepository.class);
//
//        when(movieRepository.findByMovieId(anyString())).thenReturn(null);
//
//
//
////        assertThrows(MovieNotFoundException.class, ()->moviesService.getMoviesByMovieId(movie.getMovieId()));
//    }

    @Test
    void deleteMoviesByNameId() throws MovieNotFoundException {
        mock(Movie.class);
        mock(MovieRepository.class);

        Theatre theatre = new Theatre("INOX", "INOX", "Kolkata");
        Show show = new Show("demo","Morning", 15, ETicketStatus.BOOK_ASAP, movie, theatre);
        List<Show> shows = new ArrayList<>();
        shows.add(show);
        when(showRepository.findAll()).thenReturn(shows);
        when(movieRepository.findById(movie.getMovieId())).thenReturn(Optional.of(movie));

        assertThat(moviesService.deleteMoviesByNameId(movie.getMovieName(), movie.getMovieId())).isEqualTo(movie);
    }
}