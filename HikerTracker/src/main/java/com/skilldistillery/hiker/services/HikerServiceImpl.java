package com.skilldistillery.hiker.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.hiker.entities.Hiker;
import com.skilldistillery.hiker.repositories.HikerRepository;

@Service
public class HikerServiceImpl implements HikerService {

	@Autowired
	private HikerRepository hikerRepo;
	
	@Override
	public List<Hiker> getAllHikers() {
		
		return hikerRepo.findAll();
	}

}
