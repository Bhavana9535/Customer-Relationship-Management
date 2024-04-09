package in.co.crm.Ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.ComplaintBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Bean.UserBean;
import in.co.crm.Model.ComplaintModel;
import in.co.crm.Model.ProductCategoryModel;
import in.co.crm.Model.UserModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.DataValidator;
import in.co.crm.Utility.PropertyReader;
import in.co.crm.Utility.ServletUtility;
import in.com.crm.Exception.DuplicateRecordException;

@WebServlet(name = "ComplaintCtl",urlPatterns = "/complaint")
public class ComplaintCtl extends BaseCtl{
	private static final long serialVersionUID = 1L;
       public static final String OP_SAVE ="Save";
       
  
    public ComplaintCtl() {
        super();
    }
    @Override
  	protected boolean validate(HttpServletRequest request) {
  		System.out.println("in validation");
  		boolean pass = true;

  		if (DataValidator.isNull(request.getParameter("subject"))) {
  			request.setAttribute("subject", PropertyReader.getvalue("error.require", "Subject"));
  			pass = false;

  		}
  		if (DataValidator.isNull(request.getParameter("details"))) {
  			request.setAttribute("details", PropertyReader.getvalue("error.require", "Details"));
  			pass = false;

  		}
  		return pass;
  	}
    
      protected BaseBean populateBean(HttpServletRequest request) {

    		ComplaintBean bean = new ComplaintBean();
    		HttpSession session = request.getSession(false);
    		UserBean existBean = (UserBean)session.getAttribute("user");
    		Long userId = existBean.getId();
    		bean.setUserid(userId);
    		long id = DataUtility.getLong(request.getParameter("id"));
    		bean.setId(DataUtility.getLong(request.getParameter("id")));
    		bean.setComplaintSubject(DataUtility.getString(request.getParameter("subject")));
    		bean.setDetails(DataUtility.getString(request.getParameter("details")));
    		
    		if (id>0) {
				bean.setAnswer(DataUtility.getString(request.getParameter("answer")));
			}else {
				bean.setAnswer("answer");
			}
    		
    		populateDTO(bean, request);
    		return bean;

    	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComplaintModel model = new ComplaintModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			ComplaintBean bean = null;
			try {
				bean = model.findByPk(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setbean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComplaintModel model = new ComplaintModel();
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		ComplaintBean bean = new ComplaintBean();
		bean = (ComplaintBean) populateBean(request);
		if (bean.getId() > 0) {
			System.out.println("in do post2");
			long i = model.Update(bean);
			ServletUtility.setSuccessMessage("Complaint Updated Successfully", request);
		} else {
			try {
				long pk = model.add(bean);
				ServletUtility.setSuccessMessage("Complaint sent......", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return CRMView.COMPLAINT_VIEW;
	}

}
