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
<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
	<h2 align="center">User List</h2>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=CRMView.USER__LIST_CTL%>" method="post">

		<table width="100%">
			<tr>
				<td align="center"><label>Name :</label> <input type="text"
					name="name" placeholder="Enter Name"
					value="<%=ServletUtility.getParameter("name", request)%>">
					&emsp;&emsp; <input type="submit" name="operation"
					value="<%=UserListCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input
					type="submit" name="operation" value="<%=UserListCtl.OP_RESET%>"></td>
			</tr>
		</table>
		<br>

		<table class="table table-striped">
			<tr>

				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">Email</th>
				<th scope="col">Phone-No</th>
				<th scope="col">RoleName</th>
				<th scope="col">Action</th>
				<th scope="col"></th>
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					UserBean bean = (UserBean) it.next();
			%>
			<tr>

				<th scope="row" style="color: blue"><%=index++%></th>
				<td><%=bean.getName()%></td>
				<td><%=bean.getEmail()%></td>
				<td><%=bean.getPhoneNo()%></td>

				<%
					if (bean.getRolename().equalsIgnoreCase("ADMIN")) {
				%>
				<td>ADMIN</td>
				<td>-------</td>
				<td>-------</td>
				<%
					} else {
				%>
				<td><%=bean.getRolename()%></td>

				<td><a class="btn btn-info"
					href="<%=CRMView.USER_CTL%>?id=<%=bean.getId()%>"><i
						class='fas fa-edit' style='font-size: 15px;'></i></a></td>

				<td><a class="btn btn-danger"
					href="<%=CRMView.USER__LIST_CTL%>?id=<%=bean.getId()%>"><i
						class='	far fa-trash-alt' style='font-size: 15px;'></i></a></td>
				<%
					}
				%>

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