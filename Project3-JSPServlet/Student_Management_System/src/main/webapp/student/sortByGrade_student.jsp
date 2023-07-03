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
	width: 90%;
	margin: 50px auto;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="../index.jsp">Home</a> <a
				class="navbar-brand" href=".././student/view_student.jsp">Students</a>
			<a class="navbar-brand" href=".././course/view_course.jsp">Courses</a>
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
		<nav class="navbar bg-body-tertiary mb-4">
			<div class="container-fluid">
				<a class="navbar-brand">Students List</a> <a href="add_student.jsp"><button
						type="button" class="btn btn-primary">Add new student</button> </a> <a
					href="../sortByName"><button class="btn btn-outline-dark"
						name="sort_by_name_std">Sort by name</button></a> <a
					href="../sortByGrade"><button class="btn btn-outline-dark"
						name="sort_by_grade_std">Sort by grade</button></a>
				<form class="d-flex" role="search" action="../searchStudent" method="get">
					<input class="form-control" type="search" placeholder="Search"
						aria-label="Search" name="input_std">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>

			</div>
		</nav>
		<table class="table table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col" style="width: 5%">ID</th>
					<th scope="col" style="width: 20%">Name</th>
					<th scope="col" style="width: 5%">Grade</th>
					<th scope="col" style="width: 10%">Birthday</th>
					<th scope="col" style="width: 30%">Address</th>
					<th scope="col" style="width: 10%">Note</th>
					<th scope="col" style="width: 5%"></th>
					<th scope="col" style="width: 5%">Action</th>
					<th scope="col" style="width: 10%"></th>
				</tr>
			</thead>
			<tbody>
				<%
				StudentDao dao = new StudentDao(DBConnect.getConn());
				if (dao.ORDER.equals("descending")) {
					dao.ORDER = "ascending";
				} else
					dao.ORDER = "descending";
				String fi = "grade";
				List<Student> list = dao.sortStudents(fi, dao.ORDER);
				for (Student s : list) {
				%>
				<tr>
					<th scope="row"><%=s.getID()%></th>
					<td><%=s.getName()%></td>
					<td><%=s.getGrade()%></td>
					<td><%=s.getBirthday()%></td>
					<td><%=s.getAddress()%></td>
					<td><%=s.getNote()%></td>
					<td><a href="update_student.jsp?id=<%=s.getID()%>">
							<button type="button" class="btn btn-success">Edit</button>
					</a></td>
					<td><a href="../deleteStudent?id=<%=s.getID()%>">
							<button type="button" class="btn btn-danger">Delete</button>
					</a></td>
					<td><a href="view_enrollment.jsp?id=<%=s.getID()%>"><button
								type="button" class="btn btn-light">View courses</button></a></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>
	</div>
</body>
</html>
