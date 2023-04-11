package com.greedy.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class EmployeeAndDepartementTests {

	
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
	public void empId조회() {
		
		String code = "200";
		Employee emp = entityManager.find(Employee.class, code);
//		Department de = emp.getDept();
//		System.out.println(de);
		
//		assertNotNull(de);
		assertEquals(emp.getEmpId(), "200");
		System.out.println(emp);
	}
	
	
	@Test
	public void 새로운_사원_등록() {
		
		Employee emp = new Employee();
		emp.setEmpId("250");
		emp.setEmpNo("940625-2034967");
		emp.setEmpName("테스트");
		emp.setPhone("01001011919");
		emp.setEmail("ididi@gmail.com");
		emp.setDept(entityManager.find(Department.class, "D6"));
		emp.setJobCode("J1");
		emp.setSalLevel("S1");
		
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(emp);
		trans.commit();
		
		Employee foundEmp = entityManager.find(Employee.class, emp.getEmpId());
		assertEquals(emp.getEmpId(), foundEmp.getEmpId());
		System.out.println(foundEmp);
	}
	
	@Test
	public void 삭제() {
		
		Employee emp = entityManager.find(Employee.class, "250");
		
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.remove(emp);
		trans.commit();
		
		assertEquals(null, entityManager.find(Employee.class, "250"));
	}
	
	@Test
	public void 새로운_부서_등록_테스트() {
		
		Employee emp = new Employee();
		emp.setEmpId("250");
		emp.setEmpNo("940625-2034967");
		emp.setEmpName("테스트");
		emp.setPhone("01001011919");
		emp.setEmail("ididi@gmail.com");
		emp.setJobCode("J1");
		emp.setSalLevel("S1");
		
		Department dept = new Department();
		dept.setDeptId("55");
		dept.setDeptTitle("테스트부서");
		dept.setLocationId("L1");
		
		emp.setDept(dept);
		
		EntityTransaction trans = entityManager.getTransaction();
		trans.begin();
		entityManager.persist(emp);
		trans.commit();
		
		Employee foundemp = entityManager.find(Employee.class, emp.getEmpId());
		assertEquals(emp.getEmpId(), foundemp.getEmpId());
		assertEquals(dept.getDeptId(), foundemp.getDept().getDeptId());
		System.out.println(foundemp);
	}
	
	
	
	@Test
	public void 모두모두집합조회_테스트() {
		
		//when
		String jpql = "SELECT e FROM employee as e";
		Query query = entityManager.createQuery(jpql);	
		
		List<Employee> foundEmpList = query.getResultList();
		
		//then
		assertNotNull(foundEmpList);
		foundEmpList.forEach(System.out::println);
		
	}
	
	
	@Test
	public void 자네하씨성을가졌나_조회_테스트() {
		
		//when
		String jpql = "SELECT e FROM employee as e WHERE e.empName LIKE '하' || '%' ";
		Query query = entityManager.createQuery(jpql);	
		
		List<Employee> foundEmpList = query.getResultList();
		
		//then
		assertNotNull(foundEmpList);
		foundEmpList.forEach(System.out::println);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
