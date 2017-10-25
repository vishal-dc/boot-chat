package org.vdc.boot.chat.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.vdc.boot.chat.domain.User;

public interface UserRespository extends CrudRepository<User, Long> {

	List<User> findAll();

	User findByName(String name);
	
	User findByNameAndPassword(String name, String password);
	
}
