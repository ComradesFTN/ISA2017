package isa.projekat.domain.dto;

import java.util.Date;

public class TicketDTO {

	private Integer seat;
	private Date date;
	private String term;
	private String auditoriumName;
	private String projectionName;
	private int price;
	private long id;
	private String poster;
	
	public TicketDTO(long id, Integer seat, Date date, String term, String auditoriumName, String projectionName, int price,String poster) {
		this.id=id;
		this.seat = seat;
		this.date = date;
		this.term = term;
		this.auditoriumName = auditoriumName;
		this.projectionName = projectionName;
		this.price = price;
		this.poster= poster;
	}
	public TicketDTO() {
		
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getAuditoriumName() {
		return auditoriumName;
	}
	public void setAuditoriumName(String auditoriumName) {
		this.auditoriumName = auditoriumName;
	}
	public String getProjectionName() {
		return projectionName;
	}
	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	
	
	
}
