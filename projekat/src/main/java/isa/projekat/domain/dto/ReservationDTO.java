package isa.projekat.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationDTO {
	
	private long projectionId;
	private Integer seat;
	private long userId;
	private boolean friend;
	private List<Integer> seats = new ArrayList<Integer>();
	private long inviterId;
	
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

	
}
