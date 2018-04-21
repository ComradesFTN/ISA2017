package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Projection;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.User;
import isa.projekat.domain.dto.ReservationDTO;

public interface ReservationService {
	
	List<Reservation> findAll();

	Reservation save(ReservationDTO reservationDTO);

	Reservation findOne(Long id);
	
	void Confirm(long userId, long inviterId);
	
	void Rejected(long userId, long inviterId, long reservationId);

	Boolean isEditable(Long id);
	
	List<Reservation> findByProjection_id(long projection);
	
	List<Reservation> findByUser_idAndProjection_id(long user, long projection);

	Reservation delete(Long id);

	Reservation save(Reservation reservation);
	
	List<Reservation> findByUser_id(long user);

	void Canceled(Long userId, Long reservationId);

}
