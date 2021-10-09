package com.skilldistillery.hiker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.hiker.entities.Hiker;

public interface HikerRepository extends JpaRepository<Hiker, Integer> {

}
