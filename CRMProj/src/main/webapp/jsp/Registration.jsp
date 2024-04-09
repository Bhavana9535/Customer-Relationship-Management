<%@page import="in.co.crm.Ctl.RegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.crm.Utility.HTMLUtility" %>
<%@page import="in.co.crm.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
<%@include file="Header.jsp"%>
<h6 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h6>
<form action="<%=CRMView.REGISTRATION_CTL%>" method="post">

<jsp:useBean id="bean" scope="request"
						class="in.co.crm.Bean.UserBean" />
<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">


<section class="vh-100" style="background-color: #eee;">
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px;">
          <div class="card-body p-md-5">
            <div class="row justify-content-center">
              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                <form class="mx-1 mx-md-4">

                  <div class="col-12">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <label class="form-label" for="form3Example1c">Your Name</label>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="form3Example1c" class="form-control" name="name"/>
                    
                    </div>
                  </div>
<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></div>

                  <div class="col-12">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <label class="form-label" for="form3Example3c">Your Email</label>
                    <div class="form-outline flex-fill mb-0">
                      <input type="email" id="form3Example3c" class="form-control" name="email"/>
                      
                    </div>
                  </div>
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></div>

                  <div class="col-12">
                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                    <label class="form-label" for="form3Example4c">Password</label>
                    <div class="form-outline flex-fill mb-0">
                      <input type="password" id="form3Example4c" class="form-control" name="password"/>
                      
                    </div>
                  </div>
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></div>

 <div class="col-12">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                       <label class="form-label" for="form3Example4cd">Phone-No</label>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="form3Example4cd" class="form-control" name="phoneNo"/>
                   
                    </div>
                  </div>
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></div>


					<div class="col-12">
					<i class="fas fa-key fa-lg me-3 fa-fw"></i>
						<label class="form-label">Role</label>
						<select name="role"
							class="form-control">
							<option>---Select---</option>
							<option value="2">User</option>
							<option value="3">Employee</option>
						</select>
</div>
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("role", request)%></div>


                  <div class="form-check d-flex justify-content-center mb-5">
                  </div>
                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                    <input type="submit" class="btn btn-primary btn-lg" name="operation" value="<%=RegistrationCtl.OP_SIGN_UP%>">
                  </div>

                </form>

              </div>
              <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                <img src="/CRMProj/img/7053342.jpg"
                  class="img-fluid" alt="Sample image">

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

</form>
	<%@include file="Footer.jsp"%>
</body>
</html>