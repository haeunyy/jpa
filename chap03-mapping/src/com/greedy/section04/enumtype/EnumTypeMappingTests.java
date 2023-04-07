package com.greedy.section04.enumtype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/* MemeberRole을 Enum타입으로 작성해보자 */
public class EnumTypeMappingTests {
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
	public void enum타입_매핑_테스트() {
		
		// given 
		Member member = new Member();
		member.setMemberNo(1);
		member.setMemberId("user01");
		member.setMemberPwd("pass01");
		member.setNickname("홍길동");
		member.setPhone("010-1234-1234");
		member.setAddress("서울시 ");
		member.setEnrollDate(new java.sql.Date(System.currentTimeMillis()));
		member.setMemberRole(RoleType.MEMBER);	// Enum RoleType으로 수정
		member.setStatus("Y");
		
		// when 
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(member);
		trans.commit();
		
		// then 
		Member foundMember = entityManager.find(Member.class, member.getMemberNo());
		assertEquals(member.getMemberNo(), foundMember.getMemberNo());
		System.out.println(foundMember);
		
		/* memberRole에 1로 삽입된다. @Enumerated(EnumType.STRING) 어노테이션을 사용하면 설정한 MEMBER 리터럴 값으로 삽입이 가능하다. */
	}
	
}
