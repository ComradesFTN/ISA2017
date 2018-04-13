package isa.projekat.domain;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Projection {
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Long auditorium_id;	
	
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
