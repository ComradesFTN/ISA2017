package isa.projekat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="ZvanicniOglas")
public class OfficialAd {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Slika", columnDefinition="VARCHAR(300)")
	private String image="Bez slike";
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;
	
	@Column(name="Cena", columnDefinition="DECIMAL(60,3)")
	private float price;
	
	@Column(name="Opis", columnDefinition="VARCHAR(200)")
	private String description;
	
	@Column(name="Rezervisan_od", columnDefinition="NUMERIC")
	private long reserved=0;
	
	public OfficialAd() {
	}

	public OfficialAd(long id, String image, String name, float price, String description, long reserved) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.price = price;
		this.description = description;
		this.reserved = reserved;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getReserved() {
		return reserved;
	}

	public void setReserved(long reserved) {
		this.reserved = reserved;
	}

}
