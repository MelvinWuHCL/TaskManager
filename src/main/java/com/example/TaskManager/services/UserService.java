package com.example.TaskManager.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.UserRepository;
import com.example.TaskManager.exceptions.UserNotFoundException;

@Service
public class UserService {

	Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> GetAllUsers() {
		return userRepository.findAll();
	}

	public User getUserByName(String name) {

		Optional<User> foundUser = userRepository.findByName(name);
		log.info("searching for user...");

		if (!foundUser.isPresent()) {
			log.info("user not found...");
			throw new UserNotFoundException();
		}

		log.info("found user...");
		return (foundUser.get());
	}

	public boolean getPassword(String username, String password) {

		boolean status = false;
		User user = getUserByName(username);
		log.info("***" + user.getName() + "***");
		log.info("***" + user.getPassword() + "***");
		if (user.getPassword().equals(password)) {
			log.info("password match...");
			status = true;
		} else {
			log.info("no password match...");
			status = false;
		}
		return status;
	}

	public User save(User user) {

		return userRepository.save(user);
	}

}
