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

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Ticket;
import isa.projekat.service.TicketService;


@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(value = "getTickets", method = RequestMethod.GET )
	public ResponseEntity<List<Ticket>> getTickets() {
		List<Ticket> tickets = ticketService.findAll();
		return new ResponseEntity<>(tickets, HttpStatus.OK);
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
