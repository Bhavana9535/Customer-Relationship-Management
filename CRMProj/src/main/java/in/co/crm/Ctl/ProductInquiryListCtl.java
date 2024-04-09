package in.co.crm.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.crm.Bean.ComplaintBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Bean.ProductInquiryBean;
import in.co.crm.Bean.UserBean;
import in.co.crm.Model.ComplaintModel;
import in.co.crm.Model.ProductCategoryModel;
import in.co.crm.Model.ProductInquiryModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.ServletUtility;

@WebServlet(name = "ProductInquiryListCtl" , urlPatterns = "/productinquirylist")
public class ProductInquiryListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";
       
   
    public ProductInquiryListCtl() {
        super();
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProductInquiryModel model = new ProductInquiryModel();
    	ProductInquiryBean bean = new ProductInquiryBean();
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
			  ServletUtility.setSuccessMessage("Data Deleted Successfully", request); }
			 
		}
        ServletUtility.forward(getView(), request, response);
	}

	/*
	 * protected void doGet1(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * ProductInquiryModel model = new ProductInquiryModel(); ProductInquiryBean
	 * bean = null; long id = DataUtility.getLong(request.getParameter("id"));
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
	}

	@Override
	protected String getView() {
		return CRMView.ProductInquiry_LIST_VIEW;
	}

}
