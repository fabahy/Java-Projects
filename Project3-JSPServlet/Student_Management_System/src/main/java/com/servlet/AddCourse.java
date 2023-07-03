package com.servlet;

import com.dao.CourseDao;
import com.db.DBConnect;
import com.entity.Course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("name_crs");
			String lecture = req.getParameter("lecture_crs");
			String year = req.getParameter("year_crs");
			String note = req.getParameter("note_crs");

			Course c = new Course(name, lecture, Integer.parseInt(year), note);
			CourseDao dao = new CourseDao(DBConnect.getConn());

			HttpSession session = req.getSession();

			if (dao.addCourse(c)) {
				session.setAttribute("successMsg", "Course added successfully");
				resp.sendRedirect("course/add_course.jsp");
			} else {
				session.setAttribute("errorMsg", "Something wrong on server");
				resp.sendRedirect("course/add_course.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
