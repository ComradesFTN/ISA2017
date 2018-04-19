package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Cinema;
import isa.projekat.domain.Projection;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.User;
import isa.projekat.domain.dto.ReservationDTO;
import isa.projekat.repository.AuditoriumRepository;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.ReservationRepository;
import isa.projekat.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private AuditoriumRepository auditoriumRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
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
		projection.setSeats(reservationDTO.getSeats());
		projectionRepository.save(projection);				
		Reservation newReservation = reservationRepository.save(reservation);	
		if(reservationDTO.isFriend()){
			Cinema cinemaMovie = new Cinema();
			for(Cinema cinema : cinemaRepository.findAll()){
				if(cinema.getMovies().contains(projection.getMovie())){
					cinemaMovie=cinema;
					break;
				}
			}
			String auditoriumName=auditoriumRepository.findOne(projection.getAuditoriumId()).getName();
			User inviter = userRepository.findOne(reservationDTO.getInviterId());
			try {
				emailService.sendReservationInvite(user, newReservation, inviter, cinemaMovie, auditoriumName);
			} catch (MailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return newReservation;
	}

	@Override
	public void Confirm(long userId, long inviterId) {		
		User user = userRepository.findOne(userId);
		User inviter = userRepository.findOne(inviterId);
		emailService.sendReservationInviteConfirmed(user, inviter);
	}

	@Override
	public void Rejected(long userId, long inviterId, long reservationId) {
		User user = userRepository.findOne(userId);
		User inviter = userRepository.findOne(inviterId);
		Reservation reservation=reservationRepository.findOne(reservationId);
		Projection projection = reservation.getProjection();
		int index = (int) (reservation.getSeat()-1);
		projection.getSeats().set(index, 1);
		projection.getReservations().remove(reservation);
		projectionRepository.save(projection);
		user.getReservations().remove(reservation);
		userRepository.save(user);
		reservationRepository.delete(reservation);
		emailService.sendReservationInviteRejected(user, inviter);
	}

}
