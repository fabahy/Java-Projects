package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CourseDao;
import com.db.DBConnect;

@WebServlet("/searchCourse")
public class SearchCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("input_crs");
		CourseDao dao = new CourseDao(DBConnect.getConn());
		CourseDao.listCrs = dao.getCoursesByName(input);
		resp.sendRedirect("./course/search_course.jsp");
	}

}
