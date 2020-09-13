package com.kshitiz.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.kshitiz.UserService.model.User;
import com.kshitiz.UserService.repo.UserRepository;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;


	@Bean
	public ModelMapper modelMapped() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		userRepository.save(new User("admin", "admin","admin","admin","admin","admin"));
	}

}
