package isa.projekat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Cinema;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;
import isa.projekat.domain.Show;
import isa.projekat.domain.Theater;
import isa.projekat.domain.Ticket;
import isa.projekat.repository.AuditoriumRepository;
import isa.projekat.repository.MovieRepository;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.ShowRepository;
import isa.projekat.repository.TheaterRepository;
import isa.projekat.repository.TicketRepository;

@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;
		
	@Autowired 
	private AuditoriumRepository auditoriumRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Temporal(TemporalType.DATE)
	private Date date = new Date();
	
	@Override
	public List<Theater> findAll() {
		return theaterRepository.findAll();
	}

	@Override
	public Theater save(Theater theater) {
		return theaterRepository.save(theater);	
	}

	@Override
	public Theater findOne(Long id) {
		return theaterRepository.findOne(id);
	}

	public void refresh(Theater theater) {		
		Set<Show> shows = theater.getShows();
		for(Show show : shows){
			for(String term : show.getTerm()){			
				for(Auditorium audit : show.getAuditoriums()){
					for(int i=1; i<=10;i++){
						Projection proj = projectionRepository.findByShow_idAndDateAndAuditoriumIdAndTerm(show.getId(), new Date(date.getTime() + TimeUnit.DAYS.toMillis( i )), audit.getId(), term);
						if(proj==null){
							proj = new Projection();
							proj.setAuditoriumId(audit.getId());
							proj.setDate(new Date(date.getTime() + TimeUnit.DAYS.toMillis( i )));
							proj.setShow(show);
							proj.setTerm(term);
							List<Integer> seats = new ArrayList<Integer>();
							for(Integer seat : auditoriumRepository.findOne(audit.getId()).getSeats()){
								seats.add(seat);
							}
							proj.setSeats(seats);
							Projection savedProj=projectionRepository.save(proj);
							for(int j=0;i<auditoriumRepository.findOne(audit.getId()).getSeats().size();j++){
								Integer seat = auditoriumRepository.findOne(audit.getId()).getSeats().get(j);
								if(seat==3){
									Ticket ticket = new Ticket();
									int seatIndex=j+1;
									ticket.setSeat(seatIndex);
									ticket.setProjection(savedProj);
									ticketRepository.save(ticket);
								}
							}
						}
					}
				}
			}
		}
	}

}
