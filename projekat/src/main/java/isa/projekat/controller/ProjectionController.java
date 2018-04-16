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

import isa.projekat.domain.Projection;
import isa.projekat.service.ProjectionService;

@RestController
@RequestMapping(value = "/projections")
public class ProjectionController {

	@Autowired
	private ProjectionService projectionService;
	
	@RequestMapping(value = "getProjections", method = RequestMethod.GET )
	public ResponseEntity<List<Projection>> getProjections() {
		List<Projection> projections = projectionService.findAll();
		return new ResponseEntity<>(projections, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Projection> getProjection(@PathVariable Long id) {
		Projection projection = projectionService.findOne(id);
		return new ResponseEntity<>(projection, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Projection> addProjection(@RequestBody Projection projection) {	
		Projection newProjection = projectionService.save(projection);
		return new ResponseEntity<>(newProjection, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Projection> editProjection(@PathVariable Long id,@RequestBody Projection projection) {		
		Projection editedProjection = projectionService.save(projection);
		return new ResponseEntity<>(editedProjection, HttpStatus.OK);
	}
}
