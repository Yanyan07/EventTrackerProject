package com.skilldistillery.hiker.entities;

import static org.junit.jupiter.api.Assertions.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SingleHikingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private SingleHiking singleHiking;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAHiker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		singleHiking = em.find(SingleHiking.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		singleHiking = null;
	}

	@Test
	void test() {
		assertNotNull(singleHiking);
		assertEquals(2, singleHiking.getDistance());
	}
	
	@Test
	@DisplayName("test singleHiking hiker mapping")
	void test_singleHiking_hiker() {
		assertNotNull(singleHiking);
		assertEquals(1, singleHiking.getHiker().getId());
	}
	
	@Test
	@DisplayName("test singleHiking trail mapping")
	void test_singleHiking_trail() {
		assertNotNull(singleHiking);
		assertEquals(1, singleHiking.getTrail().getId());
	}
}
