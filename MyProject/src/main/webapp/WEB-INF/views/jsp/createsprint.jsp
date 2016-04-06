<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sprint</title>
<jsp:include page="homeTrue.jsp"></jsp:include>
</head>
<body>
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-lg-offset-2 col-lg-9  col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title">
					<div class="text-center">
						<h2>
							Sprint:
							<c:out value="${sessionScope.sprint.name}">
							</c:out>
						</h2>
					</div>
				</div>
			</div>
			<div class="panel-body">


				<div class="container col-lg-12 col-md-10">

					<c:if test="${sessionScope.activeSprint==sessionScope.sprint.id}">
						<form action="./SprintInfo" method="post">
							<button name="sprintID" value="${sprint.id}"
								class="btn btn-success">Complete sprint</button>
						</form>
					</c:if>
					<div class="row">
						<div class="col-lg-4 col-md-4">
							<h4>ToDo</h4>


							<c:if test="${empty listWithActivitiesToDoInSprint }">
								<div class="text-center">
									<h4>No issues with this status</h4>
								</div>
							</c:if>
							<c:forEach var="activity"
								items="${sessionScope.listWithActivitiesToDoInSprint}">
								<c:set scope="request" var="type" value="${activity.type}"></c:set>
								<c:set scope="request" var="priority"
									value="${activity.prioriy}"></c:set>
								<c:set scope="request" var="id" value="${activity.id}"></c:set>
								<c:set scope="request" var="issueKey"
									value="${activity.issueKey}"></c:set>
								<c:set scope="request" var="estimate"
									value="${activity.estimate}"></c:set>
								<c:set scope="request" var="assigneeId"
									value="${activity.assigneeID}"></c:set>

								<jsp:include page="smallIssueNoSummary.jsp"></jsp:include>
							</c:forEach>
						</div>
						<div class="col-lg-4 col-md-4">
							<h4>InProgress</h4>
							<c:if test="${empty listWithActivitiesInProgressInSprint }">
								<div class="text-center">
									<h4>No issues with this status</h4>
								</div>
							</c:if>
							<c:forEach var="activity"
								items="${sessionScope.listWithActivitiesInProgressInSprint}">
								<c:set scope="request" var="type" value="${activity.type}"></c:set>
								<c:set scope="request" var="priority"
									value="${activity.prioriy}"></c:set>
								<c:set scope="request" var="id" value="${activity.id}"></c:set>
								<c:set scope="request" var="issueKey"
									value="${activity.issueKey}"></c:set>
								<c:set scope="request" var="estimate"
									value="${activity.estimate}"></c:set>
								<c:set scope="request" var="assigneeId"
									value="${activity.assigneeID}"></c:set>

								<jsp:include page="smallIssueNoSummary.jsp"></jsp:include>
							</c:forEach>
						</div>
						<div class="col-lg-4 col-md-4">
							<h4>Done</h4>

							<c:if test="${empty listWithActivitiesDoneInSprint }">
								<div class="text-center">
									<h4>No issues with this status</h4>
								</div>
							</c:if>
							<c:forEach var="activity"
								items="${sessionScope.listWithActivitiesDoneInSprint}">
								<c:set scope="request" var="type" value="${activity.type}"></c:set>
								<c:set scope="request" var="priority"
									value="${activity.prioriy}"></c:set>
								<c:set scope="request" var="id" value="${activity.id}"></c:set>
								<c:set scope="request" var="issueKey"
									value="${activity.issueKey}"></c:set>
								<c:set scope="request" var="estimate"
									value="${activity.estimate}"></c:set>
								<c:set scope="request" var="assigneeId"
									value="${activity.assigneeID}"></c:set>

								<jsp:include page="smallIssueNoSummary.jsp"></jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title">All issues not in any sprint</div>
			</div>
			<div class="panel-body">
				<c:if test="${not empty sessionScope.activitiesNotInSprint }">
					<c:forEach var="activity"
						items="${sessionScope.activitiesNotInSprint}">

						<c:set scope="request" var="type" value="${activity.type}"></c:set>
						<c:set scope="request" var="priority" value="${activity.prioriy}"></c:set>
						<c:set scope="request" var="summary" value="${activity.summary}"></c:set>
						<c:set scope="request" var="id" value="${activity.id}"></c:set>
						<c:set scope="request" var="issueKey" value="${activity.issueKey}"></c:set>
						<c:set scope="request" var="estimate" value="${activity.estimate}"></c:set>
						<c:set scope="request" var="assigneeId"
							value="${activity.assigneeID}"></c:set>

						<jsp:include page="smallIssueWithButton.jsp"></jsp:include>
					</c:forEach>
				</c:if>

				<c:if test="${empty sessionScope.activitiesNotInSprint }">
					<div class="text-center">
						<h4>
							No issues not in Sprint. <a href="./CreateIssue">Create a new
								one ?</a>
						</h4>
					</div>
				</c:if>


			</div>
		</div>
	</div>
</body>
</html>