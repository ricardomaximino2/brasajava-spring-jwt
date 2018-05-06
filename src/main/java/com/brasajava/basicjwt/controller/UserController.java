package com.brasajava.basicjwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@GetMapping("/public/hello")
	public String hello() {
		return "Hello Public World";
	}

	@GetMapping("/security/hello")
	public String hello1() {
		return "Hello Security World";
	}
	
	@GetMapping("/admin/hello")
	public String hello2() {
		return "Hello Admin World";
	}
}
