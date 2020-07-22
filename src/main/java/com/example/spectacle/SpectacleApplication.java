package com.example.spectacle;

import com.example.spectacle.controller.BackOfficeController;
import com.example.spectacle.service.CommentaireService;
import com.example.spectacle.service.SpectacleService;
import com.example.spectacle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;

@SpringBootApplication
@ComponentScan({"com.example.spectacle","controller"})
public class SpectacleApplication implements CommandLineRunner {

	@Autowired
	SpectacleService spectacleService;
	@Autowired
	CommentaireService commentaireService;
	@Autowired
    UserService userService;

	public static void main(String[] args) {
		new File(BackOfficeController.uploadDirectory).mkdir();
		SpringApplication.run(SpectacleApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		spectacleService.initSpectacle();
		commentaireService.initCommentaire();
		userService.initUtilisateur();
		userService.initAdmin();

	}
}
