package org.vdc.boot.chat.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vdc.boot.chat.dao.UserRespository;
import org.vdc.boot.chat.domain.LoginForm;
import org.vdc.boot.chat.domain.User;
import org.vdc.boot.chat.exception.UserNotFoundException;

@RestController
@RequestMapping(consumes = "application/json", produces = "application/json")
public class LoginController {
	public UserRespository userRepository;

	public LoginController(UserRespository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping(path = "/login")
	public User login(@PathVariable("userName") String name, LoginForm login) throws UserNotFoundException {

		User user = userRepository.findByNameAndPassword(name, login.getPassword());
		if (user == null) {
			throw new UserNotFoundException("User not found!");
		} else {
			return user;
		}

	}

	@PostMapping(path = "/logout")
	public void logout(@PathVariable("userName") String name) {

	}
}