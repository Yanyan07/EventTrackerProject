package com.skilldistillery.hiker.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.hiker.entities.SingleHiking;

public interface SingleHikingRepository extends JpaRepository<SingleHiking, Integer> {

	List<SingleHiking> findByHiker_Id(int hikerId);
	
	SingleHiking findByHiker_IdAndId(int hikerId, int id);
	
}
