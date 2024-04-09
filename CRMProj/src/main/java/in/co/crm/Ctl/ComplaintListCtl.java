package in.co.crm.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.ComplaintBean;
import in.co.crm.Bean.UserBean;
import in.co.crm.Model.ComplaintModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.ServletUtility;

@WebServlet(name = "ComplaintListCtl",urlPatterns = "/complaintlist")
public class ComplaintListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";
	
    
    public ComplaintListCtl() {
        super();
    }
    protected BaseBean populateBean(HttpServletRequest request) {

		ComplaintBean bean = new ComplaintBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setComplaintSubject(DataUtility.getString(request.getParameter("complaintsubject")));
		bean.setDetails(DataUtility.getString(request.getParameter("details")));
		populateDTO(bean, request);
		return bean;

	}
	
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	ComplaintModel model = new ComplaintModel();
    	ComplaintBean bean = new ComplaintBean();
        List list = null;
        HttpSession session = request.getSession(false);
    	UserBean bean2 = (UserBean) session.getAttribute("user");
    	long roleid = bean2.getRoleid();
    	if (roleid==2) {
    		   try {
    			     list =	model.Showlist(bean2.getId());
    			     ServletUtility.setList(list, request);
    			     
    			} catch (Exception e) {
    			}
		}else{
			 try {
			     list =	model.list();
			     ServletUtility.setList(list, request);
			     
			} catch (Exception e) {
			
		}
			 long id = DataUtility.getLong(request.getParameter("id"));
				
			  if (id > 0) { model.delete(id);
			  ServletUtility.setSuccessMessage("Complaint Deleted Successfully", request); }
			 
		}
        ServletUtility.forward(getView(), request, response);
	}

    
    
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { ComplaintModel model = new
	 * ComplaintModel(); ComplaintBean bean = null; long id =
	 * DataUtility.getLong(request.getParameter("id"));
	 * 
	 * if (id > 0) { model.delete(id);
	 * ServletUtility.setSuccessMessage("Data Deleted Successfully", request); }
	 * 
	 * List list = null; try { System.out.println("in do get"); list = model.list();
	 * } catch (Exception e) { e.printStackTrace(); } if (list == null &&
	 * list.size() == 0) { ServletUtility.setErrorMessage("No record found",
	 * request); } ServletUtility.setList(list, request);
	 * ServletUtility.forward(getView(), request, response); }
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		ComplaintModel model = new ComplaintModel();
		ComplaintBean bean = new ComplaintBean();
		bean = (ComplaintBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(CRMView.COMPLAINT_LIST_CTL, request, response);
			return;

		}
		if (OP_SEARCH.equalsIgnoreCase(op)) {
			try {
				list = model.search(bean);
				ServletUtility.setList(list, request);
				ServletUtility.setbean(bean, request);

			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}

	
	}


	@Override
	protected String getView() {
		return CRMView.COMPLAINT_LIST_VIEW;
	}

}
