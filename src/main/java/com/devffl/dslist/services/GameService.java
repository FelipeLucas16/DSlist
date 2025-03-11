package com.devffl.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devffl.dslist.dto.GameDTO;
import com.devffl.dslist.dto.GameMinDTO;
import com.devffl.dslist.entities.Belonging;
import com.devffl.dslist.entities.BelongingPK;
import com.devffl.dslist.entities.Game;
import com.devffl.dslist.entities.GameList;
import com.devffl.dslist.projections.GameMinProjection;
import com.devffl.dslist.repositories.BelongingRepository;
import com.devffl.dslist.repositories.GameListRepository;
import com.devffl.dslist.repositories.GameRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private GameListRepository gameListRepository;
	
	 @Autowired
	private BelongingRepository belongingRepository;

	
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
	    // Verifica se a categoria já existe no banco de dados
	    GameList gameList = gameListRepository.findByName(gameDTO.getCategory())
	        .orElseGet(() -> {
	            GameList newGameList = new GameList();
	            newGameList.setName(gameDTO.getCategory());
	            return gameListRepository.save(newGameList); 
	        });

	    // Criar o novo jogo e associá-lo à categoria encontrada/criada
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
	    
	    // Criar a associação usando a tabela intermediária Belonging
	    Belonging belonging = new Belonging();

	    BelongingPK belongingPK = new BelongingPK();
	    belongingPK.setGame(game);  
	    belongingPK.setList(gameList); 

	    belonging.setId(belongingPK);  
	    belonging.setPositiion(1);  

	    belongingRepository.save(belonging); 
	    
	    return new GameDTO(game);
	}


	@Transactional
	public String deleteGame(Long id) {
        // Verifica se o jogo existe antes de excluir
        Game game = gameRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Jogo com ID " + id + " não encontrado.")
        );

        System.out.println("Excluindo jogo: " + game.getTitle());
        gameRepository.delete(game);
        return "Jogo com ID " + id + " foi excluído com sucesso.";
    }
	
	@Transactional
	public GameDTO updateGame(Long id, GameDTO gameDTO) {
	    // Buscar o jogo pelo ID
	    Game game = gameRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Jogo com ID " + id + " não encontrado."));

	    // Atualizar os dados do jogo
	    game.setTitle(gameDTO.getTitle());
	    game.setYear(gameDTO.getYear());
	    game.setGenre(gameDTO.getGenre());
	    game.setPlatforms(gameDTO.getPlatforms());
	    game.setScore(gameDTO.getScore());
	    game.setImgUrl(gameDTO.getImgUrl());
	    game.setShortDescription(gameDTO.getShortDescription());
	    game.setLongDescription(gameDTO.getLongDescription());

	    // Atualizar categoria (GameList)
	    if (gameDTO.getCategory() != null) {
	        GameList gameList = gameListRepository.findByName(gameDTO.getCategory())
	                .orElseGet(() -> {
	                    GameList newGameList = new GameList();
	                    newGameList.setName(gameDTO.getCategory());
	                    return gameListRepository.save(newGameList);
	                });

	        // Criar ou atualizar a relação entre o jogo e a categoria
	        Belonging belonging = new Belonging(game, gameList, 0);

	        belongingRepository.save(belonging);
	    } 

	    // Salvar a atualização no banco de dados
	    game = gameRepository.save(game);

	    // Retornar o jogo atualizado
	    return new GameDTO(game);
	}
	
}