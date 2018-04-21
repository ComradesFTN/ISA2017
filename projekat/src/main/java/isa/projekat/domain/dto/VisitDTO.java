package isa.projekat.domain.dto;


public class VisitDTO {
	
	private long id;
	private long user;
	private long projection;
	private String cinemaName;
	private String theaterName;	
	
	public VisitDTO() {
		
	}
	
	public VisitDTO(long id, long user, long projection, String cinemaName) {
		this.setId(id);
		this.user = user;
		this.projection = projection;
		this.cinemaName = cinemaName;
	}
	
	public VisitDTO(long id, long user, long projection) {
		this.setId(id);
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

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

}
