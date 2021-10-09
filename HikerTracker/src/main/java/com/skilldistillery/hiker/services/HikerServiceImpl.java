package com.skilldistillery.hiker.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.hiker.entities.Hiker;
import com.skilldistillery.hiker.entities.SingleHiking;
import com.skilldistillery.hiker.entities.Trail;
import com.skilldistillery.hiker.repositories.HikerRepository;
import com.skilldistillery.hiker.repositories.SingleHikingRepository;

@Service
public class HikerServiceImpl implements HikerService {

	@Autowired
	private HikerRepository hikerRepo;
	@Autowired
	private SingleHikingRepository hikingRepo;
	
	@Override
	public List<Hiker> getAllHikers() {
		
		return hikerRepo.findAll();
	}
	
	@Override
	public double getTotalDistanceForHiker(int hikerId) {
		List<SingleHiking> hikings = hikingRepo.findByHiker_Id(hikerId);
		if(hikings == null) {
			return 0;
		}
		double distance = 0;
		for (SingleHiking hiking : hikings) {
			distance += hiking.getDistance();
		}
		return distance;
	}
	
	@Override
	public double getDistancePerHikerPerTrail(int hikerId, int trailId) {
		List<SingleHiking> hikings = hikingRepo.findByHiker_IdAndTrail_Id(hikerId,trailId);
		if(hikings == null) {
			return 0;
		}
		double distance = 0;
		for (SingleHiking hiking : hikings) {
			distance += hiking.getDistance();
		}
		return distance;
	}
	
	@Override
	public Trail getFavoriteTrailForHiker(int hikerId) {
		List<SingleHiking> hikings = hikingRepo.findByHiker_Id(hikerId);
		if(hikings == null) {
			return null;
		}
		Map<Trail,Integer> trailMap = new HashMap<>();
		for (SingleHiking hiking : hikings) {
			Trail trail = hiking.getTrail();
			if(trailMap.containsKey(trail)) {
				trailMap.put(trail, trailMap.get(trail)+1);
			}else {
				trailMap.put(trail, 1);
			}
		}
		
		Trail favoriteTrail = null;
		int max = 0;
		for (Trail trail : trailMap.keySet()) {
			if(trailMap.get(trail)>max || 
				trailMap.get(trail)==max&&getDistancePerHikerPerTrail(hikerId,trail.getId())>getDistancePerHikerPerTrail(hikerId,favoriteTrail.getId())) {
				max = trailMap.get(trail);
				favoriteTrail = trail;
			}
		}
		return favoriteTrail;
	}
	

}
