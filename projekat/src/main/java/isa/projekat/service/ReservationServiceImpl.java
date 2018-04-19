package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Projection;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.User;
import isa.projekat.domain.dto.ReservationDTO;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.ReservationRepository;
import isa.projekat.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findOne(Long id) {
		return reservationRepository.findOne(id);
	}

	@Override
	public Reservation save(ReservationDTO reservationDTO) {
		Projection projection = projectionRepository.findOne(reservationDTO.getProjectionId());
		User user = userRepository.findOne(reservationDTO.getUserId());
		Reservation reservation = new Reservation();
		reservation.setProjection(projection);
		reservation.setSeat(reservationDTO.getSeat());
		reservation.setUser(user);
		return reservationRepository.save(reservation);		
	}

}
