package com.example.spectacle;

import com.example.spectacle.service.CommentaireServiceInt;
import com.example.spectacle.service.SpectacleServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpectacleApplication implements CommandLineRunner {

	@Autowired
	SpectacleServiceInt spectacleServiceInt;
	@Autowired
	CommentaireServiceInt commentaireServiceInt;

	public static void main(String[] args) {
		SpringApplication.run(SpectacleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		spectacleServiceInt.initSpectacle();
		commentaireServiceInt.initCommentaire();
	}
}
