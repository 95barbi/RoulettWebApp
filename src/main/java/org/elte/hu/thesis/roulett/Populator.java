package org.elte.hu.thesis.roulett;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.elte.hu.thesis.roulett.model.Game;
import org.elte.hu.thesis.roulett.model.Player;
import org.elte.hu.thesis.roulett.repository.GameRepository;
import org.elte.hu.thesis.roulett.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Populator {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	private List<String> firstnames;
	private List<String> lastnames;
	private List<String> usernames;

	private Random random;

	public Populator() {
		super();
		
		random = new Random();
	}
	
	public LocalDate randomDate() {
		return LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 70))));
	}

	@PostConstruct
	public void init() {
		firstnames = Arrays.asList("B�lint", "Kata", "Jani", "Feri", "Viktor", "Bal�zs", "Attila", "G�bor", "Andr�s", "Marci",
				"�gi", "S�ri", "Eszter", "R�ka");
		lastnames = Arrays.asList("Kov�cs", "Kocsis", "Szab�", "Borb�ly", "Nagy", "Honti", "Gyop�r", "T�szegi", "Arat�");

		usernames = Arrays.asList("vaulter", "changtse", "kyanite", "carefree", "rem", "frosty", "boxing", "chowder",
				"rocking", "pussface", "endless", "arede", "rentot");

		for (int i = 0; i < 40; ++i) {
			String name = lastnames.get(random.nextInt(lastnames.size())) + " "
					+ firstnames.get(random.nextInt(firstnames.size()));
			String username = usernames.get(random.nextInt(usernames.size()));
			String password = usernames.get(random.nextInt(usernames.size()));
			String email = username + "@gmail.com";
			int balance = random.nextInt(1000);
			
			Player player = new Player(name, username, email, password, balance, "USER");
			
			System.out.println("Player: " + player.toString());

			playerRepository.save(player);
		}

		for (int j=0; j<50; ++j) {
			boolean isLost = random.nextBoolean();
			String date = randomDate().toString();
			int bet = random.nextInt(1000);
			
			Player player = playerRepository.findAll().get(random.nextInt(playerRepository.findAll().size()));
			Game game = new Game(player.getName(), date, isLost, bet, player);
			
			gameRepository.save(game);
		}
		
	}
}
