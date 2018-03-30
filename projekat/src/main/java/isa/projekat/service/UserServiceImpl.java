package isa.projekat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.projekat.domain.User;
import isa.projekat.domain.VerificationToken;
import isa.projekat.repository.UserRepository;
import isa.projekat.repository.VerificationTokenRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> save(List<User> users) {
		return userRepository.save(users);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String verificationToken) {
		User user = tokenRepository.findByToken(verificationToken).getUser();
        return user; 
	}

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken myToken = new VerificationToken(user, token);
        tokenRepository.save(myToken);
		
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		
		return tokenRepository.findByToken(VerificationToken);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);

	}

}
