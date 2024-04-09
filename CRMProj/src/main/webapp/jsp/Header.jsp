<!doctype html>
<%@page import="in.co.crm.Ctl.LoginCtl"%>
<%@page import="in.co.crm.Ctl.MyProfileCtl"%>

<%@page import="in.co.crm.Bean.UserBean"%>
<%@page import="in.co.crm.Ctl.CRMView"%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>
<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">
<title>Hello, world!</title>
</head>
<body>
	<%
		UserBean userBean = (UserBean) session.getAttribute("user");
	%>
	<%
		boolean userLoggedIn = userBean != null;
		String welcomemsg = "Hello, ";
		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
			welcomemsg += userBean.getRolename();
		} else {
			welcomemsg += "Guest";
		}
	%>


	<!-- As a link -->
	<nav class="navbar navbar-light bg-dark">
		<a class="navbar-brand" href="<%=CRMView.WELCOME_CTL%>"
			style="color: white">CRMProj</a>

		<!-- As a heading -->
		<%
			if (userBean != null) {
		%>
		<%
			if (userBean.getRoleid() == 1) {
		%>
<nav class="navbar navbar-expand-sm bg-light">

		<div class="dropdown">
			<button type="button" class="btn btn-info dropdown-toggle"
				data-toggle="dropdown">User</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=CRMView.USER_CTL%>"> Add User</a>
				<a class="dropdown-item" href="<%=CRMView.USER__LIST_CTL%>">User
					List</a>
			</div>
		</div>
		<div class="dropdown">
			<button type="button" class="btn btn-info dropdown-toggle"
				data-toggle="dropdown">Product Category</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=CRMView.PRODUCT_CATEORY_CTL%>">Add
					Category</a> <a class="dropdown-item"
					href="<%=CRMView.PRODUCT_CATEORY__LIST_CTL%>">Category List</a>
			</div>
		</div>
		<div class="dropdown">
			<button type="button" class="btn btn-info dropdown-toggle"
				data-toggle="dropdown">Product Details</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=CRMView.PRODUCT_DETAILS_CTL%>">Product
					Add</a> <a class="dropdown-item"
					href="<%=CRMView.PRODUCT_DETAILS_LIST_CTL%>">Product List</a>
			</div>
		</div>
		
		<div class="dropdown">
			<button type="button" class="btn btn-info dropdown-toggle"
				data-toggle="dropdown">Complaint</button>
			<div class="dropdown-menu">
				 <a class="dropdown-item"
					href="<%=CRMView.COMPLAINT_LIST_CTL%>">Complaint List</a>
			</div>
		</div>
		
		<div class="dropdown">
			<button type="button" class="btn btn-info dropdown-toggle"
				data-toggle="dropdown">Product Inquiry</button>
			<div class="dropdown-menu">
			<a class="dropdown-item"
					href="<%=CRMView.ProductInquiry_LIST%>">Product Inquiry List</a>
			</div>
		</div>
</nav>
		<%
			} else if (userBean.getRoleid() == 2) {
		%>
		<nav class="navbar navbar-expand-sm bg-light">
 <ul class="navbar-nav">
    <li class="nav-item">
  
    



		<a class="navbar-brand" href="<%=CRMView.PRODUCT_CATEORY__LIST_CTL%>"
			style="color: dark">Product Category List</a> <a class="navbar-brand"
			href="<%=CRMView.PRODUCT_DETAILS_LIST_CTL%>" style="color: dark">Product Details List</a> <a
			class="navbar-brand" href="<%=CRMView.ProductInquiry_CTL%>"
			style="color: dark">Product Inquiry</a>
<a class="navbar-brand" href="<%=CRMView.COMPLAINT_CTL%>"
			style="color: dark">Raise
				Complaint</a>
		<a class="navbar-brand"
			href="<%=CRMView.COMPLAINT_LIST_CTL%>" style="color: dark">Complaint List</a> 
		
<a class="navbar-brand"
			href="<%=CRMView.ProductInquiry_LIST%>" style="color: dark">Product Inquiry List</a> 

  </li>
</ul>
</nav>
		<%
			} else {
		%>

<nav class="navbar navbar-expand-sm bg-light">

<div class="dropdown">
			<button type="button" class="btn btn-info dropdown-toggle"
				data-toggle="dropdown">Product Category</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=CRMView.PRODUCT_CATEORY_CTL%>">Add
					Category</a> <a class="dropdown-item"
					href="<%=CRMView.PRODUCT_CATEORY__LIST_CTL%>">Category List</a>
			</div>
		</div>
		<div class="dropdown">
			<button type="button" class="btn btn-info dropdown-toggle"
				data-toggle="dropdown">Product Details</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=CRMView.PRODUCT_DETAILS_CTL%>">Product
					Add</a> <a class="dropdown-item"
					href="<%=CRMView.PRODUCT_DETAILS_LIST_CTL%>">Product List</a>
			</div>
		</div>
			
			 <a class="navbar-brand"
			href="<%=CRMView.COMPLAINT_LIST_CTL%>" style="color: dark">Complaint List</a> 

 <a class="navbar-brand"
			href="<%=CRMView.ProductInquiry_LIST%>" style="color: dark">Product Inquiry List</a> 
</nav>

		<%
			}
		%>
		<%
			}
		%>

		<%
			if (userBean == null) {
		%>
<ul class="nav justify-content-end">
<div class="dropdown">
			<button type="button" class="btn btn-secondary dropdown-toggle"
				data-toggle="dropdown" style="margin-right: 10px;">Guest</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=CRMView.LOGIN_CTL%>">SingIn</a>
				<a class="dropdown-item" href="<%=CRMView.REGISTRATION_CTL%>">SingUp</a>
			</div>
		</div>
</ul>
		</nav>
		
		<%
			} else {
		%>
		<div class="dropdown">
			<button type="button" class="btn btn-secondary dropdown-toggle"
				data-toggle="dropdown"><%=welcomemsg%></button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="<%=CRMView.MYPROFILE_CTL%>?operation=<%=MyProfileCtl.OP_MYPROFILE%>">My Profile</a>
				<a class="dropdown-item" href="<%=CRMView.CHANGE_PASSWORD_CTL%>">Change Password</a>
				<a class="dropdown-item" href="<%=CRMView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOGOUT%>">Logout</a>
			</div>
		</div>
		
		<%
			}
		%>
	</nav>
</body>
</html>