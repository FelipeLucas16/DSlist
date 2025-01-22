package com.devffl.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devffl.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

	
}
