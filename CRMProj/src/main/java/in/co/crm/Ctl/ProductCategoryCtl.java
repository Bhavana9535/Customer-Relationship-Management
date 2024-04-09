package in.co.crm.Ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Bean.UserBean;
import in.co.crm.Model.ProductCategoryModel;
import in.co.crm.Model.UserModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.DataValidator;
import in.co.crm.Utility.PropertyReader;
import in.co.crm.Utility.ServletUtility;
import in.com.crm.Exception.ApplicationException;
import in.com.crm.Exception.DuplicateRecordException;

@WebServlet(name = "ProductCategoryCtl",urlPatterns = "/productcategory")
public class ProductCategoryCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SAVE = "Save";   
   
    public ProductCategoryCtl() {
        super();
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("ProductCategoryname"))) {
			request.setAttribute("ProductCategoryname", PropertyReader.getvalue("error.require", "ProductCategoryName"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("ProductCategoryname"))) {
			request.setAttribute("ProductCategoryname", PropertyReader.getvalue("error.name", "ProductCategoryName"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getvalue("error.require", "Description"));
			pass = false;

		}
		return pass;
	}
  
    protected BaseBean populateBean(HttpServletRequest request) {

  		ProductCategoryBean bean = new ProductCategoryBean();
  		bean.setId(DataUtility.getLong(request.getParameter("id")));
  		bean.setProductCategoryName(DataUtility.getString(request.getParameter("ProductCategoryname")));
  		bean.setDescription(DataUtility.getString(request.getParameter("description")));
  		populateDTO(bean, request);
  		return bean;

  	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ProductCategoryModel model = new ProductCategoryModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		ProductCategoryBean bean = new ProductCategoryBean();
		bean = (ProductCategoryBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (ProductCategoryBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("ProductCategory Successfully Add", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setbean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);

			} catch (ApplicationException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);

		}
	}

	@Override
	protected String getView() {
		return CRMView.PRODUCT_CATEORY_VIEW;
	}

}
