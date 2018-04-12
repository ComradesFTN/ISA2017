package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import isa.projekat.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	
	//@Modifying
	//@Query("update Korisnik u set u.email = ?2, u.lozinka = ?3, u.ime = ?4, u.prezime = ?5, u.grad = ?6, u.broj_telefona= ?7 where u.id = ?1")
	//public User updateUser(long id, String email, String password, String firstName, String lastName, String city, String phone);
}
