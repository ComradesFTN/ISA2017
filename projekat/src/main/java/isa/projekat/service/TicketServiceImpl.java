package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Ticket;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Override
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket findOne(Long id) {
		return ticketRepository.findOne(id);
	}

	@Override
	public List<Ticket> findByProjection_idAndSeat(long projection_id, Integer seat) {
		return ticketRepository.findByProjection_idAndSeat(projection_id, seat);
	}

	@Override
	public List<Ticket> findByProjection_id(long projection_id) {
		return ticketRepository.findByProjection_id(projection_id);
	}

}
