<%@page import="com.dao.CourseDao"%>
<%@page import="com.entity.Student"%>
<%@page import="com.entity.Course"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.StudentDao"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.db.DBConnect"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Management</title>
<%@include file="components/bootstrap.jsp"%>
<style>
#tb {
	width: 90%;
	margin: 50px auto;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp">Home</a> <a
				class="navbar-brand" href="./student/view_student.jsp">Students</a>
			<a class="navbar-brand" href="./course/view_course.jsp">Courses</a>
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
	<%
	StudentDao std_dao = new StudentDao(DBConnect.getConn());
	Student std = std_dao.getStudentByID(1);
	List<Student> std_list = std_dao.getAllStudents();
	%>
	<div id="tb">
		<nav class="navbar bg-body-tertiary mb-4">
			<div class="container-fluid">
				<a class="navbar-brand">Student's name: <span class="text-info"><%=std.getName()%></span></a>
				<form class="d-flex" role="search"
					action="home.jsp?id_std=<%=std.getID()%>" method="get">
					<select class="form-control me-3"
						aria-label="Default select example" name="id_std">
						<%
						for (Student s : std_list) {
						%>
						<option value="<%=s.getID()%>"><%=s.getName()%></option>
						<%
						}
						%>
					</select>
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>

			</div>
		</nav>
		<table class="table table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col" style="width: 30%">Courses name</th>
					<th scope="col" style="width: 10%">Grade</th>
					<th scope="col" style="width: 20%">Lecture</th>
					<th scope="col" style="width: 20%">Year</th>
					<th scope="col" style="width: 20%">Note</th>
				</tr>
			</thead>
			<tbody>
				<%
				CourseDao dao = new CourseDao(DBConnect.getConn());
				List<Course> list = dao.getGradeCoursesEnrollment(1);
				for (Course c : list) {
				%>
				<tr>
					<th scope="row"><%=c.getName()%></th>
					<td><%=c.getGrade()%></td>
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
