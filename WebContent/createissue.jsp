<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<div class="login-page">
					<div class="form"></div>
					<br /> <label>Issue type</label> <select name="rest">
						<option value="Bug">Bug</option>
						<option value="Task">Task</option>
						<option value="Sub-task">Sub-task</option>
						<option value="Story">Story</option>
						<option value="Epic">Epic</option>
					</select>
				</div>
			</div>
		</div>
	</div>
</body>
</html>