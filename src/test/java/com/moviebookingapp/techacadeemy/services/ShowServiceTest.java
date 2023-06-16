package com.moviebookingapp.techacadeemy.services;

import com.moviebookingapp.techacadeemy.entities.ETicketStatus;
import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.repository.HallRepository;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    @Mock
    private ShowRepository showRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private TheatreRepository theatreRepository;
    @Mock
    private HallRepository hallRepository;
    @Mock
    private MoviesService moviesService;
    private ShowService showService;

    Show show;Theatre theatre;Movie movie;
    List<Show> showList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        theatre = new Theatre("INOX", "INOX", "Kolkata");
        movie = new Movie("demo", "demo", "demo", "demo", "demo", "demo", "demo", 7.6, Date.from(Instant.parse("2022-07-10T11:00:55.000+00:00")));
        show = new Show("demo","Morning", 15, ETicketStatus.BOOK_ASAP, movie, theatre);
        showList.add(show);
        showService = new ShowServiceImpl(showRepository, theatreRepository, hallRepository, movieRepository);
    }

    @AfterEach
    void tearDown() {

    }
    @Test
    void addShow() {

        when(theatreRepository.findByTheatreId(anyString())).thenReturn(theatre);
        when(showRepository.save(any(Show.class))).thenReturn(show);

        assertThat(showService.addShow(show, theatre.getTheatreId())).isEqualTo(show);
    }

    @Test
    void updateShow() {
        when(theatreRepository.findByTheatreId(anyString())).thenReturn(theatre);
        when(showRepository.save(any(Show.class))).thenReturn(show);

        assertThat(showService.updateShow(show, theatre.getTheatreId())).isEqualTo(show);
    }

    @Test
    void removeShow() {
        when(showRepository.findByShowId(anyString())).thenReturn(show);

        assertThat(showService.removeShow(show.getShowId())).isEqualTo(show);

    }

    @Test
    void viewShowByShowId() {

        when(showRepository.findByShowId(anyString())).thenReturn(show);

        assertThat(showService.viewShowByShowId(show.getShowId())).isEqualTo(show);

    }

    @Test
    void viewShowByTheatreId() {
        when(showRepository.getAllByTheatre(anyString())).thenReturn(showList);

        assertThat(showService.viewShowByTheatreId(theatre.getTheatreId())).isEqualTo(showList);
    }

    @Test
    void viewAllShows() {
        when(showRepository.findAll()).thenReturn(showList);

        assertThat(showService.viewAllShows()).isEqualTo(showList);
        assertThat(showService.viewAllShows().size()).isEqualTo(showList.size());
    }

    @Test
    void addMovieToShow() throws MovieNotFoundException {
        when(showRepository.findByShowId(anyString())).thenReturn(show);
        when(movieRepository.findByMovieId(anyString())).thenReturn(movie);

        assertThat(showService.addMovieToShow(movie.getMovieId(), show.getShowId())).isEqualTo(show);

    }
}