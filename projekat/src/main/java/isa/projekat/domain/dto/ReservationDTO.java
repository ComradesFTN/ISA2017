package isa.projekat.domain.dto;

public class ReservationDTO {
	
	private long projectionId;
	private long seat;
	private long userId;
	
	public ReservationDTO(long projectionId, long seat, long userId) {	
		this.projectionId = projectionId;
		this.seat = seat;
		this.userId = userId;
	}
	
	public ReservationDTO(){
		
	}

	public long getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(long projectionId) {
		this.projectionId = projectionId;
	}

	public long getSeat() {
		return seat;
	}

	public void setSeat(long seat) {
		this.seat = seat;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
}
