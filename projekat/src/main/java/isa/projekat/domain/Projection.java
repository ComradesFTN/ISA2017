package isa.projekat.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="SveProjekcije")
public class Projection {
	
	@EmbeddedId
	private ProjectionKey projectionKey;
	
	@ElementCollection
	@CollectionTable(name = "mesta_u_sali")
	@Column(name = "Mesto")
	List<Boolean> seat= new ArrayList<Boolean>();	
		
}
