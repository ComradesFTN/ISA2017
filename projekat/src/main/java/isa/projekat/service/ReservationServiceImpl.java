package isa.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;
import isa.projekat.domain.Reservation;
import isa.projekat.domain.Theater;
import isa.projekat.domain.User;
import isa.projekat.domain.dto.ReservationDTO;
import isa.projekat.repository.AuditoriumRepository;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.ReservationRepository;
import isa.projekat.repository.TheaterRepository;
import isa.projekat.repository.UserRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private TheaterRepository theaterRepository;
	
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
			Theater theaterShow = new Theater();
			if(projection.isMovie()){				
				for(Cinema cinema : cinemaRepository.findAll()){
					if(cinema.getMovies().contains(projection.getMovie())){
						cinemaMovie=cinema;
						break;
					}
				}
			}
			else{
				for(Theater theater : theaterRepository.findAll()){
					if(theater.getShows().contains(projection.getShow())){
						theaterShow=theater;
						break;
					}
				}
			}
			String auditoriumName=auditoriumRepository.findOne(projection.getAuditoriumId()).getName();
			User inviter = userRepository.findOne(reservationDTO.getInviterId());
			if(projection.isMovie()){
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
			else{
				try {
					emailService.sendReservationInvite(user, newReservation, inviter, theaterShow, auditoriumName);
				} catch (MailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
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
		int index = reservation.getSeat()-1;
		projection.getSeats().set(index, 1);
		projection.getReservations().remove(reservation);
		projectionRepository.save(projection);
		user.getReservations().remove(reservation);
		userRepository.save(user);
		reservationRepository.delete(reservation);
		emailService.sendReservationInviteRejected(user, inviter);
	}

	@Override
	public Boolean isEditable(Long id) {
		List<Reservation> reservations = this.findAll();
		for(Reservation reservation : reservations){
			if(reservation.getProjection().isMovie()){
				if(reservation.getProjection().getMovie().getId()==id){
					return false;
				}
			}
			else{
				if(reservation.getProjection().getShow().getId()==id){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<Reservation> findByProjection_id(long projection) {
		return reservationRepository.findByProjection_id(projection);
	}

	@Override
	public List<Reservation> findByUser_idAndProjection_id(long user, long projection) {
		return reservationRepository.findByUser_idAndProjection_id(user, projection);
	}
	
	@Override
	public Reservation delete(Long id) {
		Reservation reservation = reservationRepository.findOne(id);
		if(reservation == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant reservation");
		}
		reservationRepository.delete(reservation);
		return reservation;
	}
	
	@Override
	public Reservation save(Reservation reservation){
		return reservationRepository.save(reservation);
		
	}

	@Override
	public List<Reservation> findByUser_id(long user) {
		return reservationRepository.findByUser_id(user);
	}

	@Override
	public void Canceled(Long userId, Long reservationId) {
		User user = userRepository.findOne(userId);
		Reservation reservation=reservationRepository.findOne(reservationId);
		Projection projection = reservation.getProjection();
		int index = reservation.getSeat()-1;
		Auditorium auditorium = auditoriumRepository.findOne(projection.getAuditoriumId());
		int seat=auditorium.getSeats().get(index);
		projection.getSeats().set(index, seat);
		projection.getReservations().remove(reservation);
		projectionRepository.save(projection);
		user.getReservations().remove(reservation);
		userRepository.save(user);
		reservationRepository.delete(reservation);
	}

}
