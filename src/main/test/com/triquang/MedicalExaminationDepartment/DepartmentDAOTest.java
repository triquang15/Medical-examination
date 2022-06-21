package com.triquang.MedicalExaminationDepartment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.triquang.dao.DepartmentDAO;
import com.triquang.entity.Khoa;

public class DepartmentDAOTest {
	private static DepartmentDAO departmentDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		departmentDAO = new DepartmentDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		departmentDAO.close();
	}

	@Test
	public void testCreateKhoa() {
		Khoa newKhoa = new Khoa("Operating room");
		Khoa Khoa = departmentDAO.create(newKhoa);
		
		assertTrue(Khoa != null && Khoa.getMakhoa() > 0);
	}

	@Test
	public void testUpdateKhoa() {
		Khoa khoa = new Khoa("Nursery");
		khoa.setMakhoa(4);
		
		Khoa khoaUpdate = departmentDAO.update(khoa);
		
		assertEquals(khoa.getTenkhoa(), khoaUpdate.getTenkhoa());
		
	}

	@Test
	public void testGet() {
		Integer khoaId = 4;
		Khoa khoa = departmentDAO.get(khoaId);
		
		assertNotNull(khoa);
	}

	@Test
	public void testDeleteKhoa() {
		Integer khoaId = 4;
		departmentDAO.delete(khoaId);
		
		Khoa khoa = departmentDAO.get(khoaId);
		
		assertNull(khoa);
	}

	@Test
	public void testListAll() {
		List<Khoa> listKhoa = departmentDAO.listAll();
		
		
		listKhoa.forEach(c -> System.out.println(c.getTenkhoa() + ", "));
		
		assertTrue(listKhoa.size() > 0);
	}

	@Test
	public void testCount() {
		long totalKhoa = departmentDAO.count();
		System.out.println(totalKhoa);
		assertTrue(totalKhoa >  0);
	}
	
	@Test
	public void testFindByName() {
		String name = "Odontology";
		System.out.println(name);
		Khoa Khoa = departmentDAO.findByName(name);
		
		assertNotNull(Khoa);		
	}

	@Test
	public void testFindByNameNotFound() {
		String name = "Odontology 1";
		Khoa khoa = departmentDAO.findByName(name);
		
		assertNull(khoa);		
	}	
}
