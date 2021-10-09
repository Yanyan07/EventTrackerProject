package com.skilldistillery.hiker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skilldistillery.hiker.entities.Trail;
import com.skilldistillery.hiker.services.TrailService;

@RestController
@RequestMapping("api")
public class TrailController {
	
	@Autowired
	private TrailService trailSvc;
	
	@GetMapping("trails")
	public List<Trail> hikerIndex(){
		return trailSvc.getAllTrails();
	}
	
	@GetMapping("trails/popular")
	public Trail getPopularTrail(HttpServletResponse res) {
		Trail t = trailSvc.getPopularTrail();
		if(t == null) {
			res.setStatus(404);
		}
		return t;
	}
	
}
