<%@page import="com.entity.Course"%>
<%@page import="com.dao.CourseDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.StudentDao"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.db.DBConnect"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Management</title>
<%@include file="/components/bootstrap.jsp"%>
<style>
#tbStd {
	width: 70%;
	margin: 50px auto;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href=".././index.jsp">Home</a> <a
				class="navbar-brand" href="./view_student.jsp">Students</a> <a
				class="navbar-brand" href=".././course/view_course.jsp">Courses</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0"
					style="visibility: hidden">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Students</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Courses</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="tbStd">
		<%
		int id = Integer.parseInt(request.getParameter("id"));
		StudentDao std_dao = new StudentDao(DBConnect.getConn());
		Student s = std_dao.getStudentByID(id);
		%>
		<nav class="navbar bg-body-tertiary mb-4">
			<div class="container-fluid">
				<a class="navbar-brand">Course list of <span class="text-info"><%=s.getName()%></span>
				</a>
			</div>
		</nav>
		<table class="table table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col" style="width: 5%">ID</th>
					<th scope="col" style="width: 30%">Name</th>
					<th scope="col" style="width: 35%">Lecture</th>
					<th scope="col" style="width: 10%">Year</th>
					<th scope="col" style="width: 20%">Note</th>
				</tr>
			</thead>
			<tbody>
				<%
				CourseDao dao = new CourseDao(DBConnect.getConn());
				List<Course> list = dao.getCoursesEnrollment(id);
				for (Course c : list) {
				%>
				<tr>
					<th scope="row"><%=c.getID()%></th>
					<td><%=c.getName()%></td>
					<td><%=c.getLecture()%></td>
					<td><%=c.getYear()%></td>
					<td><%=c.getNote()%></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>
	</div>
</body>
</html>
