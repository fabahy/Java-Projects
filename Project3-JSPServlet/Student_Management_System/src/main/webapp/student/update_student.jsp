<%@page import="com.entity.Student"%>
<%@page import="com.db.DBConnect"%>
<%@page import="com.dao.StudentDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Update student</title>
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
			<a class="navbar-brand" href="../index.jsp">Home</a> <a
				class="navbar-brand" href="view_student.jsp">Students</a> <a
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

	<%
	int Id = Integer.parseInt(request.getParameter("id"));
	StudentDao dao2 = new StudentDao(DBConnect.getConn());
	Student s = dao2.getStudentByID(Id);
	%>

	<form id="update_form" action="../updateStudent" method="post">
		<h1 class="text-center">Update student</h1>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Name</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="name_std"
				value="<%=s.getName()%>">
		</div>
			<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Grade</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="grade_std"
				value="<%=s.getGrade()%>">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Birthday</span>
			</div>
			<input type="date" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="birthday_std"
				value="<%=s.getBirthday()%>">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Address</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="address_std"
				value="<%=s.getAddress()%>">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Note</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="note_std"
				value="<%=s.getNote()%>">
		</div>
		<input type="hidden" name="id_std" value="<%=s.getID()%>">
		<div class="input-group mb-3">
			<button type="submit" class="btn btn-secondary ms-auto"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default">Update</button>
		</div>
	</form>

</body>
</html>
