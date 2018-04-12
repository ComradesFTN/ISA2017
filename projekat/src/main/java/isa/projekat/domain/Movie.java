package isa.projekat.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="Film") 
public class Movie {  
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;
	
	@Column(name="Glumci", columnDefinition="VARCHAR(40)")
	private String actors;
	
	@Column(name="Zanr", columnDefinition="VARCHAR(40)")
	private String genre;
	
	@Column(name="ImeReditelja", columnDefinition="VARCHAR(40)")
	private String directorName;
	
	@Column(name="Rejting", columnDefinition="NUMERIC")
	private int rating=-1;
	
	@Column(name="PromotivniOpis", columnDefinition="VARCHAR(200)")
	private String description;
	
	@Column(name="Poster", columnDefinition="VARCHAR(200)")
	private String poster="Bez slike";
	
	@Column(name="Trajanje", columnDefinition="NUMERIC")
	private int length;
	
	@ElementCollection
	@CollectionTable(name = "SpisakSala",joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
	@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "sala_id"))
})
	List<Auditorium> auditoriums= new ArrayList<Auditorium>();
	
	@ElementCollection
	@CollectionTable(name = "SpisakTermina",joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
	@Column(name = "Termin", columnDefinition="VARCHAR(40)")
	List<String> term= new ArrayList<String>();
	
	@ElementCollection
	@CollectionTable(name = "SpisakProjekcijaFilma",joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
	@AttributeOverrides({
        @AttributeOverride(name = "SalaId", column = @Column(name = "sala_id")),
        @AttributeOverride(name = "Datum", column = @Column(name = "date")),
        @AttributeOverride(name = "Termin", column = @Column(name = "term"))
})
	List<Projection> projections= new ArrayList<Projection>();	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public void setAuditoriums(List<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	public List<String> getTerm() {
		return term;
	}

	public void setTerm(List<String> term) {
		this.term = term;
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public void setProjections(List<Projection> projections) {
		this.projections = projections;
	}
	
	
	
	
	
}
