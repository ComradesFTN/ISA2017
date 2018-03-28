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
import isa.projekat.service.CinemaService;

@RestController
@RequestMapping(value = "/cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;

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
	
}
