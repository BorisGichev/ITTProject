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
	<div class="container" style="width: 900px">
		<div class="row">
			<div class="col-lg-8 col-md-12">

				<div class="row">
					<h1>
						<c:out value="${sessionScope.sprint.name}">
						</c:out>
					</h1>
				</div>

				<div class="row">
					<c:forEach var="activity"
						items="${sessionScope.activitiesNotInSprint}">
				Issue:<a href="./IssueAll?id=${activity.id}">${activity.summary}</a>
						<form action="./CreateSprint" method="post">
							<button name="activityID" value="${activity.id}"
								class="btn btn-primary">Add to Sprint</button>
						</form>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>