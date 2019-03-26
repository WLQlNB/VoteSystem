package com.jxau.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxau.pojo.Subject;
import com.jxau.service.SubjectService;
import com.jxau.service.impl.SubjectServiceImpl;


public class DoModifyServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			SubjectService subjectService = new SubjectServiceImpl();
			Subject subject = subjectService.getSubject(id);
			
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/pages/add.jsp")
			       .forward(request, response);
		} catch (Exception e) {			
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
