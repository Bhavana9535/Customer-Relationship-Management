<%@page import="in.co.crm.Ctl.ComplaintListCtl"%>
<%@page import="in.co.crm.Bean.ComplaintBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.crm.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaint List</title>
</head>
<body>

	<div class="container-fluid p-0 m-0">
		<%@include file="Header.jsp"%>
	</div>
	<br>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<form action="<%=CRMView.COMPLAINT_LIST_CTL%>" method="post">


		<div class="container">
			<h1 class="text-center">Show Complaint List</h1>
			<%
				if (user.getRoleid() == 3) {
			%>
			<table width="100%">
				<tr>
					<td align="center"><label>Subject:</label> <input type="text"
						name="complaintsubject" placeholder="Enter Subject"
						value="<%=ServletUtility.getParameter("complaintsubject", request)%>">
						&emsp;&emsp; <input type="submit" name="operation"
						value="<%=ComplaintListCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input
						type="submit" name="operation"
						value="<%=ComplaintListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<%
				}
			%>
			<div class="row">
				<div class="col-12">
					<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
					<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>

					<%
						int index = 1;
						List list = ServletUtility.getList(request);
						Iterator it = list.iterator();
						while (it.hasNext()) {
							ComplaintBean bean = (ComplaintBean) it.next();
					%>
					<div class="card mt-3">
						<img class="card-img-top m-4 mx-auto" src="img/notepad.png"
							style="max-width: 100px;" alt="Card image cap">
						<div class="card-body px-5">
						<!-- (!bean.getAnswer().equalsIgnoreCase("answer")) -->
							<%
							if ((!bean.getAnswer().equalsIgnoreCase("answer"))||user.getRoleid()==3){
							%>

							ID :
							<h5 class="card-title text-center"><%=index++%></h5>
							Complaint Subject :
							<h5 class="card-title text-center"><%=bean.getComplaintSubject()%></h5>
							Details :
							<p class="card-title text-center"><%=bean.getDetails()%></p>

							<%
								if (user.getRoleid() == 2) {
							%>
							<%
								if (bean.getAnswer().equalsIgnoreCase("answer")) {
							%>
							<%
								} else {
							%>
							Answer :
							<h5 class="card-title text-center"><%=bean.getAnswer()%></h5>

							<%
								}
							%>
							<%
								} else {
							%>
							<div class="container text-center mt-2">
								<a href="<%=CRMView.COMPLAINT_LIST_CTL%>?id=<%=bean.getId()%>"
									class="btn btn-danger">Delete</a> <a
									href="<%=CRMView.COMPLAINT_CTL%>?id=<%=bean.getId()%>"
									class="btn btn-primary">Update</a>
							</div>

							<%
								}
							%>
							<%
								}
							%>

						</div>
					</div>

					<%
						}
					%>




				</div>
			</div>
	</form>
	</div>
	<br>



	<%@include file="Footer.jsp"%>

</body>
</html>