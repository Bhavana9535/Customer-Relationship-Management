<%@page import="in.co.crm.Ctl.ProductDetailsCtl"%>
<%@page import="in.co.crm.Ctl.CRMView"%>
<%@page import="in.co.crm.Utility.DataUtility" %>
<%@page import="in.co.crm.Utility.ServletUtility"%>
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
<title>Product details</title>
</head>
<body>
<%@ include file="Header.jsp"%>
<div class="row">
		<div class="col-4"></div>
		<div class="col-4">

			<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
			<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>

			<form action="<%=CRMView.PRODUCT_DETAILS_CTL%>" method="post"
				enctype='multipart/form-data'>

				<jsp:useBean id="bean" scope="request"
					class="in.co.crm.Bean.ProductDetailsBean" />

				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=bean.getModifiedby()%>"> <input type="hidden"
					name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
				<input type="hidden" name="modifiedDateTime"
					value="<%=bean.getModifieddatetime()%>">



				<div class="container text-center">
					<%
						ServletUtility.getList(request);
					%>
					<%
						if (bean.getId() > 0) {
					%>
					<h1>Update Product details</h1>
					<%
						} else {
					%>
					<h1>Add Product details</h1>
					<%
						}
					%>
				</div>
				<hr>
				<div class="mb-3">
					<label class="form-label">Product Code</label> <input type="text"
						class="form-control" name="productCode"
						placeholder="Enter ProductCode here..."
						value="<%=DataUtility.getStringData(bean.getProductCode())%>">

					<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productCode", request)%></div>
				</div>


				<div class="mb-3">
					<label class="form-label">Product Name</label> <input type="text"
						class="form-control" name="productName"
						placeholder="Enter ProductName here..."
						value="<%=DataUtility.getStringData(bean.getProductName())%>">

					<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productName", request)%></div>
				</div>

				

		<div class="mb-3">
			<label class="form-label">Details</label>
			<textarea rows="3" cols="3" name="details"
				placeholder="Enter Details" class="form-control"><%=DataUtility.getStringData(bean.getDetails())%></textarea>
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("details", request)%></div>
		</div>

		<div class="mb-3">
			<label class="form-label">Price</label> <input
				type="text"
				class="form-control" name="price" placeholder="Enter Price here..."
				value="<%=DataUtility.getStringData(bean.getPrice())%>">

			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("price", request)%></div>
		</div>
		
		
		<div class="mb-3">
								<p>
									<label for="exampleInputPassword1">Product Category:</label>
								<div class="form-group">
									<select class="custom-select" name=category>
									<option>--------Select--------</option>
										<%
											Connection conn = JDBCDataSource.getConnection();
											String sql = "SELECT * FROM productcategory";
											PreparedStatement ps = conn.prepareStatement(sql);
											ResultSet rs = ps.executeQuery();
											while (rs.next()) {
										%>

										
										<option value="<%=rs.getLong(1)%>"><%=rs.getString(2)%></option>

										<%
											}
										%>
									</select>

									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("category", request)%></div>
								</div>
		</div>
		
		

			<div class="mb-3">
			<label class="form-label">Select Product Image</label>
               <input type="file" name="image" class="form-control">
			<%-- <img width="100px" height="100px" src="<%=CRMView.APP_CONTEXT%>/image/<%=bean.getImage()%>"> --%>
			</div>
		
		


		<div class="container text-center">
			<%
				if (bean.getId() > 0) {
			%>
			<input type="submit" class="btn btn-primary" name="operation"
				value="<%=ProductDetailsCtl.OP_UPDATE%>">
			<%
				} else {
			%>
			<input type="submit" class="btn btn-primary" name="operation"
				value="<%=ProductDetailsCtl.OP_SAVE%>">
			<%
				}
			%>
		</div>
		
		
		</form>
	</div>
	<div class="col-4"></div>
	</div>
	<br>
	
	<div style="margin-top: 0%">
	<%@include file="Footer1.jsp"%>
	</div>
</body>
</html>