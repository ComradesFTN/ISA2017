package isa.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Projection;
import isa.projekat.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findByProjection_idAndSeat(long projection_id,Integer seat);
	
	List<Ticket> findByProjection_id(long projection_id);
	
}
