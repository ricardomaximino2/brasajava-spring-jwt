package com.brasajava.basicjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.brasajava.basicjwt.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);

}
