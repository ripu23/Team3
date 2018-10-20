package clicktime.app.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clicktime.app.entities.Movie;
import clicktime.app.service.MovieService;

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
