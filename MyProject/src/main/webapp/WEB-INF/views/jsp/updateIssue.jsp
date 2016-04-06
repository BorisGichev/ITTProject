<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update issue</title>
<jsp:include page="homeTrue.jsp"></jsp:include>
</head>
<body>

	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<div class="panel panel-info">
			<div class="panel-heading">

				<div class="panel-title">Issue details</div>

			</div>
			<div class="panel-body">


				<spring:form method="post" action="./CreateIssue"
					class="form-horizontal" commandName="issue">

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Select
							project </label>
						<div class="col-lg-3 col-md-12">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <c:if test="${not empty project }">
							${project.name}
							</c:if> <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<c:forEach var="project2" items="${projects}">
										<c:if test="${project.id!=project2.id }">
											<li><a
												href="./SelectProjectForIssue?projectId=${project2.id}">${project2.name}</a></li>
										</c:if>
									</c:forEach>
								</ul></li>
						</div>
					</div>


					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Issue
							type </label>
						<div class="col-md-9">
							<spring:select name="type" path="type" class="form-control">
								<option value="${oldIssue.type}">${oldIssue.type}</option>
								<option value="notype"></option>
								<option value="Task">Task</option>
								<option value="Sub-Task">Sub-Task</option>
								<option value="Bug">Bug</option>
								<option value="Story">Story</option>
								<option value="Epic">Epic</option>
							</spring:select>
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Estimate
							in hours </label>
						<div class="col-md-9">
							<spring:input type="double" class="form-control" path="estimate" />
						</div>
					</div>

					<div class="form-group">
						<label for="summary" class="form-control"
							class="col-md-3 control-label"> Summary </label>

						<spring:textarea name="summary" path="summary"
							class="form-control" rows="5" cols="15"></spring:textarea>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Reporter
						</label>
						<div class="col-md-9">
							<spring:select name="reporter" path="reportedID"
								class="form-control">
								<option value="${user.id}">${user.username} (You)</option>

								<c:forEach var="user2" items="${usersByOrg}">
									<c:if test="${user.id!=user2.id }">
										<option value="${user2.id}">${user2.username}</option>
									</c:if>
								</c:forEach>
							</spring:select>
						</div>
					</div>

					<div class="form-group">
						<label for="summary" class="form-control"
							class="col-md-3 control-label"> Description </label>

						<spring:textarea name="description" path="description"
							class="form-control" rows="5" cols="15"></spring:textarea>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Priority
						</label>
						<div class="col-md-9">
							<spring:select name="priority" path="prioriy"
								class="form-control">
								<option value="3">Normal</option>
								<option value="4">High</option>
								<option value="5">Highest</option>
								<option value="2">Low</option>
								<option value="1">Lowest</option>
							</spring:select>
						</div>
					</div>


					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Type
							of the link for the linked issue </label>
						<div class="col-md-9">
							<spring:select name="linktype" path="connectedType"
								class="form-control">
								<option value="N/A"></option>
								<option value="Blocks">Blocks</option>
								<option value="Is blocked by">Is blocked by</option>
								<option value="Duplicates">Duplicates</option>
								<option value="Is duplicated by">Is duplicated by</option>
								<option value="Caused by">Caused by</option>
								<option value="Relates to">Relates to</option>
							</spring:select>
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Linked
							issue </label>
						<div class="col-md-9">
							<spring:select name="assignee" path="connectedToID"
								class="form-control">

								<option value="0"></option>
								<c:forEach var="issue" items="${issuesForProject}">
									<option value="${issue.id}">${issue.summary}</option>
								</c:forEach>
							</spring:select>
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Assignee
						</label>
						<div class="col-md-9">

							<spring:select name="assignee" path="assigneeID"
								class="form-control">
								<option value="${user.id}">${user.username} (You)</option>

								<c:forEach var="user2" items="${usersByOrg}">
									<c:if test="${user.id!=user2.id }">
										<option value="${user2.id}">${user2.username}</option>
									</c:if>
								</c:forEach>
							</spring:select>
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">Sprint
						</label>
						<div class="col-md-9">
							<spring:select name="sprintId" path="sprintID" class="form-control">
								<option value="0"></option>
								<c:forEach var="sprint" items="${sprintsForProject}">
									<option value="${sprint.id}">${sprint.name}</option>
								</c:forEach>
							</spring:select>
						</div>
					</div>


					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<button id="btn-signup" type="submit" class="btn btn-info">
								<i class="icon-hand-right"></i> Continue
							</button>

						</div>
					</div>
				</spring:form>
			</div>
		</div>
	</div>
</body>
</html>