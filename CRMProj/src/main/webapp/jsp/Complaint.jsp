<%@page import="in.co.crm.Ctl.ComplaintCtl"%>
<%@page import="in.co.crm.Utility.DataUtility" %>
<%@page import="in.co.crm.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complant</title>
</head>
<body>

<%@include file="Header.jsp"%>

<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
<form action="<%=CRMView.COMPLAINT_CTL%>" method="post">

<jsp:useBean id="bean" scope="request"
						class="in.co.crm.Bean.ComplaintBean" />
<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">


<%
						ServletUtility.getList(request);
					%>
					<%
						if (bean.getId() > 0) {
					%>
					<h1 class="text-center">Update Complant</h1>
					<%
						} else {
					%>
					<h1 class="text-center">Raise Complaint</h1>
					<%
						}
					%>


<table class="table table-striped">
			<tbody align="center">
				<tr>
					<td colspan="1">
						<form class="well form-horizontal">
							<fieldset>
								<div class="col-md-2">
									<label for="form_message">Complaint Subject:</label> <input type="text"
										name="subject" style="width: 115%"
										value="<%=DataUtility.getStringData(bean.getComplaintSubject())%>"
										placeholder="Enter Complaint Subject">
</div>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("subject", request)%></div>
									<br>
<div class="col-md-2">
									<label for="form_message">Details:</label>
									<textarea name="details" class="form-Center"
										placeholder="Write your Details" cols="30" rows="3"
										><%=DataUtility.getStringData(bean.getDetails())%></textarea>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("details", request)%></div>
</div>


<%
						if (bean.getId() > 0) {
					%>
					<div class="col-md-2">
									<label for="form_message">Answer:</label>
									<textarea name="answer" class="form-Center"
										placeholder="Write your Answer" cols="30" rows="3"
										value="<%=DataUtility.getStringData(bean.getAnswer())%>"></textarea>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("answer", request)%></div>
</div>

					<%
						} else {
					%>
					
					<%
						}
					%>




<%
						if (bean.getId() > 0) {
					%>
					<input type="submit" class="btn btn-primary" name="operation"
										value="<%=ComplaintCtl.OP_UPDATE%>">
					<%
						} else {
					%>
					<input type="submit" class="btn btn-primary" name="operation"
										value="<%=ComplaintCtl.OP_SAVE%>">
					<%
						}
					%>
							</fieldset>
						</form>
					</td>
				</tr>
			</tbody>
		</table>

</form>
	<%@include file="Footer.jsp"%>


</body>
</html>