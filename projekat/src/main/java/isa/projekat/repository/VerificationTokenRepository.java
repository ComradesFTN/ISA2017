package isa.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import isa.projekat.domain.User;
import isa.projekat.domain.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	
	VerificationToken findByToken(String token);
	 
    VerificationToken findByUser(User user);

}
