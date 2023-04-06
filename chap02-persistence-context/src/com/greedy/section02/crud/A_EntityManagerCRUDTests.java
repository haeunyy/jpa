package com.greedy.section02.crud;

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

public class A_EntityManagerCRUDTests {

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
	public void 메뉴코드로_메뉴_조회_테스트() {
		
		// given
		int menuCode = 2;
		
		// when 
		Menu foundMenu = entityManager.find(Menu.class, menuCode);	// Menu클래스를 인자로 전달하여 어디에서 찾는지, PK를 전달함
		
		//then  => 주피터로 import
		assertNotNull(foundMenu);
		assertEquals(menuCode, foundMenu.getMenuCode());
		System.out.println("foundMenu = " + foundMenu);
		
	}
	
	/* 새로운 메뉴 삽입 */
	@Test
	public void 새로운메뉴추가테스트() {
		
		// given
		Menu menu = new Menu();
		menu.setMenuName("JPA 테스트용 신규 메뉴");
		menu.setMenuPrice(5000);
		menu.setCategoryCode(4);
		menu.setOrderableStatus("Y");
		 
		// when 
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.persist(menu);	// 엔티티 매니저에게 저장해달라는 요청
			entityTransaction.commit();
		} catch(Exception e) {
			entityTransaction.rollback(); 	// exception 발생시 엔티티 트렌젝션을 통해 롤백
			e.printStackTrace(); 			// 에러구문 출력 
		}
		
		// then 
		assertTrue(entityManager.contains(menu)); 	// 엔티티 매니저를 통해 메뉴라는 객체를 가지고있는지 확인하는 구문 
		/* 
		 	0	JPA 테스트용 신규 메뉴	5000	4	Y
		 	메뉴 코드 시퀀스로 핸들링 하려면 DTO에 @SequenceGenerator 설정을 추가한다. 
		 */
		
	}
	
	@Test
	public void 메뉴이름수정테스트() {
		
		// given 
		Menu menu = entityManager.find(Menu.class, 2); 	// 메뉴코드 2번을 가진 엔티티를 먼저 찾는다.
		System.out.println("menu = "+ menu);
		
		String menuNameToChange = "우럭뽈살젤리";			// 수정할 내용을 담는다.
		
		// when
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try {
			menu.setMenuName(menuNameToChange);	// 엔티티 객체를 수정한다. 
			entityTransaction.commit();				
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
		
		// then 
		assertEquals(menuNameToChange, entityManager.find(Menu.class, 2).getMenuName()); // 메뉴클래스에서 2번 pk를 찾아왔을때 엔티티의 메뉴네임과 동일한가요?
	}
	
	
	@Test
	public void 메뉴삭제하기테스트() {
		
		// given
		Menu menuToRemove = entityManager.find(Menu.class, 3);
		
		// when 
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		try {
			entityManager.remove(menuToRemove);		// 찾은 엔티티를 삭제한다. 
			entityTransaction.commit();				
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
		
		// then 
		Menu removedMenu = entityManager.find(Menu.class, 3);
		assertEquals(null, removedMenu);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
