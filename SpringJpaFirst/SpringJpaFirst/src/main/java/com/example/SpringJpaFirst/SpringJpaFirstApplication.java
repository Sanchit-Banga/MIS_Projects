package com.example.SpringJpaFirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.SpringJpaFirst.repository.EmployeeRepository;
import com.example.SpringJpaFirst.service.EmployeeService;

@SpringBootApplication
@EntityScan("com.*")

public class SpringJpaFirstApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaFirstApplication.class, args);
	}


}
