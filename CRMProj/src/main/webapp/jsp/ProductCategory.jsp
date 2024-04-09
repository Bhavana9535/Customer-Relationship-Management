<%@page import="in.co.crm.Ctl.ProductCategoryCtl"%>
<%@page import="in.co.crm.Utility.DataUtility" %>
<%@page import="in.co.crm.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ProductCategory</title>
</head>
<body>
<%@include file="Header.jsp"%>
<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
<form action="<%=CRMView.PRODUCT_CATEORY_CTL%>" method="post">

<jsp:useBean id="bean" scope="request"
						class="in.co.crm.Bean.ProductCategoryBean" />
<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">


<table class="table table-striped">
			<tbody align="center">
				<tr>
					<td colspan="1">
						<form class="well form-horizontal">
							<fieldset>
							<h2 class="text-center">Add ProductCategory</h2>
								<div class="col-md-2">
									<label for="form_message">Product Category Name:</label> <input type="text"
										name="ProductCategoryname" style="width: 115%"
										value="<%=DataUtility.getStringData(bean.getProductCategoryName())%>"
										placeholder="Enter ProductCategory Name">
</div>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("ProductCategoryname", request)%></div>
									<br>
<div class="col-md-2">
									<label for="form_message">Description:</label>
									<textarea name="description" class="form-Center"
										placeholder="Write your Description" cols="30" rows="3"
										value="<%=DataUtility.getStringData(bean.getDescription())%>"></textarea>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></div>
</div>

									<input type="submit" class="btn btn-primary" name="operation"
										value="<%=ProductCategoryCtl.OP_SAVE%>">
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