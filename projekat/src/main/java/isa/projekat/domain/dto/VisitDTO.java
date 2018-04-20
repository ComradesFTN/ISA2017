package isa.projekat.domain.dto;


public class VisitDTO {
	
	private long user;
	private long projection;
	
	public VisitDTO() {
		
	}
	
	public VisitDTO(long user, long projection) {
		this.user = user;
		this.projection = projection;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getProjection() {
		return projection;
	}

	public void setProjection(long projection) {
		this.projection = projection;
	}

	
	
	

}