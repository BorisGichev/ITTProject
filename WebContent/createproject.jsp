<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creating Project</title>
<jsp:include page="homeTrue.jsp"></jsp:include>
<style>
input, select {
	width: 150px;
	height: 25px;
	border: 1px #ccc solid;
	vertical-align: top;
}
</style>
</head>
<body>
	<div class="container" style="width: 1000px">
		<div class="row">
			<div class="col-lg-6 col-md-12">
				<div>
					<div>

						<form action="./CreateProjectServlet" method="post">
							<div class="row">
								<div class="col-lg-4 col-md-12">
									<label>Project name</label>
								</div>
								<div class="col-lg-2 col-md-12">
									<input type="text" name="name" placeholder="Project name" />
								</div>
							</div>
							<div class="row">
								<div class="col-lg-4 col-md-12">
									<label>Project key</label>
								</div>
								<div class="col-lg-2 col-md-12">
									<input type="text" name="key" placeholder="Project key" />
									<!-- this submit is referred by label outside the form -->
									<input type="submit" id="createprojectform" class="hidden" />
								</div>
							</div>
							<div class="row">
								<div class="col-lg-4 col-md-12">
									<label>Project leader</label>
								</div>
								<div class="col-lg-2 col-md-12">
									<select name="leader">
										<c:forEach var="employee" items="${employeesInOrg}">
											<option value="${employee.id}"><c:if
													test="${sessionScope.user.id==employee.id}">${employee.fullName}(admin)</c:if>
												<c:if test="${sessionScope.user.id!=employee.id}">${employee.fullName}</c:if></option>
										</c:forEach>
									</select>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px">
				<div class="col-lg-6 col-md-12">
					<!-- this label refers submit button in inside form -->
					<label for="createprojectform" class="btn btn-primary">Create
						project </label>
				</div>
			</div>
		</div>
		<div style="margin-top: 100px; margin-left: ">
			<img src="./images/creatingproject.jpg" width="60%" height="60%"/>
		</div>
	</div>
</body>
</html>