<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title>Add new course</title>
<%@include file="/components/bootstrap.jsp"%>
<style>
#add_form {
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
				class="navbar-brand" href="../student/view_student.jsp">Students</a> <a
				class="navbar-brand" href="./view_course.jsp">Courses</a>
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
	<c:if test="${not empty errorMsg}">
		<p class="fs-3 text-center text-danger">${errorMsg}</p>
		<c:remove var="errorMsg" scope="session" />
	</c:if>
	<c:if test="${not empty successMsg}">
		<p class="fs-3 text-center text-success">${successMsg}</p>
		<c:remove var="successMsg" scope="session" />
	</c:if>
	<form id="add_form" action="../addCourse" method="post">
		<h1 class="text-center">Add new course</h1>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Name</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="name_crs">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Lecture</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="lecture_crs">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Year</span>
			</div>
			<input type="number" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="year_crs">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">Note</span>
			</div>
			<input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="note_crs">
		</div>
		<div class="input-group mb-3">
			<button type="submit" class="btn btn-secondary ms-auto"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default">Insert</button>
		</div>
	</form>

</body>
</html>
