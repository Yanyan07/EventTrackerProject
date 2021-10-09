package com.skilldistillery.hiker.services;

import java.util.List;
import com.skilldistillery.hiker.entities.Hiker;
import com.skilldistillery.hiker.entities.Trail;

public interface HikerService {

	List<Hiker> getAllHikers();

	double getTotalDistanceForHiker(int hikerId);

	double getDistancePerHikerPerTrail(int hikerId, int trailId);
	
	Trail getFavoriteTrailForHiker(int hikerId);

	
}
