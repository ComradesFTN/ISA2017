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

@Entity(name="Bioskop")
public class Cinema {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;
	
	@Column(name="Adresa", columnDefinition="VARCHAR(100)")
	private String adress;
	
	@Column(name="Rejting", columnDefinition="NUMERIC")
	private int rating;
	
	@Column(name="PromotivniOpis", columnDefinition="VARCHAR(200)")
	private String description;
	
	@ElementCollection
	@CollectionTable(name = "BioskopFilmovi",joinColumns = @JoinColumn(name = "cinema_id", referencedColumnName = "id"))
	@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "movie_id"))
})
	List<Movie> movies= new ArrayList<Movie>();
	
	@ElementCollection
	@CollectionTable(name = "BioskopSale",joinColumns = @JoinColumn(name = "cinema_id", referencedColumnName = "id"))
	@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "auditorium_id"))
})
	List<Auditorium> auditoriums= new ArrayList<Auditorium>();

	
	
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
	
	
}