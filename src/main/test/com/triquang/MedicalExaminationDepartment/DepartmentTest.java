package com.triquang.MedicalExaminationDepartment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.triquang.entity.Khoa;

public class DepartmentTest {
	
	public static void main(String[] args) {
		Khoa khoa = new Khoa("Plastic surgery");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Medical-examination");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(khoa);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("A Department  object was persisted");
	}
	
}

