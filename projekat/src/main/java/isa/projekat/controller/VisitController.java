package isa.projekat.controller;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;
import isa.projekat.domain.User;
import isa.projekat.domain.Visit;
import isa.projekat.domain.dto.VisitDTO;
import isa.projekat.service.CinemaService;
import isa.projekat.service.ProjectionService;
import isa.projekat.service.UserService;

@Controller
@RequestMapping(value = "/visit")
public class VisitController {
	
	@Autowired
	private ProjectionService projectionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CinemaService cinemaService;

	@RequestMapping(value = "/displayVisit", method = RequestMethod.POST)
	public ResponseEntity<Visit> displayVisit(@RequestBody VisitDTO visitDTO) {

		User user = userService.findOne(visitDTO.getUser());
		Projection projection = projectionService.findOne(visitDTO.getProjection());
		Date currentDate = new Date();		
		
		if(projection.getDate().before(currentDate)) {
			System.out.println("Projekcija prosle, moze!");
			Movie movie = projection.getMovie();
			List<Cinema> cinemas = cinemaService.findAll();
			for(int i=0; i<cinemas.size(); i++) {
				Cinema cinema = cinemas.get(i);
				Set<Movie> movies = cinema.getMovies();
				
				for(Movie movieTemp : movies ) {
					if(movieTemp.getId() == movie.getId()) {
						cinema.getName();
						
					}
				}
				
			}
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
