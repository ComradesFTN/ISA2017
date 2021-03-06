package isa.projekat.controller;

import java.util.HashSet;
import java.util.List;

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
		for(Auditorium auditorium : cinema.getAuditoriums()){
			for(int i=0;i<auditorium.getRows()*auditorium.getColumns();i++){
				auditorium.getSeats().add(Integer.valueOf(1));
			}
		}
		Cinema newCinema = cinemaService.save(cinema);
		return new ResponseEntity<>(newCinema, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Cinema> editCinema(@PathVariable Long id,@RequestBody Cinema cinema) {
		Cinema editCinema=cinemaService.findOne(id);
		editCinema.setName(cinema.getName());
		editCinema.setAdress(cinema.getAdress());
		editCinema.setDescription(cinema.getDescription());
		Cinema editedCinema = cinemaService.save(editCinema);
		return new ResponseEntity<>(editedCinema, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/getMovies", method = RequestMethod.GET )
	public ResponseEntity<HashSet<Movie>> getCinemaMovies(@PathVariable Long id) {
		HashSet<Movie> movies = new HashSet<Movie>(cinemaService.findOne(id).getMovies());
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
	
	@RequestMapping(value = "/{cinema_id}/editMovie/{movie_id}", method = RequestMethod.PUT )
	public ResponseEntity<Movie> editMovie(@PathVariable Long cinema_id,@PathVariable Long movie_id) {
		Cinema cinema = cinemaService.findOne(cinema_id);
		Movie movie = movieService.findOne(movie_id);
		for(Movie removeMovie:cinema.getMovies()){
			if(movie.getId()==removeMovie.getId()){
				cinema.getMovies().remove(removeMovie);
				cinema.getMovies().add(movie);
				break;
			}
		}
		cinemaService.save(cinema);
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/refreshCinema/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cinema> refreshCinema(@PathVariable Long id) {
		Cinema cinema = cinemaService.findOne(id);
		cinemaService.refresh(cinema);
		return new ResponseEntity<>(cinema, HttpStatus.OK);
	}
	
}
