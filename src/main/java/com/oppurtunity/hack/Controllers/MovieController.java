package com.oppurtunity.hack.Controllers;


import java.util.List;

import com.oppurtunity.hack.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oppurtunity.hack.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/getAll")
	public List<Movie> getAllMovies() {
		return movieService.getAll();
	}
}
