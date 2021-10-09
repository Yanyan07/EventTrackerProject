package com.skilldistillery.hiker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.hiker.entities.Trail;

public interface TrailRepository extends JpaRepository<Trail, Integer>{

}
