package com.skilldistillery.hiker.services;

import java.util.List;

import com.skilldistillery.hiker.entities.Trail;

public interface TrailService {

	List<Trail> getAllTrails();

	Trail getPopularTrail();

}
