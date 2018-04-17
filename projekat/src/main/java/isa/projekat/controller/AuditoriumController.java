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
	
	@RequestMapping(value = "getAuditoriums", method = RequestMethod.GET )
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
	public ResponseEntity<Auditorium> addAuditorium(@RequestBody Auditorium auditorium) {
		for(int i=0;i<auditorium.getRows()*auditorium.getColumns();i++){
			auditorium.getSeats().add(1);
		}
		Auditorium newAuditorium = auditoriumService.save(auditorium);
		return new ResponseEntity<>(newAuditorium, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}",method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Auditorium> editAuditorium(@PathVariable Long id,@RequestBody Auditorium auditorium) {
		Auditorium editAuditorium = auditoriumService.findOne(id);		
		auditorium.setId(id);
		auditorium.setMovies(editAuditorium.getMovies());
		auditoriumService.save(auditorium);
		return new ResponseEntity<>(auditorium,HttpStatus.OK);
	}
}
