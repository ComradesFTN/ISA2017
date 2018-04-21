package isa.projekat.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import isa.projekat.domain.Auditorium;
import isa.projekat.domain.Movie;
import isa.projekat.domain.Projection;
import isa.projekat.domain.Show;
import isa.projekat.domain.Ticket;
import isa.projekat.repository.AuditoriumRepository;
import isa.projekat.repository.CinemaRepository;
import isa.projekat.repository.MovieRepository;
import isa.projekat.repository.ProjectionRepository;
import isa.projekat.repository.ShowRepository;
import isa.projekat.repository.TicketRepository;

@Service
public class ShowServiceImpl implements ShowService {

	@Value("${paths.menjaj}")
	private String putanja;
	
	@Autowired
	private ShowRepository showRepository;	
	
	@Autowired 
	private AuditoriumRepository auditoriumRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public List<Show> findAll() {
		return showRepository.findAll();
	}
	
	@Temporal(TemporalType.DATE)
	private Date date = new Date();

	@Override
	public Show save(Show show) {
		if(!show.getPoster().contains("Bez slike")) {			
			if(!show.getPoster().contains("imagesShows")) {
				if(this.findOne(show.getId())!=null) {
					if(this.findOne(show.getId()).getPoster().contains("Bez slike")) {
						String pathFile = putanja+"imagesShows\\film"+System.currentTimeMillis()+".jpg";
						decoder(show.getPoster(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						show.setPoster(splitPath[1]);
					}
					else {
						String pathFile = putanja+this.findOne(show.getId()).getPoster();
						decoder(show.getPoster(), pathFile);
						String splitPath[] = pathFile.split("static\\\\");
						show.setPoster(splitPath[1]);
					}
				}else {
					String pathFile = putanja+"imagesShows\\film"+System.currentTimeMillis()+".jpg";
					decoder(show.getPoster(), pathFile);
					String splitPath[] = pathFile.split("static\\\\");
					show.setPoster(splitPath[1]);
				}
			}
		}
		Show savedShow=showRepository.save(show);
		
		List<Projection> projections = projectionRepository.findAll();
		for(Projection proj : projections) {
			if(!proj.isMovie()){
				if(proj.getShow().getId()==savedShow.getId()){
					for(Ticket ticket : ticketRepository.findAll()){
						if(ticket.getProjection().getId()==proj.getId()){
							ticketRepository.delete(ticket);
						}
					}
					projectionRepository.delete(proj);
				}
			}
		}
		
		for(String term : savedShow.getTerm()){			
			for(Auditorium audit : savedShow.getAuditoriums()){
				for(int i=1; i<=10;i++){
					Projection proj = new Projection();
					proj.setAuditoriumId(audit.getId());
					proj.setDate(new Date(date.getTime() + TimeUnit.DAYS.toMillis( i )));
					proj.setShow(savedShow);
					proj.setTerm(term);
					List<Integer> seats = new ArrayList<Integer>();
					for(Integer seat : auditoriumRepository.findOne(audit.getId()).getSeats()){
						seats.add(seat);						
					}
					Projection saveProj=projectionRepository.save(proj);
					saveProj.setSeats(seats);
					Projection savedProj=projectionRepository.save(saveProj);
					for(int j=0;j<auditoriumRepository.findOne(audit.getId()).getSeats().size();j++){
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
		return savedShow;
	}

	@Override
	public Show findOne(Long id) {
		return showRepository.findOne(id);
	}

	public static void decoder(String base64Image, String pathFile) {
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			// Converting a Base64 String into Image byte array
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
	}
	
	@Override
	public Show delete(Long id) {
		Show show = showRepository.findOne(id);
		if(show == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant show");
		}
		List<Projection> projections = projectionRepository.findByShow_id(id);
		for(Projection projection : projections){
			ticketRepository.delete(projection.getTickets());
		}
		projectionRepository.delete(projections);
		showRepository.delete(show);
		return show;
	}

}
