package com.triquang.controller.ExaminationSchedule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.triquang.service.ExaminationScheduleService;

/**
 * Servlet implementation class UpdateExaminationScheduleServlet
 */
@WebServlet("/admin/update_schedule")
public class UpdateExaminationScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExaminationScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExaminationScheduleService scheduleService = new ExaminationScheduleService(request, response);
		scheduleService.updateSchedule();
	}

}
