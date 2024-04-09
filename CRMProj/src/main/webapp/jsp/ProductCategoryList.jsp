<%@page import="in.co.crm.Ctl.ProductCategoryList"%>
<%@page import="in.co.crm.Ctl.ProductDetailsListCtl"%>
<%@page import="in.co.crm.Bean.ProductCategoryBean"%>
<%@page import="in.co.crm.Ctl.UserListCtl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.crm.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ProductCategory List</title>
</head>
<body>

 <div class="container-fluid p-0 m-0">
  <%@include file="Header.jsp" %>
  </div>
<br>
<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
<form action="<%=CRMView.PRODUCT_CATEORY__LIST_CTL%>" method="post">

	<div class="container">
		<h1 class="text-center">Show ProductCategory List</h1>
		<table width="100%">
			<tr>
				<td align="center"><label>Product Category Name:</label> <input
					type="text" name="productcategoryname" placeholder="Enter Category Name"
					value="<%=ServletUtility.getParameter("productcategoryname", request)%>">
					&emsp;&emsp; <input type="submit" name="operation"
					value="<%=ProductCategoryList.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input type="submit"
					name="operation" value="<%=ProductCategoryList.OP_RESET%>"></td>
			</tr>
		</table>
           <div class="row">
           <div class="col-12">
           <h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
		
		<%
			
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					ProductCategoryBean bean = (ProductCategoryBean) it.next();
			%>
			<div class="card mt-3">
  <img class="card-img-top m-4 mx-auto" src="img/notepad.png" style="max-width:100px;" alt="Card image cap">
  <div class="card-body px-5">
  
  
  ID :<h5 class="card-title text-center"><%=index++%></h5>
  ProductCategory Name :  <h5 class="card-title text-center"><%=bean.getProductCategoryName()%></h5>
  Description :  <h5 class="card-title text-center"><%=bean.getDescription()%></h5>
  
 
 <%if(user.getRoleid()==2){%>
 <% }else{%> 
    <div class="container text-center mt-2">
    <a href="<%=CRMView.PRODUCT_CATEORY__LIST_CTL%>?id=<%=bean.getId()%>" class="btn btn-danger">Delete</a>
 </div>
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