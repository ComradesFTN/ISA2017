package isa.projekat.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	
	
	
	
	
}
