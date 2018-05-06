package com.brasajava.basic.jwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.brasajava.basic.jwt.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);

}
