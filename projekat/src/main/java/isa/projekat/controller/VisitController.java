package isa.projekat.controller;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/refreshVisits", method = RequestMethod.POST)
	public ResponseEntity<List<VisitDTO>> refreshVisits(){
		List<Projection> projections = projectionService.findAll();
		Date currentDate = new Date();	
		for(Projection projection : projections){
			System.out.println("Usao sam ovde");
			if(projection.getDate().before(currentDate) && projection.getOld()==false ) { 
				System.out.println("bogami i ovde");
				List<Reservation> listReservation = reservationService.findByProjection_id(projection.getId());
				Set<Integer> users = new HashSet<Integer>();
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
				for(Reservation reservation : listReservation){
					users.add((int) reservation.getUser().getId());					
				}
				for(Reservation reservation : listReservation){
					reservationService.delete(reservation.getId());
				}
				for(Integer userId : users){
					Visit visit = new Visit();
					visit.setCinemaVisit(visitCinema);
					visit.setProjectionVisit(projection);
					visit.setUserVisit(userService.findOne((long)userId));
					visitService.save(visit);					
				}				
				projection.setOld(true);
				projectionService.save(projection);
				
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/displayVisit/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<VisitDTO>> displayVisit(@PathVariable Long id) {		
		List<Visit> visits=visitService.findAll();
		List<VisitDTO> visitsDTO = new ArrayList<VisitDTO>();
		for(Visit visit : visits){
			VisitDTO visitDTO = new VisitDTO(visit.getId(),visit.getUserVisit().getId(),visit.getProjectionVisit().getId(),visit.getCinemaVisit().getName());
			visitsDTO.add(visitDTO);
		}
		return new ResponseEntity<List<VisitDTO>>(visitsDTO,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/addRating", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<List<VisitDTO>> displayVisit(@RequestBody VisitDTO visitDTO) {	
		
		return new ResponseEntity<List<VisitDTO>>(HttpStatus.OK);
	}
}
