package com.greedy.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CategoryTest {

	
//	private Connection con;
//
//    @BeforeEach
//    public void setConnection() throws SQLException, ClassNotFoundException {
//
//        String driver = "oracle.jdbc.driver.OracleDriver";
//        String url = "jdbc:oracle:thin:@localhost:1521:xe";
//        String user = "C##GREEDY";
//        String password = "GREEDY";
//
//        Class.forName(driver);
//        con = DriverManager.getConnection(url, user, password);
//    }
//
//    @AfterEach
//    public void closeConnection() throws SQLException {
//        con.close();
//    }
	
	// CRUD
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@BeforeAll
	public static void initFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
	}

	@BeforeEach
	public void initManager() {
		entityManager = entityManagerFactory.createEntityManager();
	}		

	@AfterAll
	public static void closeFactory() {
		entityManagerFactory.close();
	}

	@AfterEach
	public void closeManager() {
		entityManager.close();
	}

	@Test
	public void 카테고리_조회_테스트() {
		
		// given
		int cateCode = 10;
		
		// when 
		Category foundCate = entityManager.find(Category.class, cateCode);	// Menu클래스를 인자로 전달하여 어디에서 찾는지, PK를 전달함
		
		assertNotNull(foundCate);
		assertEquals(cateCode, foundCate.getCategoryCode());
		System.out.println("foundCate = " + foundCate);
	}
	
	@Test
	public void 새로운카테고리추가테스트() {
		
		// given
		Category cate = new Category();
		cate.setCategoryCode(99);
		cate.setCategoryName("테스트카테고리");
		// when 
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		try {
			entityManager.persist(cate);	
			trans.commit();
		} catch(Exception e) {
			trans.rollback(); 	
			e.printStackTrace(); 			
		}
		
		// then 
		assertTrue(entityManager.contains(cate)); 	
	}
	
	@Test
	public void 카테고리이름수정테스트() {
		
		// given 
		Category cate = entityManager.find(Category.class, 99); 
		System.out.println("cate = "+ cate);
		
		String cateNameToChange = "수정테스트수정수정";	
		
		// when
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		
		try {
			cate.setCategoryName(cateNameToChange);	
			trans.commit();				
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}
		
		// then 
		assertEquals(cateNameToChange, entityManager.find(Category.class, 2).getCategoryName());
	}
	
	@Test
	public void 카테고리삭제하기테스트() {
		
		// given
		Category cate = entityManager.find(Category.class,99);
		
		// when 
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.remove(cate);		
			entityTransaction.commit();				
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}

		Category removedCate= entityManager.find(Category.class, 99);
		assertEquals(null, removedCate);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
