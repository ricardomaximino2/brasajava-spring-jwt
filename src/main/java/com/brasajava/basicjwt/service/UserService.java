package com.brasajava.basicjwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brasajava.basicjwt.model.User;
import com.brasajava.basicjwt.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	public User save(User user) { 
		return userRepository.save(user);
	}

	public boolean delete(long id) {
		boolean result = false;
		if(userRepository.findById(id) != null) {
			userRepository.deleteById(id);
			result= true;
		}
		return result;
	}

	public User getById(long id) {
		return userRepository.findById(id).get();
	}

	public List<User> getAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(user -> list.add(user));
		return list;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("username not Found");
		}
		return user;
	}

}
