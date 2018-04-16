package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.Friendships;
import isa.projekat.domain.User;
import isa.projekat.domain.VerificationToken;

public interface UserService {


	List<User> findAll();

	User getUser(String verificationToken);

	void createVerificationToken(User user, String token);

	VerificationToken getVerificationToken(String VerificationToken);

	User save(User user);

	List<User> save(List<User> users);
	
	User getUserByEmail(String email);
	
	User findUserByFirstName(String firstName);
	
	User updateUser(User user);
	
	Friendships save(Friendships friendship);
	
	Friendships updateFriendship(Friendships friendship);
	
	List<Friendships> findAllFriendships();
	
	User findOne(Long id);
	
	Friendships delete(Long id);
}
