package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CourseDao;
import com.db.DBConnect;

@WebServlet("/deleteCourse")
public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		CourseDao dao = new CourseDao(DBConnect.getConn());

		if (dao.deleteCourse(id)) {
			resp.sendRedirect("./course/view_course.jsp");
		}

	}

}
