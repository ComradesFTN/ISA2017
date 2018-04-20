package isa.projekat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Poseta")
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JsonManagedReference
	private User userVisit;

	@ManyToOne
	@JsonManagedReference
	private Projection projectionVisit;

	@ManyToOne
	@JsonManagedReference
	private Cinema cinemaVisit;

	private boolean cinemaRated;

	private boolean movieRated;

	public Projection getProjectionVisit() {
		return projectionVisit;
	}

	public void setProjectionVisit(Projection projectionVisit) {
		this.projectionVisit = projectionVisit;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUserVisit() {
		return userVisit;
	}

	public void setUserVisit(User userVisit) {
		this.userVisit = userVisit;
	}

	public Cinema getCinemaVisit() {
		return cinemaVisit;
	}

	public void setCinemaVisit(Cinema cinemaVisit) {
		this.cinemaVisit = cinemaVisit;
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
