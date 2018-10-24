package org.thesis.roulett.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Game;
import org.thesis.roulett.repository.GameRepository;
import org.thesis.roulett.service.GameService;

@Component
@ViewScoped
public class GetGamesView extends BaseView {
	
//	@Autowired
//	private GameRepository repository;
	
	@Autowired
	private GameService service;

	private List<Game> games;
	
	public GetGamesView() {
		super();
	}
	
	@PostConstruct
	public void init() {
//		games = repository.findAll();
		games = service.getAllGames();
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
}
