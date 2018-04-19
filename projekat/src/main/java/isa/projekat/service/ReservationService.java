package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Reservation;
import isa.projekat.domain.dto.ReservationDTO;

public interface ReservationService {
	
	List<Reservation> findAll();

	Reservation save(ReservationDTO reservationDTO);

	Reservation findOne(Long id);

}