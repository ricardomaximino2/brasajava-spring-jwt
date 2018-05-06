package com.brasajava.basicjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages= {"com.brasajava.basicjwt"})
public class BrasajavaBasicJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrasajavaBasicJwtApplication.class, args);
	}
}
