<%@page import="in.co.crm.Model.ProductDetailsModel"%>
<%@page import="in.co.crm.Model.UserModel"%>
<%@page import="in.co.crm.Bean.UserBean"%>
<%@page import="in.co.crm.Bean.ProductDetailsBean"%>
<%@page import="in.co.crm.Ctl.ProductInquiryCtl"%>
<%@page import="in.co.crm.Utility.ServletUtility"%>
<%@page import="in.co.crm.Utility.DataUtility"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="in.co.crm.Utility.JDBCDataSource"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ProductInquiry</title>
</head>
<body>

<%@ include file="Header.jsp"%>
<div class="row">
		<div class="col-4"></div>
		<div class="col-4">

			<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
			<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
<%UserBean user = (UserBean)session.getAttribute("user"); %>
			<form action="<%=CRMView.ProductInquiry_CTL%>" method="post">

				<jsp:useBean id="bean" scope="request"
					class="in.co.crm.Bean.ProductInquiryBean" />

				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedby()%>"> <input type="hidden"
					name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
				<input type="hidden" name="modifiedDateTime"
					value="<%=bean.getModifieddatetime()%>">


				<div class="container text-center mt-3">
                        <%
						ServletUtility.getList(request);
					%>
					<%
						if (bean.getId() > 0) {
					%>
					<h1 class="text-center">Update Product Inquiry</h1>
					<%
						} else {
					%>
					<h1 class="text-center">Product Inquiry</h1>
					<%
						}
					%>
					
					</div>

<hr>
				<div class="mb-3">
					<label class="form-label">Enq_No</label> <input type="text"
						class="form-control" name="enqno"
						placeholder="Enter Enq_No here..."
						value="<%=DataUtility.getStringData(bean.getEnqNo())%>">

					<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("enqno", request)%></div>
				</div>




<%
						if (bean.getId() > 0) {
					%>
					<div class="mb-3">
								<p>
									<label for="exampleInputPassword1">Product:</label>
								<div class="form-group">
									<select class="custom-select" name="productName">
									<% ProductDetailsModel model = new ProductDetailsModel();
					ProductDetailsBean product = model.findByPk(DataUtility.getLong(bean.getProduct()));
					%>
									<option value="<%=bean.getProduct()%>">
									<% ProductDetailsModel model2 = new ProductDetailsModel();
					ProductDetailsBean product2 = model2.findByPk(DataUtility.getLong(bean.getProduct()));
					%>
									<%out.print(product2.getProductName());%>
									
									</option>
						</select>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productName", request)%></div>
								</div>
		</div>
					
					
					<%
						} else {
					%>
					<div class="mb-3">
								<p>
									<label for="exampleInputPassword1">Product:</label>
								<div class="form-group">
									<select class="custom-select" name="productName">
									<option>--------Select--------</option>
										<%
											Connection conn = JDBCDataSource.getConnection();
											String sql = "SELECT * FROM productdetails";
											PreparedStatement ps = conn.prepareStatement(sql);
											ResultSet rs = ps.executeQuery();
											while (rs.next()) {
										%>

										
										<option value="<%=rs.getLong(1)%>"><%=rs.getString(3)%></option>
										<%
											}
										%>
									</select>

									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productName", request)%></div>
								</div>
		</div>
					<%
						}
					%>





<div class="mb-3">
			<label class="form-label">Details</label>
			<textarea rows="3" cols="3" name="details"
				placeholder="Enter Details" class="form-control"><%=DataUtility.getStringData(bean.getDeatils())%></textarea>
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("details", request)%></div>
		</div>

<%
						if (bean.getId() > 0) {
					%>
					
					<div class="mb-3">
					<label class="form-label">User Email</label> <input type="text"
						class="form-control" name="username"
						placeholder="Enter User Name here..."
						value="<% UserModel model1 = new UserModel();
					UserBean product1 = model1.findByPk(DataUtility.getLong(bean.getUserName()));
					out.print(product1.getEmail());
					%>"
						
						>
					<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("username", request)%></div>
				</div>
					
					<%
						} else {
					%>
					<div class="mb-3">
					<label class="form-label">User Email</label> <input type="text"
						class="form-control" name="username"
						placeholder="Enter User Name here..." value="<%=DataUtility.getStringData(user.getEmail()) %>">
					<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("username", request)%></div>
				</div>
					<%
						}
					%>





<%
						if (bean.getId() > 0) {
					%>
					<div class="mb-3">
									<label for="form-label">Answer:</label>
									<textarea name="answer" class="form-control"
										placeholder="Write your Answer" cols="3" rows="3"
										value="<%=DataUtility.getStringData(bean.getAnswer())%>"></textarea>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("answer", request)%></div>
</div>

					<%
						} else {
					%>


<% }%>

<div class="container text-center">
			
			<%
						if (bean.getId() > 0) {
					%>
					<input type="submit" class="btn btn-primary" name="operation"
										value="<%=ProductInquiryCtl.OP_UPDATE%>">
					<%
						} else {
					%>
					<input type="submit" class="btn btn-primary" name="operation"
				value="<%=ProductInquiryCtl.OP_SAVE%>">
					<%
						}
					%>
					
</div>

</form>
</div>
<div class="col-4"></div>
</div>
<br>
<%@include file="Footer.jsp"%>
</body>
</html>