package com.triquang.service;

import java.io.IOException;
import java.util.List;

import com.triquang.dao.DepartmentDAO;
import com.triquang.dao.DoctorDAO;
import com.triquang.entity.Khoa;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DepartmentService {
	private DepartmentDAO departmentDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public DepartmentService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		departmentDAO = new DepartmentDAO();
	}
	
	public void listKhoa(String message) throws ServletException, IOException {
		List<Khoa> listCategory = departmentDAO.listAll();
		
		request.setAttribute("listKhoa", listCategory);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "department_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

		requestDispatcher.forward(request, response);		
	}
	
	public void listKhoa() throws ServletException, IOException {
		listKhoa(null);
	}
	
	public void createDepartment() throws ServletException, IOException {
		String name = request.getParameter("name");
		Khoa existDepartment = departmentDAO.findByName(name);
		
		if (existDepartment != null) {
			String message = "Could not Create Medical Examination Department."
					+ " A Medical Examination Department " + name + " already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);			
		} else {
			Khoa newDepartment = new Khoa(name);
			departmentDAO.create(newDepartment);
			String message = "New Medical Examination Department created successfully.";
			listKhoa(message);
		}
	}
	
	public void editDepartment() throws ServletException, IOException {
		int khoaId = Integer.parseInt(request.getParameter("id"));
		Khoa khoa = departmentDAO.get(khoaId);
		request.setAttribute("khoa", khoa);
		
		String editPage = "department_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);		
		
	}

	public void updateDepartment() throws ServletException, IOException {
		int khoaId = Integer.parseInt(request.getParameter("departmentId"));
		String khoaName = request.getParameter("name");
		
		Khoa khoaById = departmentDAO.get(khoaId);
		Khoa khoaByName = departmentDAO.findByName(khoaName);
		
		if (khoaByName != null && khoaById.getMakhoa() != khoaByName.getMakhoa()) {
			String message = "Could not update Medical Examination Department."
					+ " A department with name " + khoaName + " already exists.";
			
			request.setAttribute("message", message);			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);			
		} else {
			khoaById.setTenkhoa(khoaName);
			departmentDAO.update(khoaById);
			String message = "Medical Examination Department has been updated successfully";
			listKhoa(message);
		}
	}

	
	public void deleteDepartment() throws ServletException, IOException {
		int departmentId = Integer.parseInt(request.getParameter("id"));
		DoctorDAO doctorDAO = new DoctorDAO();
		long numberOfDoctors = doctorDAO.countByDepartment(departmentId);
		String message;
		
		if (numberOfDoctors > 0) {
			message = "Could not delete the department (ID: %d) because it currently contains some doctors.";
			message = String.format(message, numberOfDoctors);
		} else {
			departmentDAO.delete(departmentId);		
			message = "The department with ID " + departmentId + " has been removed successfully.";
		}
		
		listKhoa(message);
		
	}
	 
}
