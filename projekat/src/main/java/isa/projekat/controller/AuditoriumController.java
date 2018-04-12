package isa.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Auditorium> addAuditorium(@RequestBody Auditorium auditorium) {
		Auditorium newAuditorium = auditoriumService.save(auditorium);
		return new ResponseEntity<>(newAuditorium, HttpStatus.OK);
	}
	
}
