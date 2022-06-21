package com.triquang.controller.ExaminationSchedule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.triquang.service.ExaminationScheduleService;

/**
 * Servlet implementation class ListExaminationScheduleServlet
 */
@WebServlet("/admin/list_schedule")
public class ListExaminationScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExaminationScheduleService scheduleService = new ExaminationScheduleService(request, response);
		scheduleService.listSchedules();
	}


}
