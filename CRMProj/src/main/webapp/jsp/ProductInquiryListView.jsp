<%@page import="in.co.crm.Bean.ProductInquiryBean"%>
<%@page import="in.co.crm.Ctl.ProductInquiryListCtl"%>
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
<title>Product Inquiry List</title>
</head>
<body>

	<%@include file="Header.jsp"%>
	
	<br>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<h2 align="center">Product Inquiry List</h2>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=CRMView.ProductInquiry_LIST%>" method="post">
		<br>

		<table class="table table-striped">
			<tr>

				<th scope="col">ID</th>
				<th scope="col">ENQ-NO</th>
				<th scope="col">Product</th>
				<th scope="col">Details</th>
				<th scope="col">Email</th>
				<th scope="col">Action</th>
				<th scope="col"></th>
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					ProductInquiryBean bean = (ProductInquiryBean) it.next();
			%>
			
			<tr>
<%-- <%if(user.getEmail().equalsIgnoreCase(bean.getUserName())){%> --%>
				<th scope="row" style="color: blue"><%=index++%></th>
				<td><%=bean.getEnqNo()%></td>
				<td><%=bean.getProduct()%></td>
				<td><%=bean.getDeatils()%></td>
				<td><%=bean.getUserName()%></td>
				
				
				
				<%if(user.getRoleid()==2){%>
				
 <%if(bean.getAnswer().equalsIgnoreCase("answer")) {%>
 <%}else{ %>
 
 <td><%=bean.getAnswer()%></td>
   <td>
  </td>
  <%} %>
  
 <%--  <%} %> --%>
  
 <%
					}else{
				%>
  <td><a class="btn btn-info"
					href="<%=CRMView.ProductInquiry_CTL%>?id=<%=bean.getId()%>"><i
						class='fas fa-edit' style='font-size: 15px;'></i></a></td>

				<td><a class="btn btn-danger"
					href="<%=CRMView.ProductInquiry_LIST%>?id=<%=bean.getId()%>"><i
						class='	far fa-trash-alt' style='font-size: 15px;'></i></a></td>
  
 <%
					}
				%>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>


	</form>
	<br>
	<hr>
	<%@include file="Footer.jsp"%>

</body>
</html>