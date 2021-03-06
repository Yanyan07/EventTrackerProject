package com.skilldistillery.hiker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

class HikerTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Hiker hiker;

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
		hiker = em.find(Hiker.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		hiker = null;
	}

	@Test
	void test() {
		assertNotNull(hiker);
		assertEquals("hanna", hiker.getName());

	}
	
	@Test
	@DisplayName("test hiker singleHiking mapping")
	void test_hiker_singleHiking() {
		assertNotNull(hiker);
		List<SingleHiking> hikings = hiker.getSingleHikings();
		assertNotNull(hikings);
		assertTrue(hikings.size() > 0);
		assertEquals(2, hikings.get(0).getDistance());
		assertEquals(1, hikings.get(0).getHikingDate().getDayOfMonth());
	}

}
