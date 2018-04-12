package isa.projekat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="PolovniOglas")
public class UserAd {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Slika", columnDefinition="VARCHAR(300)")
	private String image="Bez slike";
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;
	
	@Column(name="Datum", columnDefinition="DATE")
	private String date;
	
	@Column(name="Opis", columnDefinition="VARCHAR(200)")
	private String description;
	
	@Column(name="Odobrenje", columnDefinition="BOOLEAN")
	private Boolean aproved=false;

	public UserAd() {
	}

	public UserAd(long id, String image, String name, String date, String description, Boolean aproved) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.date = date;
		this.description = description;
		this.aproved = aproved;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getAproved() {
		return aproved;
	}

	public void setAproved(Boolean aproved) {
		this.aproved = aproved;
	}
	
}
