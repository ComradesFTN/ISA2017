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
import isa.projekat.domain.Cinema;
import isa.projekat.domain.Projection;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.Theater;
import isa.projekat.domain.Ticket;
import isa.projekat.domain.User;
import isa.projekat.domain.dto.TicketDTO;
import isa.projekat.service.AuditoriumService;
import isa.projekat.service.CinemaService;
import isa.projekat.service.ProjectionService;
import isa.projekat.service.TicketService;
import isa.projekat.service.UserService;
import isa.projekat.service.ReservationService;
import isa.projekat.service.TheaterService;


@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private AuditoriumService auditoriumService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ProjectionService projectionService;

	@Autowired
	private TheaterService theaterService;
	
	@RequestMapping(value = "getTickets/{id}/{userId}", method = RequestMethod.GET )
	public ResponseEntity<List<TicketDTO>> getTickets(@PathVariable Long id,@PathVariable Long userId) {
		System.out.println("Uso sam ovde");
		List<Ticket> tickets = ticketService.findAll();
		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		User user = userService.findOne(userId);
		for(Ticket ticket : tickets){
			if(ticket.getProjection().isMovie()){
				Cinema cinema = cinemaService.findOne(id);
				if(cinema.getMovies().contains(ticket.getProjection().getMovie())){
					
					String auditoriumName=auditoriumService.findOne(ticket.getProjection().getAuditoriumId()).getName();
					TicketDTO ticketDTO = new TicketDTO(ticket.getId(),ticket.getSeat(),ticket.getProjection().getDate(),ticket.getProjection().getTerm(),auditoriumName,ticket.getProjection().getMovie().getName(),ticket.getProjection().getMovie().getPrice(),ticket.getProjection().getMovie().getPoster(),user.getMembership());
					ticketsDTO.add(ticketDTO);
				}
			}
			else{
				Theater theater = theaterService.findOne(id);
				if(theater.getShows().contains(ticket.getProjection().getShow())){
					
					String auditoriumName=auditoriumService.findOne(ticket.getProjection().getAuditoriumId()).getName();
					TicketDTO ticketDTO = new TicketDTO(ticket.getId(),ticket.getSeat(),ticket.getProjection().getDate(),ticket.getProjection().getTerm(),auditoriumName,ticket.getProjection().getShow().getName(),ticket.getProjection().getShow().getPrice(),ticket.getProjection().getShow().getPoster(),user.getMembership());
					ticketsDTO.add(ticketDTO);
				}
			}
		}
		return new ResponseEntity<>(ticketsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
		Ticket ticket = ticketService.findOne(id);
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {		
		Ticket newTicket = ticketService.save(ticket);
		return new ResponseEntity<>(newTicket, HttpStatus.OK);
	}
	
	@RequestMapping(value = "reserveTicket/{id}/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Ticket> reserveTicket(@PathVariable Long id, @PathVariable Long userId) {
		Ticket ticket = ticketService.findOne(id);
		User user = userService.findOne(userId);
		Reservation reservation = new Reservation();
		reservation.setUser(user);
		reservation.setProjection(ticket.getProjection());
		reservation.setSeat(ticket.getSeat());
		int index = ticket.getSeat()-1;
		for(int i=0;i<reservation.getProjection().getSeats().size();i++){
			if(i==index){
				Projection projection = reservation.getProjection();
				projection.getSeats().set(i,2);
				System.out.println(projection.getSeats().get(i));
				projectionService.save(projection);
			}
		}
		reservationService.save(reservation);
		ticketService.delete(ticket);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
