<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creating Project</title>
<jsp:include page="homeTrue.jsp"></jsp:include>
</head>
<body>
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title">SCRUM Project details</div>

			</div>
			<div class="panel-body">


				<form method="post" action="./CreateProjectServlet"
					class="form-horizontal">

					<div class="form-group">
						<label for="oranization" class="col-md-3 control-label">Project
							name</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="name"
								placeholder="Project name" />
						</div>
					</div>



					<div class="form-group">
						<label class="col-md-3 control-label">Project key </label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="key"
								placeholder="Project key" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">User name</label>
						<div class="col-md-9">
							<select name="leader" class="form-control">
								<c:forEach var="employee" items="${employeesInOrg}">
									<option value="${employee.id}"><c:if
											test="${sessionScope.user.id==employee.id}">${employee.fullname}(admin)</c:if>
										<c:if test="${sessionScope.user.id!=employee.id}">${employee.fullname}</c:if></option>
								</c:forEach>
							</select>
						</div>
					</div>


					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info"
								name="action">
								<i class="icon-hand-right"></i> Create Project
							</button>
						</div>
					</div>


					<div class="form-group">
						<label class="col-md-3 control-label">Workflow for this
							kind of project </label> 
						<label class="col-md-6 control-label">Types
							of issues for this kind of project </label>

					</div>
					<div class="form-group">
						<div class="col-md-6">
							<img src="./ImageServlet?path=workflow.jpg" class="img-rounded"
								width="300">
						</div>
						<div class="col-md-6">
							<img src="./ImageServlet?path=issuetypes.jpg" class="img-rounded"
								width="150">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>