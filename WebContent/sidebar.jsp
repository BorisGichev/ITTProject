

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sidebar -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">

		<li class="sidebar-brand">Organization name</li>

		<li class="sidebar-brand"><a href="#"><c:out
					value="${sessionScope.user.organizationName}" /> </a></li>


		<c:if test="${sessionScope.user.admin == 1 }">
			<li class="sidebar-brand">ADMINISTRATOR</li>
		</c:if>
		<c:if test="${sessionScope.user.admin == 0 }">
			<li class="sidebar-brand">EMPLOYEE</li>
		</c:if>

		<li class="sidebar-brand"><a href="#"><c:out
					value="${sessionScope.user.username}" /> </a></li>


		<li><a href="#">Dashboard</a></li>
		<li><a href="#">Shortcuts</a></li>
		<li><a href="#">Overview</a></li>
		<li><a href="#">Events</a></li>
		<li><a href="#">About</a></li>
		<li><a href="#">Services</a></li>
		<li><a href="#">Contact</a></li>
	</ul>
</div>
<!-- /#sidebar-wrapper -->
