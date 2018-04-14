package isa.projekat.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="PolovniOglas")
public class UserAd {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Slika", columnDefinition="VARCHAR(300)")
	private String image="Bez slike";
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Datum", columnDefinition="DATE")
	private Date date;
	
	@Column(name="Opis", columnDefinition="VARCHAR(200)")
	private String description;
	
	@Column(name="Odobrenje", columnDefinition="BOOLEAN")
	private Boolean aproved=false;
	
	@Column(name="Napravljen_od", columnDefinition="NUMERIC")
	private long creatorId;

	@ElementCollection
	@CollectionTable(name = "Ponude",joinColumns = @JoinColumn(name = "userAd_id", referencedColumnName = "id"))
	@AttributeOverrides({
        @AttributeOverride(name = "userId", column = @Column(name = "Ponudjac")),
        @AttributeOverride(name = "price", column = @Column(name = "Cena")),
})
	Set<Bid> bids= new HashSet<Bid>();	
	
	
	public UserAd() {
	}

	public UserAd(long id, String image, String name, Date date, String description, Boolean aproved,
			long creatorId) {
		this.id = id;
		this.image = image;
		this.name = name;
		this.date = date;
		this.description = description;
		this.aproved = aproved;
		this.creatorId = creatorId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
	
	public void addBid(Bid bid) {
		bids.add(bid);
	}
}
