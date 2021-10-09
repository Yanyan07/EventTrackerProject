package com.skilldistillery.hiker.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.hiker.entities.SingleHiking;
import com.skilldistillery.hiker.entities.Trail;
import com.skilldistillery.hiker.repositories.HikerRepository;
import com.skilldistillery.hiker.repositories.SingleHikingRepository;
import com.skilldistillery.hiker.repositories.TrailRepository;

@Service
public class TrailServiceImpl implements TrailService {

	@Autowired
	private TrailRepository trailRepo;
	@Autowired
	private SingleHikingRepository hikingRepo;
	
	@Override
	public List<Trail> getAllTrails(){
		return trailRepo.findAll();
	}
	
	@Override
	public Trail getPopularTrail() {
		List<SingleHiking> hikings = hikingRepo.findAll();
		Map<Trail,Integer> trailMap = new HashMap<>();
		if(hikings == null) {
			return null;
		}
		for (SingleHiking hiking : hikings) {
			Trail trail = hiking.getTrail();
			
			if(trailMap.containsKey(trail)) {
				trailMap.put(trail, trailMap.get(trail)+1);
			}else {
				trailMap.put(trail, 1);
			}
		}
		Trail popularTrail = null;
		int maxVisit = 0;
		for (Trail t : trailMap.keySet()) {
			if(trailMap.get(t) > maxVisit) {
				maxVisit = trailMap.get(t);
				popularTrail = t;
			}
		}
		return popularTrail;
	}
}
