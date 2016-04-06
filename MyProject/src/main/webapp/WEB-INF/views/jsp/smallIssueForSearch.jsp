
<div class="form-group">
	<div class="col-lg-12 col-md-12">

		<div class="col-lg-1 col-md-1">
			<img src="./ImageServlet?path=types/"+issue.type+".jpg" alt="Type"
				class="img-rounded" width="25" height="25" />
		</div>

		<div class="col-lg-1 col-md-1">
			<img src="./ImageServlet?path=priority/${priority}.jpg"
				alt="${priority}" class="img-rounded" width="25" height="25" />
		</div>
		<div class="col-lg-2 col-md-1">
			<a href="./Issue?id=${id}">${issueKey}</a>
		</div>
		<div class="col-lg-5 col-md-1">
			<div class="text-center">"+issue.summary+"</div>
		</div>
		<div class="col-lg-1 col-md-1">
			<div class="text-center">${estimate}</div>
		</div>




		<div class="col-lg-1 col-md-1">
			<img src="./ImageServletFromID?userId=${assigneeId}"
				class="img-rounded" width="30" height="30" alt="No assignee" />
		</div>


	</div>
</div>
