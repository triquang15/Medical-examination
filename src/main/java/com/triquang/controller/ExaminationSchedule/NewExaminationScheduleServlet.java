package com.triquang.controller.ExaminationSchedule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.triquang.service.ExaminationScheduleService;

/**
 * Servlet implementation class NewExaminationScheduleServlet
 */
@WebServlet("/admin/new_schedule")
public class NewExaminationScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewExaminationScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExaminationScheduleService scheduleService = new ExaminationScheduleService(request, response) ;
		scheduleService.showScheduleNewForm();
	}

}
