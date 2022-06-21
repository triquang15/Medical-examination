package com.triquang.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.triquang.dao.DoctorDAO;
import com.triquang.dao.ExaminationScheduleDAO;
import com.triquang.dao.PatientDAO;
import com.triquang.entity.Bacsi;
import com.triquang.entity.Benhnhan;
import com.triquang.entity.Lichkham;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExaminationScheduleService {
	private ExaminationScheduleDAO scheduleDAO;
	private PatientDAO patientDAO;
	private DoctorDAO doctorDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ExaminationScheduleService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		scheduleDAO = new ExaminationScheduleDAO();
		patientDAO = new PatientDAO();
		doctorDAO = new DoctorDAO();
	}

	public void listSchedules() throws ServletException, IOException {
		listSchedules(null);
	}
	
	public void listSchedules(String message) throws ServletException, IOException {
		List<Lichkham> listSchedules = scheduleDAO.listAll();
		request.setAttribute("listSchedules", listSchedules);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "schedule_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
	
	public void showScheduleNewForm() throws ServletException, IOException {
		List<Benhnhan> listPatient = patientDAO.listAll();
		request.setAttribute("listPatient", listPatient);
		
		List<Bacsi> listDoctor = doctorDAO.listAll();
		request.setAttribute("listDoctor", listDoctor);
		
		String newPage = "schedule_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);		
	}

	public void createSchedule() throws ServletException, IOException {
		String title = request.getParameter("title");
		
		Lichkham existSchedule = scheduleDAO.findByTitle(title);
		
		if (existSchedule != null) {
			String message = "Could not create new shedule because the title '"
					+ title + "' already exists.";
			listSchedules(message);
			return;
		}
		
		Lichkham newSchedule = new Lichkham();
		readScheduleFields(newSchedule);
		
		Lichkham createdSchedule = scheduleDAO.create(newSchedule);
		
		if (createdSchedule.getId() > 0) {
			String message = "A new schedule has been created successfully.";
			listSchedules(message);
		}
	}

	public void readScheduleFields(Lichkham lichkham) throws ServletException, IOException {

		String content = request.getParameter("content");	
		String title = request.getParameter("title");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date scheduleDay = null;
		
		try {
			scheduleDay = dateFormat.parse(request.getParameter("scheduleDay"));
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
		}

		lichkham.setNoidung(content);
		lichkham.setTitle(title);
		lichkham.setNgaykham(scheduleDay);
		
		Integer doctorId = Integer.parseInt(request.getParameter("bacsi"));
		Bacsi doctor = doctorDAO.get(doctorId);
		lichkham.setBacsi(doctor);
		
		Integer patientId = Integer.parseInt(request.getParameter("benhnhan"));
		Benhnhan patient = patientDAO.get(patientId);	
		lichkham.setBenhnhan(patient);
		
	}
	
	public void editSchedule() throws ServletException, IOException {
		Integer scheduleId = Integer.parseInt(request.getParameter("id"));
		Lichkham schedule = scheduleDAO.get(scheduleId);
		
		List<Bacsi> listDoctor = doctorDAO.listAll();
		List<Benhnhan> listPatient = patientDAO.listAll();
		
		request.setAttribute("schedule", schedule);
		request.setAttribute("listDoctor", listDoctor);
		request.setAttribute("listPatient", listPatient);
		
		String editPage = "schedule_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);		
		
	}

	public void updateSchedule() throws ServletException, IOException {
		Integer scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
		String title = request.getParameter("title");
		
		Lichkham existSchedule = scheduleDAO.get(scheduleId);
		Lichkham scheduleByTitle = scheduleDAO.findByTitle(title);
		
		if (scheduleByTitle != null && !existSchedule.equals(scheduleByTitle)) {
			String message = "Could not update schedule because there's another schedule having same title.";
			listSchedules(message);
			return;
		}
		
		readScheduleFields(existSchedule);
		
		scheduleDAO.update(existSchedule);
		
		String message = "The schedule has been updated successfully.";
		listSchedules(message);
	}

	public void deleteSchedule() throws ServletException, IOException {
		Integer scheduleId = Integer.parseInt(request.getParameter("id"));
		
		scheduleDAO.delete(scheduleId);
		
		String message = "The schedule has been deleted successfully.";
		listSchedules(message);		
	}

	public void listScheduleByDoctor() throws ServletException, IOException {
		int doctorId = Integer.parseInt(request.getParameter("id"));
		List<Lichkham> listSchedules = scheduleDAO.listByDoctor(doctorId);
		Bacsi doctor = doctorDAO.get(doctorId);
		
		request.setAttribute("listSchedules", listSchedules);
		request.setAttribute("doctor", doctor);
		
		String listPage = "frontend/listScheduleByDoctor.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);		
	}

	public void viewScheduleDetail() throws ServletException, IOException {
		Integer scheduleId = Integer.parseInt(request.getParameter("id"));
		Lichkham schedule = scheduleDAO.get(scheduleId);
		
		request.setAttribute("schedule", schedule);

		String detailPage = "frontend/schedule_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage);
		requestDispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Lichkham> result = null; 
				
		if (keyword.equals("")) {
			result = scheduleDAO.listAll();
		} else {
			result = scheduleDAO.search(keyword);
		}
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		
		String resultPage = "frontend/search_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);		
	}
}
