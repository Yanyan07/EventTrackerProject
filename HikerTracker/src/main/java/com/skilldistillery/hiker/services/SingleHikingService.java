package com.skilldistillery.hiker.services;

import java.util.List;
import com.skilldistillery.hiker.entities.SingleHiking;

public interface SingleHikingService {

	List<SingleHiking> findHikingForHiker(int hikerId);

	List<SingleHiking> findAllHiking();

	SingleHiking createHiking(int hikerId, int trailId, SingleHiking singleHiking);
	
	boolean deleteHiking(int hikerId, int hikingId);

	List<SingleHiking> findHikingForTrail(int trailId);
	
}
