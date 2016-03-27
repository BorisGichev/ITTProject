<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Work Profile</title>
<jsp:include page="./homeTrue.jsp"></jsp:include>
</head>
<body>
	<div class="container" style="width: 1000px; background-color: #E6E9EB">
		<div class="row">
			<div class="col-lg-12 col-md-12">
				<img src="${not empty user.avatarPath}.jpg" width="30px"
					height="30px" style="display: inline" />
				<h2 style="display: inline">PROFILE</h2>
			</div>
		</div>
	</div>
	<div class="container" style="width: 1000px">
		<div class="row">
			<div class="col-lg-8 col-md-6">
				<h3>Summary</h3>
				<div class="row">
					<div class="col-lg-2 col-md-6">Avatar:</div>
					<div class="col-lg-2 col-md-6">
						<button>
							<img src="${not empty user.avatarPath}.jpg" />
						</button>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2 col-md-6">Username:</div>
					<div class="col-lg-2 col-md-6">
						<c:out value="${not empty user.username}"></c:out>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2 col-md-6">email:</div>
					<div class="col-lg-2 col-md-6">
						<c:out value="${not empty user.email}"></c:out>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-2 col-md-6">Passowrd:</div>
					<div class="col-lg-4 col-md-6">
						<a href="#">change passowrd</a>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12 col-md-12">
						<div id="Foo" class="collapse">
							<table>
								<tr>
									<td width="105px" style="color: red;">Username: <br
										style="display: block; margin: 3px 0;" /> email:<br />

									</td>
									<td><form action="" method="post">
											<br /> <input type="newusername" name="first"
												placeholder="First name" value="${not empty user.username }" />
											<br /> <input type="text" name="newemail"
												placeholder="email" value="${not empty user.email }" /> <br />
											<button>Confirm</button>
										</form></td>

								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="col-lg-2 col-md-6">
					<a href="#Foo" class="btn btn-default" data-toggle="collapse">Make
						changes </a>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<h3>Activity Stream</h3>
			</div>
		</div>
	</div>
</body>
</html>