package in.co.crm.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.ComplaintBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Model.ComplaintModel;
import in.co.crm.Model.ProductCategoryModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.ServletUtility;

@WebServlet(name = "ProductCategoryList",urlPatterns = "/productcategorylist")
public class ProductCategoryList extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";
   
    public ProductCategoryList() {
        super();
    }

    protected BaseBean populateBean(HttpServletRequest request) {

  		ProductCategoryBean bean = new ProductCategoryBean();
  		bean.setId(DataUtility.getLong(request.getParameter("id")));
  		bean.setProductCategoryName(DataUtility.getString(request.getParameter("productcategoryname")));
  		bean.setDescription(DataUtility.getString(request.getParameter("description")));
  		populateDTO(bean, request);
  		return bean;

  	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductCategoryModel model = new ProductCategoryModel();
		ProductCategoryBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			model.delete(id);
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}

		List list = null;
		try {
			System.out.println("in do get");
			list = model.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null && list.size() == 0) {
			ServletUtility.setErrorMessage("No record found", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		  ProductCategoryModel model = new ProductCategoryModel();
		  ProductCategoryBean bean = new ProductCategoryBean();
		bean = (ProductCategoryBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(CRMView.PRODUCT_CATEORY__LIST_CTL, request, response);
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
		return CRMView.PRODUCT_CATEORY_LIST_VIEW;
	}

}
