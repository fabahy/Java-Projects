package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StudentDao;
import com.db.DBConnect;

@WebServlet("/searchStudent")
public class SearchStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input_std");
		StudentDao dao = new StudentDao(DBConnect.getConn());
		StudentDao.listStd = dao.getStudentsByName(input);
		resp.sendRedirect("./student/search_student.jsp");
	}

}
