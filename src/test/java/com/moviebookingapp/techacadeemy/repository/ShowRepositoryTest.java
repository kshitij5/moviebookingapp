package com.moviebookingapp.techacadeemy.repository;

import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ShowRepositoryTest {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    TheatreRepository theatreRepository;

    Theatre theatre;
    List<Show> shows;

    @BeforeEach
    void setUp() {
        theatre = new Theatre("INXKOL", "INOX", "Kolkata");
        theatreRepository.save(theatre);
    }

    @AfterEach
    void tearDown() {
        theatreRepository.delete(theatre);
        showRepository.deleteAll(shows);
//        showRepository.deleteAll();
//        theatreRepository.deleteAll();
    }

    @Test
    void getAllByTheatreId() {
        shows = showRepository.getAllByTheatre(theatre.getTheatreId());

        for (Show show : shows) {
            assertThat(show.getTheatre().getTheatreId()).isEqualTo(theatre.getTheatreId());
            assertThat(show.getShowName()).containsAnyOf("Morning","Afternoon","Evening");
        }

//        assertThat(shows.get(0).getShowName()).isEqualTo("Morning");
//        assertThat(shows.get(1).getShowName()).isEqualTo("Afternoon");
//        assertThat(shows.get(2).getShowName()).isEqualTo("Evening");
    }
}