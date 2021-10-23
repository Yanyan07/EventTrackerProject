package com.skilldistillery.hiker.controllers;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skilldistillery.hiker.entities.Hiker;
import com.skilldistillery.hiker.entities.Trail;
import com.skilldistillery.hiker.services.HikerService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class HikerController {
	
	@Autowired
	private HikerService hikerSvc;
	
	@GetMapping("hikers")
	public List<Hiker> hikerIndex(){
		return hikerSvc.getAllHikers();
	}

	@GetMapping("hikers/distance/{hikerId}")
	public double hikerDistance(@PathVariable Integer hikerId,
								HttpServletResponse res) {
		double distance = hikerSvc.getTotalDistanceForHiker(hikerId);
		if(distance <= 0) {
			res.setStatus(404);
		}
		return distance;
	}
	
	@GetMapping("hikers/distance/{hikerId}/{trailId}")
	public double hikerDistancePerHikerPerTrail(@PathVariable Integer hikerId,
												@PathVariable Integer trailId,
												HttpServletResponse res) {
		double distance = hikerSvc.getDistancePerHikerPerTrail(hikerId, trailId);
		if(distance <= 0) {
			res.setStatus(404);
		}
		return distance;
	}
	
	@GetMapping("hikers/favorite/{hikerId}")
	public Trail getFavoriteTrail(@PathVariable Integer hikerId,
								  HttpServletResponse res) {
		Trail trail = hikerSvc.getFavoriteTrailForHiker(hikerId);
		if(trail == null) {
			res.setStatus(404);
		}
		return trail;
	}
	
}
