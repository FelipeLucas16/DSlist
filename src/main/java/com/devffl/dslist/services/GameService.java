package com.devffl.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devffl.dslist.dto.GameDTO;
import com.devffl.dslist.dto.GameMinDTO;
import com.devffl.dslist.entities.Game;
import com.devffl.dslist.projections.GameMinProjection;
import com.devffl.dslist.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id).get();
		GameDTO dto = new GameDTO(result);
		return dto;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> finAll(){
		List<Game> result = gameRepository.findAll();
		List<GameMinDTO> dto = result.stream().map(x -> new GameMinDTO(x)).toList();
		return dto;
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findbyList(Long listId){
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	}

	@Transactional
	public GameDTO addGame(GameDTO gameDTO) {
        Game game = new Game();
        game.setTitle(gameDTO.getTitle());
        game.setYear(gameDTO.getYear());
        game.setGenre(gameDTO.getGenre());
        game.setPlatforms(gameDTO.getPlatforms());
        game.setScore(gameDTO.getScore());
        game.setImgUrl(gameDTO.getImgUrl());
        game.setShortDescription(gameDTO.getShortDescription());
        game.setLongDescription(gameDTO.getLongDescription());

        game = gameRepository.save(game);
        return new GameDTO(game);  // Retorna o jogo salvo como DTO
    }

	@Transactional
	public String deleteGame(Long id) {
        // Verifica se o jogo existe antes de excluir
        Game game = gameRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Jogo com ID " + id + " não encontrado.")
        );

        System.out.println("Excluindo jogo: " + game.getTitle()); // Log para debug
        gameRepository.delete(game);
        return "Jogo com ID " + id + " foi excluído com sucesso.";
    }
}
 