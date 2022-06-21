package com.triquang.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.triquang.dao.PatientDAO;
import com.triquang.entity.Benhnhan;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PatientService {
	private PatientDAO patientDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public PatientService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		patientDAO = new PatientDAO();
	}
	
	public void listPatient(String message) throws ServletException, IOException {
		List<Benhnhan> listPatient = patientDAO.listAll();
		
		request.setAttribute("listPatient", listPatient);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "patient_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

		requestDispatcher.forward(request, response);		
	}
	
	public void listPatient() throws ServletException, IOException {
		listPatient(null);
	}
	
	public void createPatient() throws ServletException, IOException {
		String name = request.getParameter("name");
		String patientGender = request.getParameter("gender");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date birthDay = null;
		
		try {
			birthDay = dateFormat.parse(request.getParameter("birthDay"));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing birthday (format is MM/dd/yyyy)");
		}
		Benhnhan existPatient = patientDAO.findByName(name);
		
		if (existPatient != null) {
			String message = "Could not create patient."
					+ " A patient " + name + " already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);			
		} else {
			Benhnhan newPatient = new Benhnhan(name);
			newPatient.setGioitinh(patientGender);
			newPatient.setNgaysinh(birthDay);
			patientDAO.create(newPatient);
			String message = "New patient created successfully.";
			listPatient(message);
		}
	}
	
	public void editPatient() throws ServletException, IOException {
		int patientId = Integer.parseInt(request.getParameter("id"));
		Benhnhan patient = patientDAO.get(patientId);
		request.setAttribute("patient", patient);
		
		String editPage = "patient_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);		
		
	}

	public void updatePatient() throws ServletException, IOException {
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		String patientName = request.getParameter("name");
		String patientGender = request.getParameter("gender");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date birthDay = null;
		
		try {
			birthDay = dateFormat.parse(request.getParameter("birthDay"));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing birthday (format is MM/dd/yyyy)");
		}
		
		
		Benhnhan patientById = patientDAO.get(patientId);
		Benhnhan patientByName = patientDAO.findByName(patientName);
		
		if (patientByName != null && patientById.getMabn() != patientByName.getMabn()) {
			String message = "Could not update patient."
					+ " A patient with name " + patientName + " already exists.";
			
			request.setAttribute("message", message);			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);			
		} else {
			patientById.setTenbn(patientName);
			patientById.setGioitinh(patientGender);
			patientById.setNgaysinh(birthDay);
			patientDAO.update(patientById);
			String message = "Patient has been updated successfully";
			listPatient(message);
		}
	}
	
	public void deletePatient() throws ServletException, IOException {
		Integer patientId = Integer.parseInt(request.getParameter("id"));
		
		patientDAO.delete(patientId);
		
		String message = "The patient has been deleted successfully.";
		listPatient(message);		
	}
}
