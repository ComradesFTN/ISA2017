package isa.projekat.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="Pozoriste")
public class Theater {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;
	
	@Column(name="Adresa", columnDefinition="VARCHAR(100)")
	private String adress;
	
	@Column(name="GeografskaSirina", columnDefinition="DECIMAL(10,8)")
	private float myLat;
	
	@Column(name="GeografskaVisina", columnDefinition="DECIMAL(11,8)")
	private float myLang;
	
	@Column(name="Rejting", columnDefinition="NUMERIC")
	private int rating=-1;
	
	@Column(name="PromotivniOpis", columnDefinition="VARCHAR(200)")
	private String description;
	
	@ElementCollection
	@CollectionTable(name = "PozoristePredstave",joinColumns = @JoinColumn(name = "theater_id", referencedColumnName = "id"))
	@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "show_id"))
})
	Set<Show> shows= new HashSet<Show>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@CollectionTable(name = "PozoristeSale",joinColumns = @JoinColumn(name = "theater_id", referencedColumnName = "id"))
	@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "auditorium_id"))       
})
	Set<Auditorium> auditoriums= new HashSet<Auditorium>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "theaterVisit")
	@JsonBackReference
	private List<Visit> visits = new ArrayList<Visit>();

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

	public Set<Show> getShows() {
		return shows;
	}

	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}

	public Set<Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public void setAuditoriums(Set<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	public float getMyLat() {
		return myLat;
	}

	public void setMyLat(float myLat) {
		this.myLat = myLat;
	}

	public float getMyLang() {
		return myLang;
	}

	public void setMyLang(float myLang) {
		this.myLang = myLang;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}
	
	
}
