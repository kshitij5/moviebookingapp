package com.moviebookingapp.techacadeemy.repository;

import com.moviebookingapp.techacadeemy.entities.Theatre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TheatreRepositoryTest {
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    ShowRepository showRepository;

    Theatre theatre;

    @BeforeEach
    void setUp() {
        theatre = new Theatre("INXKOL", "INOX", "Kolkata");
        theatreRepository.save(theatre);
    }

    @AfterEach
    void tearDown() {
        theatreRepository.delete(theatre);
        showRepository.deleteAll(showRepository.getAllByTheatre(theatre.getTheatreId()));
//        showRepository.deleteAll();
//        theatreRepository.deleteAll();
    }

    @Test
    void findByTheatreId() {
        Theatre expected = theatreRepository.findByTheatreId(theatre.getTheatreId());
        assertThat(expected.getTheatreName()).isEqualTo(theatre.getTheatreName());
        assertThat(expected.getTheatreCity()).isEqualTo(theatre.getTheatreCity());
        assertThat(expected.getClass()).isEqualTo(theatre.getClass());
    }

    @Test
    void existsByTheatreId() {
        Boolean expected = theatreRepository.existsByTheatreId(theatre.getTheatreId());
        assertThat(expected).isTrue();
    }
}