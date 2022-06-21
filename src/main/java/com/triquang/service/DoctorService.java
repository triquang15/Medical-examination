package com.triquang.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.triquang.dao.DepartmentDAO;
import com.triquang.dao.DoctorDAO;
import com.triquang.dao.ExaminationScheduleDAO;
import com.triquang.entity.Bacsi;
import com.triquang.entity.Khoa;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoctorService {
	private DoctorDAO doctorDAO;
	private DepartmentDAO departmentDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public DoctorService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		doctorDAO = new DoctorDAO();
		departmentDAO = new DepartmentDAO();
		}

	public void listDoctors() throws ServletException, IOException {
		listDoctors(null);
	}
	
	public void listDoctors(String message) throws ServletException, IOException {
		List<Bacsi> listDoctors = doctorDAO.listAll();
		request.setAttribute("listDoctors", listDoctors);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "doctor_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
	
	public void showDoctorNewForm() throws ServletException, IOException {
		List<Khoa> listDepartment = departmentDAO.listAll();
		request.setAttribute("listDepartment", listDepartment);
		
		String newPage = "doctor_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);		
	}
	
	public void createDoctor() throws ServletException, IOException {
		String name = request.getParameter("name");
		
		Bacsi existDoctor = doctorDAO.findByName(name);
		
		if (existDoctor != null) {
			String message = "Could not create new doctor because the name '"
					+ name + "' already exists.";
			listDoctors(message);
			return;
		}
		
		Bacsi newDoctor = new Bacsi();
		readDoctorFields(newDoctor);
		
		Bacsi createdDoctor = doctorDAO.create(newDoctor);
		
		if (createdDoctor.getMabs() > 0) {
			String message = "A new doctor has been created successfully.";
			listDoctors(message);
		}
	}

	public void readDoctorFields(Bacsi doctor) throws ServletException, IOException {
		String name = request.getParameter("name");
	
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date birthDay = null;
		
		try {
			birthDay = dateFormat.parse(request.getParameter("birthDay"));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
		}
				
		doctor.setTenbs(name);
		doctor.setNgaysinh(birthDay);
		
		Integer departmentId = Integer.parseInt(request.getParameter("department"));
		Khoa department = departmentDAO.get(departmentId);
		doctor.setKhoa(department);
		
	}
	
	public void editDoctor() throws ServletException, IOException {
		Integer doctorId = Integer.parseInt(request.getParameter("id"));
		Bacsi doctor = doctorDAO.get(doctorId);
		List<Khoa> listDepartment = departmentDAO.listAll();
		
		request.setAttribute("doctor", doctor);
		request.setAttribute("listDepartment", listDepartment);
		
		String editPage = "doctor_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);		
		
	}

	public void updateDoctor() throws ServletException, IOException {
		Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));
		String name = request.getParameter("name");
		
		Bacsi existDoctor = doctorDAO.get(doctorId);
		Bacsi doctorByName = doctorDAO.findByName(name);
		
		if (doctorByName != null && !existDoctor.equals(doctorByName)) {
			String message = "Could not update doctor because there's another doctor having same name.";
			listDoctors(message);
			return;
		}
		
		readDoctorFields(existDoctor);
		
		doctorDAO.update(existDoctor);
		
		String message = "The doctor has been updated successfully.";
		listDoctors(message);
	}

	public void deleteDoctor() throws ServletException, IOException {
		int doctorId = Integer.parseInt(request.getParameter("id"));
		ExaminationScheduleDAO scheduleDAO = new ExaminationScheduleDAO();
		long number = scheduleDAO.countBySchedule(doctorId);
		String message;
		
		if (number > 0) {
			message = "Could not delete the doctor (ID: %d) because it currently contains some department.";
			message = String.format(message, number);
		} else {
			doctorDAO.delete(doctorId);		
			message = "The doctor with ID " + doctorId + " has been removed successfully.";
		}
		
		listDoctors(message);
		
	}
	
}
