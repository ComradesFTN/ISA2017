package isa.projekat.controller;

import java.util.List;

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

import isa.projekat.domain.Reservation;
import isa.projekat.domain.dto.ReservationDTO;
import isa.projekat.service.ReservationService;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
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
	
}
