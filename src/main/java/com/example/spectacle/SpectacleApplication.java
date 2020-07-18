package com.example.spectacle;

import com.example.spectacle.service.AdminService;
import com.example.spectacle.service.CommentaireService;
import com.example.spectacle.service.SpectacleService;
import com.example.spectacle.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpectacleApplication implements CommandLineRunner {

	@Autowired
	SpectacleService spectacleService;
	@Autowired
	CommentaireService commentaireService;
	@Autowired
	UtilisateurService utilisateurService;
	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
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
		utilisateurService.initUtilisateur();
		adminService.initAdmin();

	}
}
