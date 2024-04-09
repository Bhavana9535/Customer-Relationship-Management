<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div class="container mt-3">
	<h3 class="text-center">Customer Relationship Management (CRM).</h3>
		</div>
	<div id="carouselExampleControls" class="carousel slide mt-3"
		data-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img class="d-block w-100" src="/CRMProj/img/crm1.jpg"
					alt="First slide" style="height: 550px; width: 500px;">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" src="/CRMProj/img/crm2.jpg"
					alt="Second slide" style="height: 550px; width: 500px;">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" src="/CRMProj/img/crm3.jpg"
					alt="Third slide" style="height: 550px; width: 500px;">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleControls"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleControls"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<hr>
	<h3 class="text-center">Here are 3 features to look for in CRM
		software</h3>
	<hr>
	<!-- WelCome End
 -->
	<!-- Card start-->
	<div class="card-deck mt-5">
		<div class="card">
			<img class="card-img-top" src="/CRMProj/img/crm3.jpg"
				alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title">Customer service</h5>
				<p class="card-text">CRM software can help you acquire and
					retain customers by providing excellent customer service. Look for
					the following capabilities that will allow your sales reps and
					customer support team to perform their best: Gives you a 360-degree
					comprehensive view of customers that shows everything there is to
					know about a customer from the first point of contact Automatically
					tracks all points of communications, from lead acquisition to
					closed sales and sales histories Logs all incidents, website
					visits, purchase histories, and other activities for future
					reference and to keep all reps on the same page.</p>
			</div>
			<div class="card-footer">
				<small class="text-muted">Last updated 3 mins ago</small>
			</div>
		</div>
		<div class="card">
			<img class="card-img-top" src="/CRMProj/img/7053342.jpg"
				alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title">Employee tracking</h5>
				<p class="card-text">CRM software is a great way to track
					employee activity and performance. Choose software that gives
					employees their own accounts where they can individually track
					their hours, tasks, meetings, sales numbers, goals and other items.
					The software should also give supervisors and upper management
					access to dashboards that let them view individual employee goals,
					completed tasks and other metrics of productivity. This can help
					managers write employee performance reviews, create incentives,
					reward strong employees, and identify those who are struggling and
					address areas of improvement. .</p>
			</div>
			<div class="card-footer">
				<small class="text-muted">Last updated 3 mins ago</small>
			</div>
		</div>
		<div class="card">
			<img class="card-img-top" src="/CRMProj/img/crm1.jpg"
				alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title">Lead management</h5>
				<p class="card-text">A lead management feature will help you to
					identify your leads and the actions theyve taken along the sales
					cycle. Through the lead management process, youll be able to score
					your leads and, if needed, filter them off to a different member of
					your team to turn select leads into customers. Sometimes dead leads
					sit in a CRM for weeks or months. A smart sales manager stays on
					top of leads and redistributes quiet or seemingly dead leads to
					different members of their team for reengagement. .</p>
			</div>
			<div class="card-footer">
				<small class="text-muted">Last updated 3 mins ago</small>
			</div>
		</div>
	</div>
	<!-- Card End -->
	<br>

	<%@include file="Footer.jsp"%>
</body>
</html>