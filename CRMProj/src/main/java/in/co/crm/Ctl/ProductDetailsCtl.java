package in.co.crm.Ctl;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.ProductDetailsBean;
import in.co.crm.Model.ProductDetailsModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.DataValidator;
import in.co.crm.Utility.PropertyReader;
import in.co.crm.Utility.ServletUtility;


@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "ProductDetailsCtl" ,urlPatterns = "/productdetails")
public class ProductDetailsCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SAVE = "Save";   
	public static final String OP_UPDATE = "Update";   
    public ProductDetailsCtl() {
        super();
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("productCode"))) {
			request.setAttribute("productCode", PropertyReader.getvalue("error.require", "product Code"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("productName"))) {
			request.setAttribute("productName", PropertyReader.getvalue("error.require", "Product Name"));
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("category"))) {
			request.setAttribute("category", PropertyReader.getvalue("error.require", "Category"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getvalue("error.require", "Price"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("details"))) {
			request.setAttribute("details", PropertyReader.getvalue("error.require", "Details"));
			pass = false;

		}
		
		return pass;
	}
    
    protected BaseBean populateBean(HttpServletRequest request) {

  		ProductDetailsBean bean = new ProductDetailsBean();
  		bean.setId(DataUtility.getLong(request.getParameter("id")));
  		bean.setProductCode(DataUtility.getString(request.getParameter("productCode")));
  		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
  		bean.setDetails(DataUtility.getString(request.getParameter("details")));
  		bean.setPrice(DataUtility.getString(request.getParameter("price")));
  		//bean.setProductCategoryid(DataUtility.getLong(request.getParameter("category")));
  	   bean.setProductCategory(DataUtility.getString(request.getParameter("category")));
  		populateDTO(bean, request);
  		return bean;

  	}
    
    protected String SubImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String savePath = DataUtility.getString(PropertyReader.getvalue("imagePath"));

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("image");
		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);
		String filePath = fileName;
		System.out.println("Path----" + savePath + File.separator + fileName);

		return fileName;
	}
	
	private String extractFileName(Part part) {
		try {
			String contentDisp = part.getHeader("content-disposition");
			String[] items = contentDisp.split(";");
			for (String s : items) {
				if (s.trim().startsWith("filename")) {
					return s.substring(s.indexOf("=") + 2, s.length() - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDetailsModel model = new ProductDetailsModel();
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		ProductDetailsBean bean = (ProductDetailsBean) populateBean(request);
		bean.setImage(SubImage(request, response));
		if (bean.getId() > 0) {
			System.out.println("in do post2");
		//	long i = model.Update(bean);
			ServletUtility.setSuccessMessage("Data Updated Successfully", request);
		} else {
			try {
				long pk = model.add(bean);
				ServletUtility.setSuccessMessage("Data Add Successfully", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return CRMView.PRODUCT_DETAILS_VIEW;
	}

}
