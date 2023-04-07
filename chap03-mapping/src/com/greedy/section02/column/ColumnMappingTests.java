package com.greedy.section02.column;

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


public class ColumnMappingTests {

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
	public void 컬럼에서_사용하는_속성테스트() {
		
		
		// given 
		Member member = new Member();
		member.setMemberNo(1);
		member.setMemberId("user01");
		member.setMemberPwd("pass01");
		member.setNickname("홍길동");
		member.setPhone("010-1234-1234");
		member.setAddress("서울시 ");
		member.setEnrollDate(new java.sql.Date(System.currentTimeMillis()));
		member.setMemberRole("ROLE_MEMBER");
		member.setStatus("Y");
		
		// when 
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(member);
		transaction.commit();
		
		// then 
		Member foundMember = entityManager.find(Member.class, member.getMemberNo());
		assertEquals(member.getMemberNo(), foundMember.getMemberNo());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
