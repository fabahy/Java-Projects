<%@page import="com.entity.Course"%>
<%@page import="com.dao.CourseDao"%>
<%@page import="com.db.DBConnect"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Update course</title>
<%@include file="/components/bootstrap.jsp"%>
<style>
#update_form {
	width: 50%;
	margin: auto;
	margin-top: 100px;
}

.input-group>.input-group-prepend {
	flex: 0 0 10%;
}

.input-group .input-group-text {
	width: 100%;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href=".././index.jsp">Home</a> <a
				class="navbar-brand" href="../student/view_student.jsp">Students</a>
			<a class="navbar-brand" href="./view_course.jsp">Courses</a>
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
	int Id = Integer.parseInt(request.getParameter("id"));
	CourseDao dao2 = new CourseDao(DBConnect.getConn());
	Course c = dao2.getCourseByID(Id);
	%>

	<form id="update_form" action="../updateCourse" method="post">
		<h1 class="text-center">Update course</h1>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Name</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="name_crs"
				value="<%=c.getName()%>">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Birthday</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="lecture_crs"
				value="<%=c.getLecture()%>">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Address</span>
			</div>
			<input type="number" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="year_crs"
				value="<%=c.getYear()%>">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Note</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="note_crs"
				value="<%=c.getNote()%>">
		</div>
		<input type="hidden" name="id_crs" value="<%=c.getID()%>">
		<div class="input-group mb-3">
			<button type="submit" class="btn btn-secondary ms-auto"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default">Update</button>
		</div>
	</form>

</body>
</html>
