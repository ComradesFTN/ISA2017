package isa.projekat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Poseta")
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JsonIgnoreProperties("visits")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("visits")
	private Projection projection;
	
	@ManyToOne
	@JsonIgnoreProperties("visits")
	private Cinema cinema;
	
	private boolean cinemaRated;
	
	private boolean movieRated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
			
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public boolean isCinemaRated() {
		return cinemaRated;
	}

	public void setCinemaRated(boolean cinemaRated) {
		this.cinemaRated = cinemaRated;
	}

	public boolean isMovieRated() {
		return movieRated;
	}

	public void setMovieRated(boolean movieRated) {
		this.movieRated = movieRated;
	}

	public Visit() {
		this.cinemaRated = false;
		this.movieRated = false;
	}
}
