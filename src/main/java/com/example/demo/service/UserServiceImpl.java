package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.jpa.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);

		User theUser = null;

		if (result.isPresent()) {
			theUser = result.get();
		} else {
			throw new RuntimeException("Did not find user id - " + theId);
		}
		return theUser;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	

}
