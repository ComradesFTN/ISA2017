package isa.projekat.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="Sale")
public class Auditorium {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JsonIgnoreProperties("auditoriums")
	@JoinTable(name = "FilmSale",joinColumns = @JoinColumn(name = "auditorium_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
	List<Movie> movies= new ArrayList<Movie>();
	
	@Column(name="BrojRedova")
	private long rows;
	
	@Column(name="BrojKolona")
	private long columns;
	
	
	/*
	 *			PRAVILA ZA STATUSE MESTA: 
	 * 			0 - NEDOSTUPNO
	 *  		1 - SLOBODNO
	 * 			2 - REZERVISANO
	 */	
	@ElementCollection
	@CollectionTable(name = "SalaMesta",joinColumns = @JoinColumn(name = "auditorium_id", referencedColumnName = "id"))
	@Column(name = "StatusMesta")
	List<Integer> seats= new ArrayList<Integer>(); 	
		
	public Auditorium(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getColumns() {
		return columns;
	}

	public void setColumns(long columns) {
		this.columns = columns;
	}

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;		
	}
	
	
	
}
