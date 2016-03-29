<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create issue</title>
<jsp:include page="homeTrue.jsp"></jsp:include>
</head>
<body>
	<div class="container" style="width: 900px">
		<div class="row">
			<div class="col-lg-8 col-md-12">
				<!-- issue=activity -->
				<form action="./CreateIssueServlet" method="post">
					<div class="row">
						<div class="col-lg-3 col-md-12">
							<label>Summary</label>
						</div>
						<div class="col-lg-2 col-md-12">
							<textarea rows="5" cols="50" name="summary"
								placeholder="Add your summary here..."></textarea>
							<input type="submit" id="createissueform" class="hidden" />
						</div>
					</div>

					<div class="row">
						<div class="col-lg-3 col-md-12">
							<label>Issue type</label>
						</div>

						<div class="col-lg-2 col-md-12">
							<select name="issueType">
								<option value="Bug">Bug</option>
								<option value="Task">Task</option>
								<option value="Sub-task">Sub-task</option>
								<option value="Story">Story</option>
								<option value="Epic">Epic</option>
							</select>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-lg-4 col-md-12">
				<!-- this label refers submit button in inside form -->
				<label for="createissueform" class="btn btn-primary">Create
					issue </label>
			</div>
		</div>
	</div>
</body>
</html>