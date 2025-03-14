package com.devffl.dslist.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devffl.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{

	@Modifying
	@Query(nativeQuery = true, 
	value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
	void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
	
	 Optional<GameList> findByName(String name);  // Método para buscar pelo nome

}
