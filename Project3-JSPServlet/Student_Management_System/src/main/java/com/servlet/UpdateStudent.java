package com.servlet;

import com.dao.StudentDao;
import com.db.DBConnect;
import com.entity.Student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("name_std");
			String grade = req.getParameter("grade_std");
			String birthday = req.getParameter("birthday_std");
			String address = req.getParameter("address_std");
			String note = req.getParameter("note_std");
			String id = req.getParameter("id_std");

			Student s = new Student(id, name, Float.parseFloat(grade), birthday, address, note);
			StudentDao dao = new StudentDao(DBConnect.getConn());


			if (dao.updateStudent(s)) {
				resp.sendRedirect("./student/view_student.jsp");
			} else {
				resp.sendRedirect("./student/view_student.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
