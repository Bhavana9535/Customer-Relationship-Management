package in.co.crm.Ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.crm.Bean.UserBean;
import in.co.crm.Model.UserModel;
import in.co.crm.Utility.EmailMessage;
import in.co.crm.Utility.EmailUtility;
import in.co.crm.Utility.ServletUtility;
import in.com.crm.Exception.ApplicationException;

@WebServlet(name = "ForgetPasswordCtl" ,urlPatterns = "/forget")
public class ForgetPasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
   
    public ForgetPasswordCtl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(CRMView.FORGET_VIEW, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String email = request.getParameter("email").trim();
		    EmailMessage emailbean = new EmailMessage();
		    UserBean user = UserModel.FindUserPassword(email);
		    emailbean.setTo(email);
		    emailbean.setMessage("Hi Your"+"password is.....:-"+user.getPassword());
		    try {
		      
		      EmailUtility.sendMail(emailbean);
		      ServletUtility.setSuccessMessage("Mail has been sent successfully..", request);
		      
		    } catch (ApplicationException e) {
		      ServletUtility.setErrorMessage("Somting Wrong", request);
	}
	ServletUtility.forward(CRMView.FORGET_VIEW, request, response);
	}


	@Override
	protected String getView() {
		return CRMView.FORGET_VIEW;
	}

}
