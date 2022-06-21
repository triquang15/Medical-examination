package com.triquang.MedicalExaminationDepartment;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.triquang.entity.Bacsi;
import com.triquang.entity.Benhnhan;

public class PatientTest {
	public static void main(String[] args) {
		
		Benhnhan benhnhan = new Benhnhan();
		benhnhan.setTenbn("Tri Quang");
		benhnhan.setNgaysinh(new Date());
		benhnhan.setGioitinh("Female");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Medical-examination");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(benhnhan);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("Done!");
	}
}
