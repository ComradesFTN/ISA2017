package isa.projekat.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="Projekcija")
public class Projection {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datum")
	private Date date;
	
	@Column(name="sala_id")
	private long auditoriumId;	
	
	/*
	 *			PRAVILA ZA STATUSE MESTA: 
	 * 			0 - NEDOSTUPNO
	 *  		1 - SLOBODNO
	 * 			2 - REZERVISANO
	 */	
	@ElementCollection
	@JsonIgnore
	@CollectionTable(name = "ProjekcijaSalaMesta",joinColumns = @JoinColumn(name = "projekcija_id", referencedColumnName = "id"))
	@Column(name = "StatusMesta")
	List<Integer> seats= new ArrayList<Integer>();
	
	@ManyToOne
	@JsonIgnoreProperties("projections")
	private Movie movie;
	
	@ManyToOne
	@JsonIgnoreProperties("projections")
	private Show show;
	
	@Column(name="termin")
	private String term;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection")
	@JsonIgnoreProperties("projection")
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectionVisit")
	@JsonBackReference
	private List<Visit> visits = new ArrayList<Visit>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection")
	@JsonIgnoreProperties("projection")
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
	private boolean old = false;
	
	private boolean isMovie;
	
	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Projection() {
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public long getAuditoriumId() {
		return auditoriumId;
	}

	public void setAuditoriumId(long auditoriumId) {
		this.auditoriumId = auditoriumId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public boolean getOld() {
		return old;
	}

	public void setOld(boolean old) {
		this.old = old;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public boolean isMovie() {
		return isMovie;
	}

	public void setMovie(boolean isMovie) {
		this.isMovie = isMovie;
	}
	
	
}
