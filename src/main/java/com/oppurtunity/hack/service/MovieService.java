package com.oppurtunity.hack.service;

import java.util.ArrayList;
import java.util.List;

import com.oppurtunity.hack.entities.Movie;
import com.oppurtunity.hack.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public List<Movie> getAll() {
		List<Movie> list = new ArrayList<>();
		list = (List<Movie>) movieRepository.findAll();	
		return list;
	}
	
	public void addMovie(Movie movie) {
		movieRepository.save(movie);
	}
	
	
	public void addAllMovie(List<Movie> movies) {
		movieRepository.saveAll(movies);
	}
}
