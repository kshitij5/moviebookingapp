package com.moviebookingapp.techacadeemy.controller;

import com.moviebookingapp.techacadeemy.entities.Movie;
import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.exception.UserNotFoundException;
import com.moviebookingapp.techacadeemy.payload.request.LoginRequest;
import com.moviebookingapp.techacadeemy.payload.request.PasswordResetRequest;
import com.moviebookingapp.techacadeemy.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

// special controller class for project required api endpoint
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class ProjectController {

    @Autowired
    AuthController authController;

    @Autowired
    MovieController movieController;

    @Autowired
    TicketController ticketController;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
        return authController.registerUser(signUpRequest);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest)
            throws Exception, UserNotFoundException {
        return  authController.authenticateUser(loginRequest);
    }
    @PostMapping("/{username}/forgot")
    public ResponseEntity<?> forgot(@PathVariable String username, @RequestBody PasswordResetRequest passwordReset)
            throws UserNotFoundException {return  authController.forgot(username, passwordReset);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() throws MovieNotFoundException {
        return movieController.getAllMovies();
    }

    @GetMapping("/movies/search/{movieName}")
    public ResponseEntity<List<Movie>> viewMovieByMovieName(@PathVariable String movieName)
            throws MovieNotFoundException {
        return  movieController.viewMovieByMovieName(movieName);
    }
    @PostMapping("/{movieName}/add")
    public ResponseEntity<Movie> addMovie(@PathVariable String movieName, @RequestBody Movie movie) throws MovieNotFoundException, IOException {
        return movieController.addMovie(movie);
    }
//    @GetMapping("/{moviename}/update/{ticket}")
//    public ResponseEntity<?> update(@PathVariable Movie movie) throws MovieNotFoundException {
//        return ticketController.updateTicketStatus(movie);
//    }
    @DeleteMapping("/{moviename}/delete/{movieId}")
    public Movie deleteMovie(@PathVariable String moviename, @PathVariable String movieId)
            throws MovieNotFoundException {
        return  movieController.deleteMovie(moviename, movieId);
    }
}