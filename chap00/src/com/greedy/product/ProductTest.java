package com.greedy.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

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
	public void 인서트를해보자() {
		
		// given 
		Product product = new Product();
		product.setName("아주잘보여요모니터");
		product.setPrice(1000000);
		product.setReleaseDate(new java.sql.Date(System.currentTimeMillis()));
		product.setCategory(ProductCategory.MONITOR);
		
		// when 
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(product);
		trans.commit();
		
		// then 
		Product productFound = entityManager.find(Product.class, product.getProductId());
		assertEquals(product.getProductId(), 1);
		/* 지금 무엇을 하고 싶은가 */
	}
	
	
}
