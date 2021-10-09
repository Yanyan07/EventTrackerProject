package com.skilldistillery.hiker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.hiker.entities.Location;

public interface LocationRepository extends JpaRepository<Location,Integer> {

}
