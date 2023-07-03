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

@WebServlet("/updateCourse")
public class UpdateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("name_crs");
			String lecture = req.getParameter("lecture_crs");
			String year = req.getParameter("year_crs");
			String note = req.getParameter("note_crs");
			String id = req.getParameter("id_crs");

			Course c = new Course(id, name, lecture, Integer.parseInt(year), note);
			CourseDao dao = new CourseDao(DBConnect.getConn());

			if (dao.updateCourse(c)) {
				resp.sendRedirect("./course/view_course.jsp");
			} else {
				resp.sendRedirect("./course/view_course.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
