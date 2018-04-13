package isa.projekat.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

@Entity(name = "Korisnik")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Email")
	private String email;

	@Column(name = "Lozinka")
	private String password;

	@Column(name = "Ime")
	private String firstName;

	@Column(name = "Prezime")
	private String lastName;

	@Column(name = "Grad")
	private String city;

	@Column(name = "BrojTelefona")
	private String phone;

	@Column(name = "Potvrdjen")
	private boolean enabled;
	
	@ElementCollection
	@CollectionTable(name = "ListaPrijatelja",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	Set<User> friendsList = new HashSet<User>();
	
	public User() {
		this.enabled = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<User> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(Set<User> friendsList) {
		this.friendsList = friendsList;
	}
	

}
