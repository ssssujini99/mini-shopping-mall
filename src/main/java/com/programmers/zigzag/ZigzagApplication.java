package com.programmers.zigzag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ZigzagApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZigzagApplication.class, args);
	}

}
