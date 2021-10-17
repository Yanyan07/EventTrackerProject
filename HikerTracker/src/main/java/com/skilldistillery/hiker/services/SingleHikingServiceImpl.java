package com.skilldistillery.hiker.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.hiker.entities.Hiker;
import com.skilldistillery.hiker.entities.SingleHiking;
import com.skilldistillery.hiker.entities.Trail;
import com.skilldistillery.hiker.repositories.HikerRepository;
import com.skilldistillery.hiker.repositories.SingleHikingRepository;
import com.skilldistillery.hiker.repositories.TrailRepository;

@Service
public class SingleHikingServiceImpl implements SingleHikingService {

	@Autowired
	private SingleHikingRepository hikingRepo;
	@Autowired
	private HikerRepository hikerRepo;
	@Autowired
	private TrailRepository trailRepo;
	
	@Override
	public List<SingleHiking> findAllHiking(){
		return hikingRepo.findAll();
	}
	
	@Override
	public List<SingleHiking> findHikingForHiker(int hikerId) {
		return hikingRepo.findByHiker_Id(hikerId);
	}
	
	@Override
	public List<SingleHiking> findHikingForTrail(int trailId){
		return hikingRepo.findByTrail_Id(trailId);
	}
	
	@Override   
	public SingleHiking createHiking(int hikerId, int trailId, 
									 SingleHiking singleHiking) {
		Optional<Hiker> hikerOpt = hikerRepo.findById(hikerId);
		Optional<Trail> trailOpt = trailRepo.findById(trailId);
		if(!hikerOpt.isPresent() || !trailOpt.isPresent() || singleHiking==null) {
			return null;
		}else {
			singleHiking.setHiker(hikerOpt.get());
			singleHiking.setTrail(trailOpt.get());
			hikingRepo.saveAndFlush(singleHiking);
			return singleHiking;
		}
	}
	
	@Override
	public SingleHiking updateHiking(int hikerId, int trailId, int hikingId,
			SingleHiking singleHiking) {
		Optional<Hiker> hikerOpt = hikerRepo.findById(hikerId);
		Optional<Trail> trailOpt = trailRepo.findById(trailId);
		Optional<SingleHiking> hikingOpt = hikingRepo.findById(hikingId);
		if(!hikerOpt.isPresent() || !trailOpt.isPresent() || !hikingOpt.isPresent()) {
			return null;
		}else {
			singleHiking.setId(hikingId);
			singleHiking.setHiker(hikerOpt.get());
			singleHiking.setTrail(trailOpt.get());
			hikingRepo.saveAndFlush(singleHiking);
			return singleHiking;
		}
	}

	@Override
	public boolean deleteHiking(int hikerId, int hikingId) {
		boolean deleted = false;
		SingleHiking singleHiking = hikingRepo.findByHiker_IdAndId(hikerId, hikingId);
		if(singleHiking != null) {
			hikingRepo.delete(singleHiking);
			deleted = !hikingRepo.existsById(hikingId);
		}
		return deleted;
	}

}
