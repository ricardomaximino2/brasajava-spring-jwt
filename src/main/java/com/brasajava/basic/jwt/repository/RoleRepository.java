package com.brasajava.basic.jwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.brasajava.basic.jwt.model.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {

}
