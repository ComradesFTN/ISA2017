package isa.projekat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.dto.ReservationDTO;
import isa.projekat.service.AuditoriumService;
import isa.projekat.service.CinemaService;
import isa.projekat.service.ReservationService;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@RequestMapping(value = "getReservations", method = RequestMethod.GET )
	public ResponseEntity<List<Reservation>> getReservations() {
		List<Reservation> reservations = reservationService.findAll();
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
		Reservation reservation = reservationService.findOne(id);
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addReservation", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO reservationDTO) {
		System.out.println("???");
		Reservation newReservation = reservationService.save(reservationDTO);
		System.out.println("NAPRAVLJENA REZERVACIJA : " + newReservation.getId() + " " + newReservation.getSeat() + " " + newReservation.getProjection().getId() + " " + newReservation.getUser().getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservationConfirmed/{userId}/{inviterId}", method = RequestMethod.GET)
	public RedirectView reservationConfirmed(@PathVariable Long userId,@PathVariable Long inviterId) {
		reservationService.Confirm(userId, inviterId);
		return new RedirectView("http://localhost:8080/index.html");
	}
	
	@RequestMapping(value = "/reservationRejected/{userId}/{inviterId}/{reservationId}", method = RequestMethod.GET)
	public RedirectView reservationRejected(@PathVariable Long userId,@PathVariable Long inviterId, @PathVariable Long reservationId ) {
		reservationService.Rejected(userId, inviterId, reservationId);
		return new RedirectView("http://localhost:8080/index.html");
	}
	
	@RequestMapping(value = "/reservationCanceled/{userId}/{reservationId}", method = RequestMethod.GET)
	public ResponseEntity<Reservation> reservationRejected(@PathVariable Long userId, @PathVariable Long reservationId ) {
		reservationService.Canceled(userId,reservationId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "getReservedSeats/{salaId}", method = RequestMethod.GET )
	public ResponseEntity<List<Integer>> getReservedSeats(@PathVariable Long salaId) {
		List<Reservation> reservations = reservationService.findAll();
		List<Integer> reservedSeats= new ArrayList<Integer>();
		for(Reservation reservation : reservations){
			if(reservation.getProjection().getAuditoriumId()==salaId){
				reservedSeats.add(reservation.getSeat());
			}
		}
		return new ResponseEntity<>(reservedSeats, HttpStatus.OK);
	}
	
	@RequestMapping(value = "isEditable/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> isEditable(@PathVariable Long id) {
		Boolean isEditable=reservationService.isEditable(id);
		return new ResponseEntity<>(isEditable, HttpStatus.OK);
	}
	
	@RequestMapping(value = "getReservations/{userId}", method = RequestMethod.GET )
	public ResponseEntity<List<ReservationDTO>> getReservations(@PathVariable Long userId) {
		System.out.println(userId);
		List<Reservation> reservations = reservationService.findByUser_id(userId);
		List<ReservationDTO> reservationsDTO=new ArrayList<ReservationDTO>();
		for(Reservation reservation : reservations){
			System.out.println("???");
			ReservationDTO dto = new ReservationDTO();
			dto.setDate(reservation.getProjection().getDate());
			dto.setAuditoriumName(auditoriumService.findOne(reservation.getProjection().getAuditoriumId()).getName());
			dto.setTerm(reservation.getProjection().getTerm());
			List<Cinema> cinemas = cinemaService.findAll();
			Cinema visitCinema = new Cinema();
			for(int i=0; i<cinemas.size(); i++) {
				Cinema cinema = cinemas.get(i);
				Set<Movie> movies = cinema.getMovies();
					for(Movie movieTemp : movies ) {
						if(movieTemp.getId() == reservation.getProjection().getMovie().getId()) {
							visitCinema = cinema;
							break;
						}
					}
				if(visitCinema.getName() != null) {
					break;
				}
			}
			dto.setPlaceName(visitCinema.getName());
			dto.setProjectionName(reservation.getProjection().getMovie().getName());
			dto.setId(reservation.getId());
			System.out.println("???");
			reservationsDTO.add(dto);			
			}
		return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
		}
	}
