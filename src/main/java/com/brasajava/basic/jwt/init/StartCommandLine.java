package com.brasajava.basic.jwt.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.brasajava.basic.jwt.repository.UserRepository;

@Component
public class StartCommandLine implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository; 
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("asdjfañsdjfañsdjfañsjdfñaj");
		System.out.println(userRepository.count());
	}
public StartCommandLine() {
	System.out.println("Initing Start Command Line");
}
}
