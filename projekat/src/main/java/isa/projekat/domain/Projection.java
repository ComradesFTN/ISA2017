package isa.projekat.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="Projekcija")
public class Projection {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datum")
	private Date date;
	
	@Column(name="sala_id")
	private long auditorium_id;	
	
	/*
	 *			PRAVILA ZA STATUSE MESTA: 
	 * 			0 - NEDOSTUPNO
	 *  		1 - SLOBODNO
	 * 			2 - REZERVISANO
	 */	
	@ElementCollection
	@CollectionTable(name = "ProjekcijaSalaMesta",joinColumns = @JoinColumn(name = "projekcija_id", referencedColumnName = "id"))
	@Column(name = "StatusMesta")
	List<Integer> seats= new ArrayList<Integer>();
	
	@ManyToOne
	private Movie movie;
	
	@Column(name="termin")
	private String term;	
	
	public Projection() {
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(long auditorium_id) {
		this.auditorium_id = auditorium_id;
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
	
	
	
}
