package com.Book.projectBook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication

public class ProjectBookApplication implements CommandLineRunner {

	public static void main(String[] args) { SpringApplication.run(ProjectBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
