package com.greedy.section02.parameter;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParameterBindingTests {

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
	
	
	/*** 
	 * 
	 * 파라미터 바인딩 하는 방법 
	 * 1. 이름 기준 파라미터(named parameters)
	 * 		':' 다음에 이름 기준 파라미터를 지정한다. 
	 * 2. 위치 기준 파라미터 (positional parameters)
	 * 		'?' 다음에 값을 주고 위치 값은 1부터 시작한다. 
	 * 
	 ***/
	@Test
	public void 이름_기준_파라미터_바인딩_메뉴목록_조회_테스트() {
		
		// given 
		String menuNameParameter = "한우딸기국밥";
		
		// when 
		String jpql = "SELECT m FROM section02_menu m WHERE m.menuName = :menuName"; // : 이 파라미터 위치
		
		List<Menu> menuList = entityManager.createQuery(jpql, Menu.class) // createQuery의 반환 값이 typedquery이다
				.setParameter("menuName", menuNameParameter)	// :menuName" 의 menuName과 연결 된다.
				.getResultList();
		
		// then 
		assertNotNull(menuList);
		menuList.forEach(System.out::println);
		
	}
	
	@Test
	public void 위치_기준_파라미터_바인딩_메뉴목록_조회_테스트() {
		
		// given 
		String menuNameParameter = "한우딸기국밥";
		
		// when 
		String jpql = "SELECT m FROM section02_menu m WHERE m.menuName = ?1"; // ?와 포지션 숫자 전달
		
		List<Menu> menuList = entityManager.createQuery(jpql, Menu.class) // createQuery의 반환 값이 typedquery이다
				.setParameter(1, menuNameParameter)	// :menuName" 의 menuName과 연결 된다.
				.getResultList();
		
		// then 
		assertNotNull(menuList);
		menuList.forEach(System.out::println);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
