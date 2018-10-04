package org.thesis.roulett.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thesis.roulett.model.Game;
import org.thesis.roulett.repository.GameRepository;

@Component
@ViewScoped
public class GetGamesView extends BaseView {

	private List<Game> games;
	
	@Autowired
	private GameRepository repository;
	
	public GetGamesView() {
		super();
	}
	
	@PostConstruct
	public void init() {
		games = repository.findAll();
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
}
