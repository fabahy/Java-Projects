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
import javax.servlet.http.HttpSession;

@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("name_std");
			String grade = req.getParameter("grade_std");
			String birthday = req.getParameter("birthday_std");
			String address = req.getParameter("address_std");
			String note = req.getParameter("note_std");

			Student s = new Student(name, Float.parseFloat(grade), birthday, address, note);
			StudentDao dao = new StudentDao(DBConnect.getConn());

			HttpSession session = req.getSession();

			if (dao.addStudent(s)) {
				session.setAttribute("successMsg", "Student added successfully");
				resp.sendRedirect("student/add_student.jsp");
			} else {
				session.setAttribute("errorMsg", "Something wrong on server");
				resp.sendRedirect("student/add_student.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
