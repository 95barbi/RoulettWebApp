package org.joinfaces.example;

import org.joinfaces.example.model.Player;
import org.joinfaces.example.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JoinFacesExampleApplication implements CommandLineRunner {

	@Autowired
	private PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(JoinFacesExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Populator population = new Populator();

		Player player = new Player(1L, "Borbely Balint", "95barbi", "95barbi@gmail.com", "123456", 10000, "ADMIN");
		playerRepository.save(player);

		player = new Player(2L, "Gyopos Marcel", "gyopi", "gyopi@gmail.com", "123456", 10, "USER");
		playerRepository.save(player);
	}
}
