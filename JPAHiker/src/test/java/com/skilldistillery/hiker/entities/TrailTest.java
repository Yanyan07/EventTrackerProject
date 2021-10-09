package com.skilldistillery.hiker.entities;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrailTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Trail trail;

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
		trail = em.find(Trail.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		trail = null;
	}

	@Test
	void test() {
		assertNotNull(trail);
		assertEquals("Emerald Lake Trail", trail.getName());
	}
	
	@Test
	@DisplayName("test trail singleHiking mapping")
	void test_trail_singleHiking() {
		assertNotNull(trail);
		List<SingleHiking> hikings = trail.getSingleHikings();
		assertNotNull(hikings);
		assertTrue(hikings.size() > 0);
		assertEquals(2, hikings.get(0).getDistance());
		assertEquals(5, hikings.get(1).getHikingDate().getDayOfMonth());
	}

}
