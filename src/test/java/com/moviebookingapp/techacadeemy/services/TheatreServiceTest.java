package com.moviebookingapp.techacadeemy.services;

import com.moviebookingapp.techacadeemy.entities.ETicketStatus;
import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.TheatreNotFoundException;
import com.moviebookingapp.techacadeemy.repository.MovieRepository;
import com.moviebookingapp.techacadeemy.repository.ShowRepository;
import com.moviebookingapp.techacadeemy.repository.TheatreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TheatreServiceTest {

    @Mock
    private TheatreRepository theatreRepository;
    @Mock
    private MovieRepository moviesrepository;

    @Mock
    private ShowRepository showRepository;
    @Mock
    private TheatreService theatreService;
    Theatre theatre;

    @BeforeEach
    void setUp() {
        theatreService = new TheatreServiceImpl(theatreRepository, moviesrepository,showRepository);
         theatre = new Theatre("INOX", "INOX", "Kolkata");
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllTheatres() throws TheatreNotFoundException {
        mock(Theatre.class);
        mock(TheatreRepository.class);
        List<Theatre> theatres = new ArrayList<>();
        theatres.add(theatre);

        when(theatreRepository.findAll()).thenReturn(theatres);

        assertThat(theatreService.getAllTheatres()).isEqualTo(theatres);
    }

    @Test
    void findTheatres() {
        mock(Theatre.class);
        mock(TheatreRepository.class);

        when(theatreRepository.findById(theatre.getTheatreId())).thenReturn(Optional.ofNullable(theatre));

        assertThat(theatreService.findTheatres(theatre.getTheatreId())).isEqualTo(theatre);
    }

    @Test
    void addTheatre() throws TheatreNotFoundException {
        mock(Theatre.class);
        mock(TheatreRepository.class);

        when(theatreRepository.existsByTheatreId(theatre.getTheatreId())).thenReturn(false);
        when(theatreRepository.save(theatre)).thenReturn(theatre);

        assertThat(theatreService.addTheatre(theatre)).isEqualTo(theatre);
    }

    @Test
    void updateTheatre() {
        mock(Theatre.class);
        mock(TheatreRepository.class);

        when(theatreRepository.save(theatre)).thenReturn(theatre);

        assertThat(theatreService.updateTheatre(theatre)).isEqualTo(theatre);
    }

    @Test
    void findTheatresByMovie() throws TheatreNotFoundException {
        mock(Theatre.class);
        mock(TheatreRepository.class);
        Movie movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        Show show = new Show("demo","Morning", 15, ETicketStatus.BOOK_ASAP, movie, theatre);
        List<Show> shows = new ArrayList<>();
        shows.add(show);
        when(showRepository.findAll()).thenReturn(shows);

        Set<Theatre> theatres = new HashSet<>();
        theatres.add(theatre);
        assertThat(theatreService.findTheatresByMovie(movie.getMovieId())).isEqualTo(theatres);
    }

    @Test
    void deleteTheatreById() {
        Movie movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        Show show = new Show("demo","Morning", 15, ETicketStatus.BOOK_ASAP, movie, theatre);
        List<Show> shows = new ArrayList<>();
        shows.add(show);
        when(showRepository.getAllByTheatre(theatre.getTheatreId())).thenReturn(shows);

        assertNull(theatreService.deleteTheatreById(theatre.getTheatreId()));
    }
}