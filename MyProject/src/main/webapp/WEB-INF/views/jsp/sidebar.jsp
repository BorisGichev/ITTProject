<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>

<!-- Sidebar -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">

		<div align="center">

			<font color="white"><trans:message code="sidebar.orgname" /></font>

		</div>

		<li class="sidebar-brand"><a href="#"><c:out
					value="${sessionScope.organization.name}" /> </a></li>



		<c:if test="${sessionScope.user.admin == 1 }">
			<div align="center">

				<font color="white"><trans:message code="sidebar.admin" /></font>

			</div>
		</c:if>
		<c:if test="${sessionScope.user.admin == 0 }">
			<div align="center">

				<font color="white"><trans:message code="sidebar.employee" /></font>

			</div>
		</c:if>
		<div class="text-center">
			<img src="./ImageServlet?path=<c:out value="${user.avatarPath}"/>"
				class="img-rounded" width="100">

		</div>

		<li class="sidebar-brand"><a href="./UpdateProfile"><c:out
					value="${user.fullname}" /> </a></li>

		<div align="center">

			<font color="white">Current Project</font>

		</div>
		<li class="sidebar-brand"><a href="./SelectProject?projectId=${project.id}">${project.name}</a></li>


		<li><a href="#"><trans:message code="sidebar.backlog" /></a></li>
		<li><a href="#"><trans:message code="sidebar.activesprint" /></a></li>
		<li><a href="#"><trans:message code="sidebar.reports" /></a></li>
		<li><a href="#"><trans:message code="sidebar.issues" /></a></li>
	</ul>
</div>
<!-- /#sidebar-wrapper -->
