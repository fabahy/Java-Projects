<%@page import="com.dao.CourseDao"%>
<%@page import="com.entity.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
				<a class="navbar-brand">Courses List</a> <a href="add_course.jsp"><button
						type="button" class="btn btn-primary">Add new course</button> </a> <a
					href="../sortByNameCrs"><button class="btn btn-outline-dark"
						name="sort_by_name_std">Sort by name</button></a>
				<form class="d-flex" role="search" action="../searchCourse"
					method="get">
					<input class="form-control" type="search" placeholder="Search"
						aria-label="Search" name="input_crs">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>

			</div>
		</nav>
		<table class="table table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col" style="width: 5%">ID</th>
					<th scope="col" style="width: 20%">Name</th>
					<th scope="col" style="width: 30%">Lecture</th>
					<th scope="col" style="width: 10%">Year</th>
					<th scope="col" style="width: 10%">Note</th>
					<th scope="col" style="width: 5%"></th>
					<th scope="col" style="width: 5%">Action</th>
					<th scope="col" style="width: 5%"></th>
				</tr>
			</thead>
			<tbody>
				<%
				CourseDao dao = new CourseDao(DBConnect.getConn());
				List<Course> list = dao.getAllCourses();
				for (Course c : list) {
				%>
				<tr>
					<th scope="row"><%=c.getID()%></th>
					<td><%=c.getName()%></td>
					<td><%=c.getLecture()%></td>
					<td><%=c.getYear()%></td>
					<td><%=c.getNote()%></td>
					<td><a href="update_course.jsp?id=<%=c.getID()%>">
							<button type="button" class="btn btn-success">Edit</button>
					</a></td>
					<td><a href="../deleteCourse?id=<%=c.getID()%>">
							<button type="button" class="btn btn-danger">Delete</button>
					</a></td>
					<td><a href="enroll.jsp?id=<%=c.getID()%>"><button
								class="btn btn-outline-dark" name="enroll">Enroll</button></a></td>

				</tr>
				<%
				}
				%>

			</tbody>
		</table>
	</div>
</body>
</html>
