package isa.projekat.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class ProjectionKey implements Serializable {

	@Column(name="Datum")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="SalaId")
	private Long auditorium_id;	
	
	@Column(name="Termin", columnDefinition="VARCHAR(30)")
	private String term;

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
	
	
}
