package isa.projekat.domain;

import java.lang.annotation.Repeatable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "Poseta")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private User userVisit;

	@ManyToOne
	private Projection projectionVisit;

	@ManyToOne
	private Cinema cinemaVisit;
	
	@ManyToOne
	private Theater theaterVisit;

	private int cinemaRated;

	private int movieRated;
	
	private int theaterRated;
	
	private int showRated;

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

	public int getCinemaRated() {
		return cinemaRated;
	}

	public void setCinemaRated(int cinemaRated) {
		this.cinemaRated = cinemaRated;
	}

	public int getMovieRated() {
		return movieRated;
	}

	public void setMovieRated(int movieRated) {
		this.movieRated = movieRated;
	}

	public Visit() {
		this.cinemaRated = 0;
		this.movieRated = 0;
		this.theaterRated = 0;
		this.showRated = 0;
	}

	public Theater getTheaterVisit() {
		return theaterVisit;
	}

	public void setTheaterVisit(Theater theaterVisit) {
		this.theaterVisit = theaterVisit;
	}

	public int getTheaterRated() {
		return theaterRated;
	}

	public void setTheaterRated(int theaterRated) {
		this.theaterRated = theaterRated;
	}

	public int getShowRated() {
		return showRated;
	}

	public void setShowRated(int showRated) {
		this.showRated = showRated;
	}
	
	
}
