package com.greedy.section05.access.subsection02.property;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PropertyAccessTests {

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
	public void property_접근_테스트() {

		// given 
		Member member = new Member();
		member.setMemberNo(1);
		member.setMemberId("user01");
		member.setMemberPwd("pass01");
		member.setNickname("홍길동");

		// when 
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(member);
		trans.commit();
		
		// then 
		String jpql = "SELECT A.nickname FROM property_access_member A WHERE A.memberNo = 1";
		String registNickname = entityManager.createQuery(jpql, String.class).getSingleResult();
		assertEquals("홍길동 님", registNickname);
		System.out.println(registNickname);

	}
	/* @Id의 위치에 따라 @Access 방식이 결정되며 이는 전역적인 설정이다. 
	 * @Access설정을 getter에 지정하게 되면 특정 필드만 getter메소드로 접근할 수 있으며, 
	 * 추가 로직이 필요한 경우 설정한다. */
}
