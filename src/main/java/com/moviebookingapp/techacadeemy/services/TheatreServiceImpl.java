package com.moviebookingapp.techacadeemy.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.techacadeemy.entities.Show;
import com.moviebookingapp.techacadeemy.entities.Theatre;
import com.moviebookingapp.techacadeemy.exception.TheatreNotFoundException;
import com.moviebookingapp.techacadeemy.repository.MovieRepository;
import com.moviebookingapp.techacadeemy.repository.ShowRepository;
import com.moviebookingapp.techacadeemy.repository.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatrerepository;
	@Autowired
	private MovieRepository moviesrepository;
	@Autowired
	private ShowRepository showRepository;

	public TheatreServiceImpl(TheatreRepository theatrerepository, MovieRepository moviesrepository, ShowRepository showRepository) {
		this.theatrerepository = theatrerepository;
		this.moviesrepository = moviesrepository;
		this.showRepository = showRepository;
	}

	@Override
	public List<Theatre> getAllTheatres() throws TheatreNotFoundException {
		List<Theatre> the = (List<Theatre>) theatrerepository.findAll();
		// if (the.size() == 0) throw new TheatreNotFoundException("No theatres found");
		return the;
	}

	@Override
	public Theatre findTheatres(String theatreId) {
		// TODO Auto-generated method stub
		if (theatrerepository.findById(theatreId).isPresent()) {
			return theatrerepository.findById(theatreId).get();
		} else
			return null;
	}

	@Override
	public Theatre addTheatre(Theatre m) throws TheatreNotFoundException {
		if (m != null) {
			if (theatrerepository.existsByTheatreId(m.getTheatreId())) {
				throw new TheatreNotFoundException("Theatre already exists");
			} else {
				theatrerepository.save(m);
			}
		}
		return m;
	}

	@Override
	public Theatre updateTheatre(Theatre m) {
		theatrerepository.save(m);
		return theatrerepository.save(m);
	}

	@Override
	public Object deleteTheatreById(String theatreId) {
		theatrerepository.deleteById(theatreId);
		List<Show> shows = showRepository.getAllByTheatre(theatreId);
		for(Show show : shows) {
			showRepository.delete(show);
		}
		return null;
	}

	@Override
	public Set<Theatre> findTheatresByMovie(String movieId) throws TheatreNotFoundException {

		List<Show> shows = new ArrayList<>();
		Set<Theatre> theatreList = new HashSet<>();

		shows = showRepository.findAll();

		for (Show show : shows) {
			if (show.getMovie()!=null && Objects.equals(show.getMovie().getMovieId(), movieId)) {
				theatreList.add(show.getTheatre());
			}
		}

		return theatreList;
	}
}