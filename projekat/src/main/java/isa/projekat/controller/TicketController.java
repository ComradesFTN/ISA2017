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
import isa.projekat.domain.Ticket;
import isa.projekat.domain.dto.TicketDTO;
import isa.projekat.service.AuditoriumService;
import isa.projekat.service.CinemaService;
import isa.projekat.service.TicketService;


@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private AuditoriumService auditoriumService;
	
	@RequestMapping(value = "getTickets/{id}", method = RequestMethod.GET )
	public ResponseEntity<List<TicketDTO>> getTickets(@PathVariable Long id) {
		List<Ticket> tickets = ticketService.findAll();
		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		Cinema cinema = cinemaService.findOne(id);
		for(Ticket ticket : tickets){
			if(cinema.getMovies().contains(ticket.getProjection().getMovie())){
				String auditoriumName=auditoriumService.findOne(ticket.getProjection().getAuditoriumId()).getName();
				TicketDTO ticketDTO = new TicketDTO(ticket.getId(),ticket.getSeat(),ticket.getProjection().getDate(),ticket.getProjection().getTerm(),auditoriumName,ticket.getProjection().getMovie().getName(),ticket.getProjection().getMovie().getPrice(),ticket.getProjection().getMovie().getPoster());
				ticketsDTO.add(ticketDTO);
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
	
}
