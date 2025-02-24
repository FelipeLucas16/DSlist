package com.devffl.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devffl.dslist.dto.GameListDTO;
import com.devffl.dslist.dto.GameMinDTO;
import com.devffl.dslist.services.GameListService;
import com.devffl.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
    @GetMapping
	public List<GameListDTO> findAll(){
		List<GameListDTO> result = gameListService.finAll();
		return result;
	}
    
    @GetMapping(value = "/{listId}/games")
  	public List<GameMinDTO> findByList(@PathVariable Long listId){
  		List<GameMinDTO> result = gameService.findbyList(listId);
  		return result;
  	}
}
