<%@page import="in.co.crm.Ctl.LoginCtl"%>
<%@page import="in.co.crm.Utility.ServletUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="css/style.css"> 
<title>Login</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<h4 class="text-center" style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
		<h4 class="text-center" style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>
<form action="<%=CRMView.LOGIN_CTL%>" method="post">

<jsp:useBean id="bean" scope="request"
						class="in.co.crm.Bean.UserBean" />
<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">

	
	
	
    <div class="login-box">
    <img src="/CRMProj/img/crm6.png" class="avatar">
        <h1>Login Here</h1>
            <form>
            <p>Email</p>
            <input type="text" name="email" placeholder="Enter Email">
            <p>Password</p>
            <input type="password" name="password" placeholder="Enter Password">
            <input type="submit" name="operation" value="<%=LoginCtl.OP_SINGIN %>">
             
            </form>
        
        
        </div>
	
	</form>
	

	
	
	
		<div style="margin-top: 41%">
	<%@include file="Footer1.jsp"%>
	</div>
</body>
</html>