package isa.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.Projection;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	List<Reservation> findByUser_id(long user);
	
	List<Reservation> findByUser_idAndProjection_id(long user, long projection);
}
