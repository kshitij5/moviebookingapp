package com.moviebookingapp.techacadeemy.services;

import java.util.ArrayList;
import java.util.List;

import com.moviebookingapp.techacadeemy.entities.*;
import com.moviebookingapp.techacadeemy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.techacadeemy.exception.MovieNotFoundException;
import com.moviebookingapp.techacadeemy.repository.HallRepository;
import com.moviebookingapp.techacadeemy.repository.ShowRepository;
import com.moviebookingapp.techacadeemy.repository.TheatreRepository;

@Service
public class ShowServiceImpl implements ShowService {
	@Autowired
	private ShowRepository showRepository;


	@Autowired
	private TheatreRepository theatreRepository;
	@Autowired
	private HallRepository hallRepository;
	@Autowired
	private MovieRepository movieRepository;

	public ShowServiceImpl(ShowRepository showRepository, TheatreRepository theatreRepository, HallRepository hallRepository, MovieRepository movieRepository) {
		this.showRepository = showRepository;
		this.theatreRepository = theatreRepository;
		this.hallRepository = hallRepository;
		this.movieRepository = movieRepository;
	}
	@Override
	public Show addShow(Show show, String theatreId) {
		Theatre theatre = new Theatre();
		if (theatreId != null) {
			theatre = theatreRepository.findByTheatreId(theatreId);
			show.setTheatre(theatre);
			show = showRepository.save(show);
		}
		return show;
	}

	@Override
	public Show updateShow(Show show, String theatreId) {
		Theatre theatre = new Theatre();
		if (theatreId != null) {
			theatre = theatreRepository.findByTheatreId(theatreId);
			show.setTheatre(theatre);
		}
		showRepository.save(show);
		return show;
	}

	@Override
	public Show removeShow(String showid) {
		Show s = showRepository.findByShowId(showid);
		showRepository.delete(s);
		return s;
	}

	@Override
	public Show viewShowByShowId(String showid) {
		return showRepository.findByShowId(showid);
	}

	@Override
	public List<Show> viewShowByTheatreId(String theatreId) {
		return showRepository.getAllByTheatre(theatreId);
	}

	@Override
	public List<Show> viewAllShows() {
		return showRepository.findAll();
	}

	@Override
	public Show addMovieToShow(String movieId, String showId) throws MovieNotFoundException {
		int rows = 5, column = 10;
		double price = 150.0;

		if (showId != null) {
			Hall hall = new Hall();
			hall.setColumnNumer(column);
			hall.setRowNumer(rows);
			List<Seat> seats = new ArrayList<>();

			for (int i = 1; i <= rows; i++) {
				for (int j = 1; j <= column; j++) {
					Seat seat = new Seat();
					if (j > 8)
						price = +100;
					seat = new Seat(i + "" + j, i, j, ESeatStatus.AVAILABLE, price);
					seats.add(seat);
				}
			}

			hall.setSeats(seats);
//		hall.setMovie(movie);
//		hall.setShow(show);
			hallRepository.save(hall);

			Show show = new Show();
			show = showRepository.findByShowId(showId);
			Movie movie = movieRepository.findByMovieId(movieId);
			show.setMovie(movie);
			show.setHall(hall);

			showRepository.save(show);
		}

		return showRepository.findByShowId(showId);
	}

	@Override
	public Show updateTicketStatus(String showId, ETicketStatus status) {
		Show show = showRepository.findById(showId).get();
		show.setTicketStatus(status);
		return showRepository.save(show);
	}

	@Override
	public List<Show> viewShowByMovieId(String movieId) throws MovieNotFoundException {
		List<Show> shows = showRepository.getAllByMovie(movieRepository.findByMovieId(movieId));
		return shows;
	}

	/**
	 * @param hallId
	 * @return
	 */
//	@Override
//	public List<Show> viewShowByHallId(String hallId) {
//		List<Show> shows = showRepository.getAllByHallId(hallId);
//		return shows;
//	}

}