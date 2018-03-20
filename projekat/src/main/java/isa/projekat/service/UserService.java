package isa.projekat.service;

import java.util.List;

import isa.projekat.domain.User;

public interface UserService {
	
	List<User> findAll();
	
	User save(User user);
	
	List<User> save(List<User> users);
}
