package isa.projekat.controller;

import java.util.ArrayList;
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
import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;
import isa.projekat.service.AuditoriumService;
import isa.projekat.service.MovieService;
import isa.projekat.service.ProjectionService;

@RestController
@RequestMapping(value = "/projections")
public class ProjectionController {

	@Autowired
	private AuditoriumService auditoriumService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ProjectionService projectionService;
	
	@RequestMapping(value = "getProjections", method = RequestMethod.GET )
	public ResponseEntity<List<Projection>> getProjections() {
		List<Projection> projections = projectionService.findAll();
		return new ResponseEntity<>(projections, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getSeats/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Integer>> getSeats(@PathVariable Long id) {
		Projection projection = projectionService.findOne(id);
		List<Integer> seats = projection.getSeats();
		System.out.println(projection.getDate());
		return new ResponseEntity<>(seats, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Projection> getProjection(@PathVariable Long id) {
		Projection projection = projectionService.findOne(id);
		return new ResponseEntity<>(projection, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Projection> addProjection(@PathVariable Long id, @RequestBody Projection projection) {
		Movie movie = movieService.findOne(id);
		projection.setMovie(movie);
		Auditorium auditorium = auditoriumService.findOne(projection.getAuditoriumId());
		List<Integer> seats = new ArrayList<Integer>();
		for(Integer i : auditorium.getSeats()){
			seats.add(i);
		}
		projection.setSeats(seats);
		Projection newProjection = projectionService.save(projection);
		return new ResponseEntity<>(newProjection, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Projection> editProjection(@PathVariable Long id,@RequestBody Projection projection) {		
		Projection editedProjection = projectionService.save(projection);
		return new ResponseEntity<>(editedProjection, HttpStatus.OK);
	}
}
