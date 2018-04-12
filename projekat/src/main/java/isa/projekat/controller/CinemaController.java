package isa.projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.service.CinemaService;
import isa.projekat.service.MovieService;

@RestController
@RequestMapping(value = "/cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "getCinemas", method = RequestMethod.GET )
	public ResponseEntity<List<Cinema>> getCinemas() {
		List<Cinema> cinemas = cinemaService.findAll();
		return new ResponseEntity<>(cinemas, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Cinema> getCinema(@PathVariable Long id) {
		Cinema cinema = cinemaService.findOne(id);
		return new ResponseEntity<>(cinema, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Cinema> addCinema(@RequestBody Cinema cinema) {
		Cinema newCinema = cinemaService.save(cinema);
		return new ResponseEntity<>(newCinema, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Cinema> editCinema(@PathVariable Long id,@RequestBody Cinema cinema) {
		cinema.setId(id);
		Cinema editedCinema = cinemaService.save(cinema);
		return new ResponseEntity<>(editedCinema, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/getMovies", method = RequestMethod.GET )
	public ResponseEntity<List<Movie>> getCinemaMovies(@PathVariable Long id) {
		List<Movie> movies = cinemaService.findOne(id).getMovies();
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{cinema_id}/addMovie/{movie_id}", method = RequestMethod.PUT )
	public ResponseEntity<Movie> addMovie(@PathVariable Long cinema_id,@PathVariable Long movie_id) {
		Cinema cinema = cinemaService.findOne(cinema_id);
		Movie movie = movieService.findOne(movie_id);
		cinema.getMovies().add(movie);
		cinemaService.save(cinema);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
}
