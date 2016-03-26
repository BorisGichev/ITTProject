<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="issue.html" />
	<div class="container" style="width: 900px">
		<div class="row">
			<div class="col-lg-8 col-md-12">

				<!-- button for comment start   -->
				<div style="display: inline">

					<form action="./IssueAll.jsp" method="get" style="display: inline">
						<button class="btn btn-default">All</button>
					</form>
					<form action="./IssueComments.jsp" method="get"
						style="display: inline">
						<button class="btn btn-default">Comments</button>
					</form>
					<form action="./IssueWorkLog.jsp" method="get"
						style="display: inline">
						<button class="btn btn-default">Work Log</button>
					</form>
					<form action="./IssueHistory.jsp" method="get"
						style="display: inline">
						<button class="btn btn-default">History</button>
					</form>
					<form action="./IssueActivity.jsp" method="get" style="display: inline">
						<button class="btn btn-primary">Activity</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>