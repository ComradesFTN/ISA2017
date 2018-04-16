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

import isa.projekat.domain.Auditorium;
import isa.projekat.service.AuditoriumService;

@RestController
@RequestMapping(value = "/auditoriums")
public class AuditoriumController {

	@Autowired
	private AuditoriumService auditoriumService;
	
	@RequestMapping(value = "getAuditorium", method = RequestMethod.GET )
	public ResponseEntity<List<Auditorium>> getAuditoriums() {
		List<Auditorium> auditoriums = auditoriumService.findAll();
		return new ResponseEntity<>(auditoriums, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Auditorium> getAuditorium(@PathVariable Long id) {
		Auditorium auditorium = auditoriumService.findOne(id);
		return new ResponseEntity<>(auditorium, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Auditorium> addCinema(@RequestBody Auditorium auditorium) {
		Auditorium newAuditorium = auditoriumService.save(auditorium);
		return new ResponseEntity<>(newAuditorium, HttpStatus.OK);
	}
}