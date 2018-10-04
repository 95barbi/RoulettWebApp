package org.thesis.roulett.view;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Player;
import org.thesis.roulett.repository.PlayerRepository;

@Component
@ViewScoped
public class LoginRegisterView extends BaseView {

	private String username;
	private String password;
	private String email;
	private String name;
	private int balance;
	
	private static Player player;
	
	private static Long id;
	
	@Autowired
	private PlayerRepository repository;
	
	public LoginRegisterView() {
		super();
	}

	public LoginRegisterView(String username, String password, String email, String name, int balance,
			PlayerRepository repository) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.balance = balance;
		this.repository = repository;
	}
	
	@PostConstruct
	public void init() {
		player = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public PlayerRepository getRepositor() {
		return repository;
	}

	public void setRepositor(PlayerRepository repository) {
		this.repository = repository;
	}
	
	public static Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String login() {
		player = repository.findPlayerIfRegistered(username, password);
		
		if (player != null) {
			if (player.getRole().equals("ADMIN")) {
				return this.redirect("adminHomePage");
			} else {
				System.out.println("loggedInPlayer: " + player.toString());
				id = player.getId();
				return this.redirect("userHomePage");
			}
		} else {
			this.error("Incorrect username or password!", "Incorrect username or password");
		}
		
		return null;
	}
	
	public String register() {
		player = new Player(name, username, email, password, balance, "USER");
		repository.save(player);
		
		return this.redirect("userHomePage");
	}

	public static Long getId() {
		return id;
	}

	public static void setId(Long id) {
		LoginRegisterView.id = id;
	}
	
}
