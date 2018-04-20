package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Ticket;

public interface TicketService {
	
	List<Ticket> findAll();

	Ticket save(Ticket ticket);

	Ticket findOne(Long id);
	
	List<Ticket> findByProjection_idAndSeat(long projection_id,Integer seat);
	
	List<Ticket> findByProjection_id(long projection_id);

	void delete(Ticket ticket);
	
}
