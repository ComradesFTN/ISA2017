package isa.projekat.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDTO {
	
	private long projectionId;
	private Integer seat;
	private long userId;
	private boolean friend;
	private List<Integer> seats = new ArrayList<Integer>();
	private long inviterId;
	private String projectionName;
	private Date date;
	private String term;
	private String placeName;
	private String auditoriumName;
	private long id;
	
	public ReservationDTO(long projectionId, Integer seat, long userId, boolean friend, List<Integer> seats ) {	
		this.projectionId = projectionId;
		this.seat = seat;
		this.userId = userId;
		this.friend = friend;
		this.seats= seats;		
	}
	
	public ReservationDTO(){
		
	}
		
	public long getInviterId() {
		return inviterId;
	}

	public void setInviterId(long inviterId) {
		this.inviterId = inviterId;
	}

	public boolean isFriend() {
		return friend;
	}

	public void setFriend(boolean friend) {
		this.friend = friend;
	}

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

	public long getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(long projectionId) {
		this.projectionId = projectionId;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
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

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getAuditoriumName() {
		return auditoriumName;
	}

	public void setAuditoriumName(String auditoriumName) {
		this.auditoriumName = auditoriumName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
