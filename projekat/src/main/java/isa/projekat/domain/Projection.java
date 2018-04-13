package isa.projekat.domain;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Projection {
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String auditorium_name;	
	
	private String term;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuditorium_id() {
		return auditorium_name;
	}

	public void setAuditorium_id(String auditorium_name) {
		this.auditorium_name = auditorium_name;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
}
