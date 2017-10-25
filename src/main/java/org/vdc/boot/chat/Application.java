package org.vdc.boot.chat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.vdc.boot.chat.dao.UserRespository;
import org.vdc.boot.chat.domain.Address;
import org.vdc.boot.chat.domain.User;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	/**
	 * jar
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		SpringApplication.run(Application.class, args);
	}

	/**
	 * war
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

//	@Autowired
//	UserDao userDao = null;
//
//	@PostConstruct
//	public void init() {
//		User user = new User("Vishal", new Date(1990, 5, 3));
//		Set<Address> addresses = new HashSet<>();
//		addresses.add(new Address("Pune", "Kothrud", "Bhelkenagar"));
//		user.setAddresses(addresses);
//
//		userDao.save(user);
//
//	}

}
