package com.skilldistillery.hiker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.hiker.entities.SingleHiking;
import com.skilldistillery.hiker.services.SingleHikingService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class SingleHikingController {

	@Autowired
	private SingleHikingService hikingSvc;

	@GetMapping("hikings")
	public List<SingleHiking> getAllHiking(HttpServletResponse res) {
		List<SingleHiking> hikings = hikingSvc.findAllHiking();
		if (hikings == null) {
			res.setStatus(404);
		}
		return hikings;
	}

	@GetMapping("hikings/{hikerId}")
	public List<SingleHiking> getHikingByHiker(@PathVariable Integer hikerId, 
												HttpServletResponse res) {
		List<SingleHiking> hikings = hikingSvc.findHikingForHiker(hikerId);
		if (hikings == null) {
			res.setStatus(404);
		}
		return hikings;
	}

	@PostMapping("hikings/{hikerId}/{trailId}/hiking")
	public SingleHiking createHiking(@PathVariable Integer hikerId, 
									 @PathVariable Integer trailId,
									 @RequestBody SingleHiking hiking, 
									 HttpServletResponse res) {
		hiking = hikingSvc.createHiking(hikerId, trailId, hiking);
		if (hiking == null) {
			res.setStatus(404);
			return null;
		}
		return hiking;
	}

	@PutMapping("hikings/{hikerId}/{trailId}/{hikingId}/hiking")
	public SingleHiking updateHiking(@PathVariable Integer hikerId, 
									 @PathVariable Integer trailId,
									 @PathVariable Integer hikingId,
									 @RequestBody SingleHiking hiking, 
									 HttpServletResponse res) {
		hiking = hikingSvc.updateHiking(hikerId, trailId, hikingId, hiking);
		if (hiking == null) {
			res.setStatus(404);
			return null;
		}
		return hiking;
	}

	@DeleteMapping("hikings/{hikerId}/{hikingId}/hiking")
	public void deleteHiking(@PathVariable Integer hikerId, @PathVariable Integer hikingId, HttpServletResponse res) {
		if (hikingSvc.deleteHiking(hikerId, hikingId)) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}

}
