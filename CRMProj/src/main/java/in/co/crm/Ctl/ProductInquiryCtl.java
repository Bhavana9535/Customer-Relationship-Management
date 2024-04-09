

package in.co.crm.Ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.ProductDetailsBean;
import in.co.crm.Bean.ProductInquiryBean;
import in.co.crm.Bean.UserBean;
import in.co.crm.Model.ProductDetailsModel;
import in.co.crm.Model.ProductInquiryModel;
import in.co.crm.Model.UserModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.DataValidator;
import in.co.crm.Utility.PropertyReader;
import in.co.crm.Utility.ServletUtility;


@WebServlet(name = "ProductInquiryCtl" , urlPatterns = "/ProductInquiry")
public class ProductInquiryCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SAVE = "Save";
  
    public ProductInquiryCtl() {
        super();
    }
    
    @Override
 	protected boolean validate(HttpServletRequest request) {
 		boolean pass = true;

 		if (DataValidator.isNull(request.getParameter("enqno"))) {
 			request.setAttribute("enqno", PropertyReader.getvalue("error.require", "Enq No"));
 			pass = false;
 		}
 		if (DataValidator.isNull(request.getParameter("productName"))) {
 			request.setAttribute("productName", PropertyReader.getvalue("error.require", "Product Name"));
 			pass = false;
 		}
 		if (DataValidator.isNull(request.getParameter("details"))) {
 			request.setAttribute("details", PropertyReader.getvalue("error.require", "Details"));
 			pass = false;
 		}
 		if (DataValidator.isNull(request.getParameter("username"))) {
 			request.setAttribute("username", PropertyReader.getvalue("error.require", "User Name"));
 			pass = false;
 		}
 		return pass;
 	}
    
    protected BaseBean populateBean(HttpServletRequest request) {
		long id = DataUtility.getLong(request.getParameter("id"));
    	ProductInquiryBean bean = new ProductInquiryBean();
    	HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		Long user = existBean.getId();
		bean.setUsername(user);
  		bean.setId(DataUtility.getLong(request.getParameter("id")));
  		bean.setEnqNo(DataUtility.getString(request.getParameter("enqno")));
  		bean.setDeatils(DataUtility.getString(request.getParameter("details")));
  		
  		if (id>0) {
  			bean.setProductname(DataUtility.getLong(request.getParameter("productName")));
		}else {
			bean.setProductname(DataUtility.getLong(request.getParameter("productName")));
			System.out.println("ProductName"+bean.getProductname());
		}
  		
  		if (id>0) {
  			bean.setUsername(DataUtility.getLong(request.getParameter("username")));
		}else {
			bean.setUserName(request.getParameter("username"));
			System.out.println("UserName"+bean.getUsername());
		}
  		
  		if (id>0) {
			bean.setAnswer(DataUtility.getString(request.getParameter("answer")));
		}else {
			bean.setAnswer("answer");
		}
  		
  		populateDTO(bean, request);
  		return bean;

  	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductInquiryModel model = new ProductInquiryModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			ProductInquiryBean bean = null;
			try {
				bean = model.findByPk(id);
				System.out.println("product111:"+bean.getProduct());
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setbean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductInquiryModel model = new ProductInquiryModel();
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		ProductInquiryBean bean = new ProductInquiryBean();
		bean = (ProductInquiryBean) populateBean(request);
		if (bean.getId() > 0) {
			System.out.println("bean:"+request.getParameter("username"));
			bean.setProduct(request.getParameter("productName"));
			ProductDetailsModel model1 = new ProductDetailsModel();
			UserModel model2 = new UserModel();
			try {
				ProductDetailsBean product = model1.findByPk(DataUtility.getLong(bean.getProduct()));
				UserBean userbean = model2.findByLogin(request.getParameter("username"));
				bean.setUsername(userbean.getId());
				long i = model.Update(bean);
				ServletUtility.setSuccessMessage("Inquiry Updated Successfully", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				
				System.out.println("Email:"+bean.getUsername());
				long pk = model.add(bean);
				ServletUtility.setSuccessMessage("Inquiry sent......", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);

		}

	@Override
	protected String getView() {
		return CRMView.ProductInquiry_VIEW;
	}

}
