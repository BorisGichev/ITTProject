<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="trans" uri="http://www.springframework.org/tags"%>




<div class="form-group">
	<div class="col-md-12">
	
	
		<img src="./ImageServlet?path=${activity.type}.jpg" class="img-rounded"
			width="15" height="15"> 
			
			
			<label
			class="col-xl-6 col-md-6 control-label"> <c:out
				value="${username}"></c:out>
		</label> <label class="col-xl-6 col-md-6 control-label"> <c:out
				value="${email}"></c:out>
		</label>
	</div>
</div>



</body>
</html>