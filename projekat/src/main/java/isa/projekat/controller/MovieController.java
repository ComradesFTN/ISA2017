package isa.projekat.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.UserAd;
import isa.projekat.service.CinemaService;
import isa.projekat.service.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CinemaService cinemaService;

	@RequestMapping(value = "getMovies", method = RequestMethod.GET )
	public ResponseEntity<List<Movie>> getMovies() {
		List<Movie> movies = movieService.findAll();
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
		Movie movie = movieService.findOne(id);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {	
		Movie newMovie = movieService.save(movie);
		return new ResponseEntity<>(newMovie, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT ,consumes = "application/json")
	public ResponseEntity<Movie> editMovie(@PathVariable Long id,@RequestBody Movie movie) {		
		movie.setId(id);		
		Movie editedMovie = movieService.save(movie);
		return new ResponseEntity<>(editedMovie, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
		List<Cinema> cinemas=cinemaService.findAll();	
		Movie deletedMovie = movieService.findOne(id);
		for(Cinema cinema : cinemas){
			Set<Movie> cinemaMovies = cinema.getMovies();
			if(cinemaMovies.remove(deletedMovie)){
				cinemaService.save(cinema);
			}
		}
		deletedMovie = movieService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
