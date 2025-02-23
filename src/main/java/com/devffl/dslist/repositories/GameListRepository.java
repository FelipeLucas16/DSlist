package com.devffl.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devffl.dslist.entities.Game;
import com.devffl.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{

	
}
