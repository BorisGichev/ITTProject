<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sprint</title>
<jsp:include page="homeTrue.jsp"></jsp:include>
</head>
<body>
	<div class="container" style="width: 900px">
		<div class="row">
			<h1>
				<c:out value="${sessionScope.sprint.name}">
				</c:out>
			</h1>
		</div>
		<c:if test="${sessionScope.activeSprint==sessionScope.sprint.id}">
			<form action="./SprintInfo" method="post">
				<button name="sprintID" value="${sprint.id}" class="btn btn-success">Complete
					sprint</button>
			</form>F
		</c:if>
		<div class="row">
			<div class="col-lg-4 col-md-12">
				<c:forEach var="activity"
					items="${sessionScope.listWithActivitiesToDoInSprint}">
				Issue:<a href="./Issue?id=${activity.id}">${activity.summary}</a>
					<form action="./Issue" method="get">
						<button name="id" value="${activity.id}" class="btn btn-primary">View</button>

					</form>
				</c:forEach>
			</div>
			<div class="col-lg-4 col-md-12">
				<c:forEach var="activity"
					items="${sessionScope.listWithActivitiesInProgressInSprint}">
				Issue:<a href="./IssueA?id=${activity.id}">${activity.summary}</a>
					<form action="./IssueA" method="get">
						<button name="id" value="${activity.id}" class="btn btn-primary">View</button>

					</form>
				</c:forEach>
			</div>
			<div class="col-lg-4 col-md-12">
				<c:forEach var="activity"
					items="${sessionScope.listWithActivitiesDoneInSprint}">
				Issue:<a href="./Issue?id=${activity.id}">${activity.summary}</a>
					<form action="./Issue" method="get">
						<button name="id" value="${activity.id}" class="btn btn-primary">View</button>

					</form>
				</c:forEach>
			</div>

		</div>
	</div>
	<div class="container" style="width: 900px">
		<div class="row">
			<div class="col-lg-8 col-md-12">

				<hr />
				<div class="row">
					<c:forEach var="activity"
						items="${sessionScope.activitiesNotInSprint}">
				Issue:<a href="./Issue?id=${activity.id}">${activity.summary}</a>
						<form action="./SprintInfo" method="get">
							<button name="activityID" value="${activity.id}"
								class="btn btn-primary">Add to Sprint</button>
							<input name="id" value="${sessionScope.sprint.id}" class="hidden" />
						</form>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>