package com.electioncomission.ec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class EcApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		int t;
		SpringApplication.run(EcApplication.class, args);
	}

}
