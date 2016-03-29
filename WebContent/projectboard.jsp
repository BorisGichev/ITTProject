<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project borad</title>
<jsp:include page="homeTrue.jsp"></jsp:include>
</head>
<body>
	<div class="container" style="width: 1000px">
		<div class="row" align="center">

			<div id="sprint" class="collapse">
				<div class="col-lg-3 col-md-12">
					<label>Sprint name</label>
				</div>
				<div class="col-lg-2 col-md-12">
					<form style="display: inline" action="./CreateSprint" method="get">
						<input type="text" name="name" /><input type="submit"
							class="btn btn-primary" value="Create Sprint" />
					</form>
				</div>
			</div>
		</div>
		<div class="row" align="center">
			<button href="#sprint" class="btn btn-primary" data-toggle="collapse">New
				Sprint</button>
		</div>

		<div class="row">
			<div class="col-lg-12 col-md-12">
				<c:forEach var="entry" items="${activitiesBySprint}">
					<h4>
						<a href="./SprintInfo?id=${entry.key.id}">Sprint:${entry.key.name}</a>
					</h4>
					<br />
					<c:forEach var="activity" items="${entry.value}">
						<a href="./IssueAll?id=${activity.id}">Issue:${activity.summary}</a>
					</c:forEach>
					<hr />
				</c:forEach>
				<hr />
				<c:forEach var="activity"
					items="${sessionScope.activitiesNotInSprint}">
				Issue:<a href="./IssueAll?id=${activity.id}">${activity.summary}</a>
					<form action="./IssueAll" method="get">
						<button name="id" value="${activity.id}" class="btn btn-primary">View</button>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>