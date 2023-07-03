package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CourseDao;
import com.db.DBConnect;

@WebServlet("/enrollStudent")
public class EnrollStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ID_Student = req.getParameter("id_std");
		String ID_Course = req.getParameter("id_crs");
		String Grade_Course = req.getParameter("grade_crs");
		CourseDao dao = new CourseDao(DBConnect.getConn());
		if (dao.enrollStudent(ID_Student, ID_Course, Grade_Course))
			resp.sendRedirect("./course/view_course.jsp");
	}

}
