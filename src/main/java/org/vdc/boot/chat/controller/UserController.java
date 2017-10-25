package org.vdc.boot.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vdc.boot.chat.dao.UserRespository;
import org.vdc.boot.chat.domain.User;
import org.vdc.boot.chat.exception.UserNotFoundException;

@RestController
@RequestMapping(path = "/user", consumes = "application/json", produces = "application/json")
public class UserController {

	UserRespository userRepository = null;

	@GetMapping(path = "/{userName}")
	public User getUser(@PathVariable("userName") String name) throws UserNotFoundException {

		User user = userRepository.findByName(name);
		if (user == null) {
			throw new UserNotFoundException("User not found!");
		} else {
			return user;
		}
	}

	@GetMapping
	public List<User> getAllUsers() throws UserNotFoundException {

		List<User> users = userRepository.findAll();
		return users;
	}

	@PostMapping
	public User createUser(User user) throws UserNotFoundException {

		User newUser = userRepository.save(user);
		if (newUser == null) {
			throw new UserNotFoundException("User not found!");
		} else {
			return user;
		}
	}


	@Autowired
	public UserController(UserRespository userRepository) {
		this.userRepository = userRepository;
	}

}
