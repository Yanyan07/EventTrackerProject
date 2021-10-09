package com.skilldistillery.hiker.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skilldistillery.hiker.entities.Hiker;
import com.skilldistillery.hiker.services.HikerService;

@RestController
@RequestMapping("api")
public class HikerController {
	
	@Autowired
	private HikerService hikerSvc;
	
	@GetMapping("hikers")
	public List<Hiker> hikerIndex(){
		return hikerSvc.getAllHikers();
	}

	
}
