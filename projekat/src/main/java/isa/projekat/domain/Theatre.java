package isa.projekat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Pozoriste")
public class Theatre {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Naziv", columnDefinition="VARCHAR(40)")
	private String name;
	
	@Column(name="Udaljenost", columnDefinition="VARCHAR(20)")
	private String distance;
	
	@Column(name="Rejting", columnDefinition="NUMERIC")
	private int rating;
}
