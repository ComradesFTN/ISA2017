package isa.projekat.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="Projekcije")
public class Projection {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Datum")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="SalaId")
	private Long auditorium_id;	
	
	@Column(name="Termin", columnDefinition="VARCHAR(30)")
	private String term;
	
	@ElementCollection
	@CollectionTable(name = "mesta_u_sali")
	@Column(name = "Mesto")
	Set<Boolean> seat= new HashSet<Boolean>();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(Long auditorium_id) {
		this.auditorium_id = auditorium_id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Set<Boolean> getSeat() {
		return seat;
	}

	public void setSeat(Set<Boolean> seat) {
		this.seat = seat;
	}	
	
	
		
}
