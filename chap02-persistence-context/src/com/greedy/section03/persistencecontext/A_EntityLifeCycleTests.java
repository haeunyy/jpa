package com.greedy.section03.persistencecontext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greedy.section02.crud.Menu;

public class A_EntityLifeCycleTests {

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
	
	
	
	/* 영속성 컨텍스트는 엔티티 매니저가 엔티티 객체를 저장하는 공간으로 엔티티객체를 보관하고 관리한다. 
	 * 엔티티 매니저가 생성될 때 하나의 영속성 컨텍스트가 만들어진다. 
	 * 
	 * 엔티티의 생명 주기 
	 * 비영속, 영속, 준영속, 삭제상태 
	 * */
	
	/* 비영속성 테스트 */
	@Test
	public void 비영속성테스트() {
		
		// given 
		Menu foundMenu = entityManager.find(Menu.class, 11);
		
		/* 객체만 생성하면 영속성 컨텍스트나 데이터 베이스와 관련없는 비영속 상태이다. */
		Menu newMenu = new Menu();
		newMenu.setMenuCode(foundMenu.getMenuCode());
		newMenu.setMenuName(foundMenu.getMenuName());
		newMenu.setMenuPrice(foundMenu.getMenuPrice());
		newMenu.setCategoryCode(foundMenu.getCategoryCode());
		newMenu.setOrderableStatus(foundMenu.getOrderableStatus());
		
		// when 
		boolean isTrue = (foundMenu == newMenu);
		
		// then 
		assertFalse(isTrue);
	}
	
	@Test
	public void 영속성연속조회테스트() {
		/* 엔티티 매니저가 영속성 컨텍스트에 엔티티 객체를 저장(persist)하면 영속성 컨텍스트가 엔티티 객체를 관리하게 되고 이를 영속 상태라고 한다. 
		 * find(), JPQL을 사용한 조회도 영속 상태가 된다. */
		
		// given 
		Menu foundMenu1 = entityManager.find(Menu.class, 11);
		Menu foundMenu2 = entityManager.find(Menu.class, 11);
		
		// when
		boolean isTrue = (foundMenu1 == foundMenu2);
		
		// then 
		assertTrue(isTrue);
		/* 11번에 대한 객체가 영속성 컨텍스트 안에 저장되어 있으므로 재조회하지 않고 재전달 한다. 결과적으로 같은 객체를 가진다. => 동일성 
		 * persist() 요청, find() 둘다 영속상태 */
	}
	
	@Test
	public void 영속성객체추가테스트() {
		/* 시퀀스 주석하고 실행 테스트 */
		
		// given 
		Menu MenuToRegist = new Menu();
		MenuToRegist.setMenuCode(500);
		MenuToRegist.setMenuName("수박가래떡");
		MenuToRegist.setMenuPrice(10000);
		MenuToRegist.setCategoryCode(1);
		MenuToRegist.setOrderableStatus("Y");
		/* 비영속 상태, 관계없는 새로운 객체 */
		
		// when 
		entityManager.persist(MenuToRegist); /* 비영속상태였던 객체를 persist하여 컨텍스트에 저장하고 보관 => 영속상태 */
		Menu foundMenu = entityManager.find(Menu.class, 500); // 키 500번을 가진 엔티티는 재조회 하지 않고 컨텍스트에서 전달됨 
		boolean isTrue = (MenuToRegist == foundMenu);
		
		// then 
		assertTrue(isTrue); 
		/* 객체로서 영속성 관리가 가능하다 */
	}
	
	@Test
	public void 영속성객체추가값변경테스트() {
		
		// given 
		Menu MenuToRegist = new Menu();
		MenuToRegist.setMenuCode(500);
		MenuToRegist.setMenuName("수박가래떡");
		MenuToRegist.setMenuPrice(10000);
		MenuToRegist.setCategoryCode(1);
		MenuToRegist.setOrderableStatus("Y");
		/* 비영속 상태, 관계없는 새로운 객체 */
		
		// when
		entityManager.persist(MenuToRegist);
		MenuToRegist.setMenuName("수박김치");
		
		Menu foundMenu = entityManager.find(Menu.class, 500);
		
		// then 
		assertEquals("수박김치", foundMenu.getMenuName());		// 500 키값을 가지고있는 객체의 주소값을 가르키기 때문에 수박김치가 true이다.  
		/* 키값과 value값으로 관리되기 때문에 키값에 해당되는 주소값을 찾아서 값을 반환한다.*/
	}
	
	@Test
	public void 준영속성detach테스트() {
		
		//given 
		Menu foundMenu1 = entityManager.find(Menu.class, 11);
		Menu foundMenu2 = entityManager.find(Menu.class, 12);
		
		/* 1. 영속성 확인 */
		// when 
//		foundMenu1.setMenuPrice(50000);
//		foundMenu2.setMenuPrice(50000);
//		
//		// then 
//		assertEquals(50000, entityManager.find(Menu.class, 11).getMenuPrice());
//		assertEquals(50000, entityManager.find(Menu.class, 12).getMenuPrice());

		
		/* 2. 준영속성 확인 */
		/* 영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태가 된다면 준영속 상태라고 한다. 
		 * 그 중 detach는 특정 엔티티만 준영속 상태로 만든다. */
		// when 
		entityManager.detach(foundMenu2);
		
		foundMenu1.setMenuPrice(50000);
		foundMenu2.setMenuPrice(50000);
		
		assertEquals(50000, entityManager.find(Menu.class, 11).getMenuPrice());
		assertEquals(50000, entityManager.find(Menu.class, 12).getMenuPrice()); // detach되었기 때문에 새로운 객체를 생성해서 조회하였다. false 
	}
		
	@Test
	public void 준영속성clear테스트() {
		
		//given 
		Menu foundMenu1 = entityManager.find(Menu.class, 11);
		Menu foundMenu2 = entityManager.find(Menu.class, 12);
		
		// when 
		/* clear()는 영속성 컨텍스트를 초기화한다. */
		entityManager.clear();	
		
		foundMenu1.setMenuPrice(50000);
		foundMenu2.setMenuPrice(50000);
		
		// then
		assertEquals(50000, entityManager.find(Menu.class, 11).getMenuPrice()); // false 
		assertEquals(50000, entityManager.find(Menu.class, 12).getMenuPrice()); // false 
	}
	
	
	@Test
	public void close_테스트() {
		
		//given 
		Menu foundMenu1 = entityManager.find(Menu.class, 11);
		Menu foundMenu2 = entityManager.find(Menu.class, 12);

		// when 
		/* close()는 영속성 컨텍스트를 종료한다. */
		entityManager.close();	

		foundMenu1.setMenuPrice(50000);
		foundMenu2.setMenuPrice(50000);

		// then
		/* 영속성 컨텍스트를 닫았기 때문에 다시 만들기 전에는 사용할 수 없다.
		 * java.lang.IllegalStateException : Session / EntityManager is closed */
		assertEquals(50000, entityManager.find(Menu.class, 11).getMenuPrice()); // false 
		assertEquals(50000, entityManager.find(Menu.class, 12).getMenuPrice()); // false 
	}
	
	@Test
	public void 삭제_remove_테스트() {
		
		/* remove : 엔티티를 영속성 컨텍스트 및 데이터 베이스에서 삭제한다. 
		 * 단, 트렌젝션을 제어하지 않으면 영구 반영되지는 않는다. 
		 * 트랜젝션을 커밋하는 순간 영속성 컨텍스트에서 관리하는 엔티티 객체가 데이터 베이스에 반영하게 된다 .이를 flush라고 한다. 
		 * flush  : 영속성 컨텍스트의 변경 내용을 데이터 베이스에 동기화 하는 작업 ( 등록, 수정, 삭제한 엔티티를 데이터 베이스에 반영 )*/
		
		// given 
		Menu foundMenu = entityManager.find(Menu.class, 2);
		
		// when 
		entityManager.remove(foundMenu);	// DB에서 삭제되지는 않음
		Menu refoundMenu = entityManager.find(Menu.class, 2); 	// 2번을 삭제하였기 때문에 null이 담긴다?
		
		// then
		assertEquals(2, foundMenu.getMenuCode());
		assertEquals(null, refoundMenu);
	}
	
	
	/* 병합(merge) : 파라미터로 넘어온 준영속 엔티티 객체의 식별자 값으로 1차 캐시에서 엔티티 객체를 조회한다. 
	 * 만약 1차 캐시에 엔티티가 없으면 데이터베이스에서 엔티티를 조회하고 1차 캐시에 저장한다. 
	 * 조회한 영속 엔티티 객체에 준영속 상태의 엔티티 객체의 값을 병합한 뒤 영속 엔티티 객체를 반환한다 
	 * >>>>> 혹은 조회할 수 없는 데이터의 경우 새로 생성해서 병합한다. (save or update) */
	
	@Test
	public void 병합_merge_수정_테스트() {
		
		//given 
		Menu menuToDetach = entityManager.find(Menu.class, 2);
		entityManager.detach(menuToDetach);
		
		// when 
		menuToDetach.setMenuName("수박죽");
		Menu refoundMenu = entityManager.find(Menu.class, 2);
		refoundMenu.setMenuName("수박초콜릿");
		
		/* 준영속 엔티티와 영속 엔티티의 해쉬코드는 다른 상태이다. */
		System.out.println(menuToDetach.hashCode());
		System.out.println(refoundMenu.hashCode());
		
		entityManager.merge(menuToDetach); // menuToDetach를 기준으로 덮어씌워진다. 
		
		//then
		Menu mergeMenu = entityManager.find(Menu.class,2);
		assertEquals("수박죽", mergeMenu.getMenuName());
	}
	
	
	@Test
	public void 병합_merge_삽입_테스트() {
		
		// given 
		Menu menuToDetach = entityManager.find(Menu.class, 2);
		entityManager.detach(menuToDetach);
		
		// when 
		menuToDetach.setMenuCode(999);		// DB에서 조회할 수 없는 키 값으로 변경
		menuToDetach.setMenuName("수박죽");
		
		entityManager.merge(menuToDetach);	// 영속 상태의 엔티티와 병합 (현재는 없기 때문에 삽입된다.)
		System.out.println(menuToDetach);
		
		//then 
		Menu mergeMenu = entityManager.find(Menu.class, 999);
		assertEquals("수박죽", mergeMenu.getMenuName());
		System.out.println(mergeMenu);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
