package isa.projekat.controller;

import java.util.List;
import java.util.Set;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.User;
import isa.projekat.domain.Visit;
import isa.projekat.domain.dto.VisitDTO;
import isa.projekat.service.CinemaService;
import isa.projekat.service.ProjectionService;
import isa.projekat.service.ReservationService;
import isa.projekat.service.UserService;
import isa.projekat.service.VisitService;

@Controller
@RequestMapping(value = "/visit")
public class VisitController {
	
	@Autowired
	private ProjectionService projectionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private ReservationService reservationService;

	@RequestMapping(value = "/displayVisit", method = RequestMethod.POST)
	public ResponseEntity<Visit> displayVisit(@RequestBody VisitDTO visitDTO) {

		User user = userService.findOne(visitDTO.getUser());
		Projection projection = projectionService.findOne(visitDTO.getProjection());
		Date currentDate = new Date();		
		System.out.println("gadjanje");
		if(projection.getDate().before(currentDate)) { //projekcija je prosla
			System.out.println("usooo");
			List<Reservation> listReservation = reservationService.findByUser_idAndProjection_id(user.getId(), projection.getId());
			Visit visit = new Visit();
			visit.setUser(user);
			visit.setProjection(projection);
			
			List<Cinema> cinemas = cinemaService.findAll();
			Cinema visitCinema = new Cinema();
			for(int i=0; i<cinemas.size(); i++) {
				Cinema cinema = cinemas.get(i);
				Set<Movie> movies = cinema.getMovies();
					for(Movie movieTemp : movies ) {
						if(movieTemp.getId() == projection.getMovie().getId()) {
							visitCinema = cinema;
							break;
						}
					}
				if(visitCinema.getName() != null) {
					break;
				}

			}
			
			visit.setCinema(visitCinema);
			List<Visit> visitList = visitService.findAll();
			for(int j = 0; j < visitList.size(); j++) {
				Visit checkingVisit = visitList.get(j);
				if(checkingVisit.getProjection().getId() == visit.getProjection().getId()) {
					if(checkingVisit.getUser().getId() == visit.getUser().getId()) {
						return new ResponseEntity<>(visit, HttpStatus.OK);
					}  
						
				}
			} 
			visitService.save(visit);
			return new ResponseEntity<>(visit, HttpStatus.OK);
			
			/*Movie movie = projection.getMovie();
			List<Cinema> cinemas = cinemaService.findAll();
			for(int i=0; i<cinemas.size(); i++) {
				Cinema cinema = cinemas.get(i);
				Set<Movie> movies = cinema.getMovies();
				
				for(Movie movieTemp : movies ) {
					if(movieTemp.getId() == movie.getId()) {
						Visit visit = new Visit();
						visit.setUser(user);
						visit.setProjection(projection);
						visit.setCinema(cinema);
						List<Visit> visitList = visitService.findAll();
						for(int j = 0; j < visitList.size(); j++) {
							Visit checkingVisit = visitList.get(j);
							if(checkingVisit.getProjection().getId() != visit.getProjection().getId()) {
								if(checkingVisit.getUser().getId() != visit.getUser().getId()) {
									visitService.save(visit);
								}  
									
							}
						}
						
					}
				}*/
				
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
