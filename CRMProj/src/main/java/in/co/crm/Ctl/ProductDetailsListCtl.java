package in.co.crm.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Bean.ProductDetailsBean;
import in.co.crm.Model.ProductCategoryModel;
import in.co.crm.Model.ProductDetailsModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.ServletUtility;


@WebServlet(name = "ProductDetailsListCtl" , urlPatterns = "/productdetailslist")
public class ProductDetailsListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";
    public ProductDetailsListCtl() {
        super();
    }

    protected BaseBean populateBean(HttpServletRequest request) {

  		ProductDetailsBean bean = new ProductDetailsBean();
  		bean.setId(DataUtility.getLong(request.getParameter("id")));
  		bean.setProductCode(DataUtility.getString(request.getParameter("productCode")));
  		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
  		bean.setDetails(DataUtility.getString(request.getParameter("details")));
  		bean.setPrice(DataUtility.getString(request.getParameter("price")));
  		bean.setProductCategoryid(DataUtility.getLong(request.getParameter("category")));
  		populateDTO(bean, request);
  		return bean;

  	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDetailsModel model = new ProductDetailsModel();
		ProductDetailsBean bean = null;
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

		  ProductDetailsModel model = new ProductDetailsModel();
		  ProductDetailsBean bean = new ProductDetailsBean();
		bean = (ProductDetailsBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(CRMView.PRODUCT_DETAILS_LIST_CTL, request, response);
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
		return CRMView.PRODUCT_DETAILS_LIST_VIEW;
	}

}
