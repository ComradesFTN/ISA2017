package isa.projekat.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

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
import isa.projekat.domain.dto.RatingDTO;
import isa.projekat.domain.dto.VisitDTO;
import isa.projekat.repository.VisitRepository;
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
	public ResponseEntity<List<VisitDTO>> displayVisit(@PathVariable Long id, HttpSession session) {		
		List<Visit> visits=visitService.findAll();
		List<VisitDTO> visitsDTO = new ArrayList<VisitDTO>();
		for(Visit visit : visits){
			if(visit.getUserVisit().getId()==id){
				VisitDTO visitDTO = new VisitDTO(visit.getId(),visit.getUserVisit().getId(),visit.getProjectionVisit().getId(),visit.getCinemaVisit().getName());
				visitsDTO.add(visitDTO);
			}
		}
		
		User user = userService.findOne(id);
		List<Visit> posete = user.getVisits();
		int brojac = 0;
		for (Visit v : posete) {
			brojac++;
		}
		if (brojac == 1) {
			user.setMembership("Bronzani");
			brojac = 0;
			
		} else if (brojac == 2) {
			user.setMembership("Srebrni");
			brojac = 0;
			
		} else if (brojac >= 3) {
			user.setMembership("Zlatni");
			brojac = 0;
		} else {
			user.setMembership("Nema clanstva");
			brojac = 0;
		}
		userService.save(user);
		session.setAttribute("loggedUser", user);
		return new ResponseEntity<List<VisitDTO>>(visitsDTO,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/addRating", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<List<VisitDTO>> addRating(@RequestBody VisitDTO visitDTO, HttpSession session) {	
		session.setAttribute("visitId", visitDTO.getId());
		return new ResponseEntity<List<VisitDTO>>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/populateRatingPage", method = RequestMethod.GET)
	public ResponseEntity<Visit> populateRatingPage(HttpSession session) {	
		Visit visit = visitService.findOne((Long) session.getAttribute("visitId"));

/*		System.out.println(visit.getId());
		System.out.println(visit.getCinemaVisit().getId());
		System.out.println(visit.getProjectionVisit().getId());*/
		return new ResponseEntity<>(visit, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveRating", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<Visit> saveRating(@RequestBody RatingDTO ratingDTO, HttpSession session) {	
		Visit visit = visitService.findOne((Long) session.getAttribute("visitId"));
		visit.setCinemaRated(ratingDTO.getCinemaRating());
		visit.setMovieRated(ratingDTO.getMovieRating());
		visitService.save(visit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
