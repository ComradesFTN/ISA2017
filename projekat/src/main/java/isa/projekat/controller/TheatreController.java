package isa.projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.projekat.domain.Theatre;
import isa.projekat.service.TheatreService;

@RestController
@RequestMapping(value = "/theatres")
public class TheatreController {
	
	@Autowired
	private TheatreService theatreService;

	@RequestMapping(value = "getTheatres", method = RequestMethod.GET )
	public ResponseEntity<List<Theatre>> getTheatres() {
		List<Theatre> theatres = theatreService.findAll();
		return new ResponseEntity<>(theatres, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json" )
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
		Theatre newTheatre = theatreService.save(theatre);
		return new ResponseEntity<>(newTheatre, HttpStatus.OK);
	}
}
