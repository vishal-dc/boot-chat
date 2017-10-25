package org.vdc.boot.chat.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vdc.boot.chat.dao.UserRespository;
import org.vdc.boot.chat.domain.User;

@RestController
@EnableAutoConfiguration
public class HelloWorldController {

	@Autowired
	UserRespository	userDao = null;
	
	@RequestMapping("/hi")
	public  List<User> hi() {
		return userDao.findAll();
	}

	@PostConstruct
	public void init() {

	}
}
