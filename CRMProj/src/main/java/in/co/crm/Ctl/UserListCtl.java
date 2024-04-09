package in.co.crm.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.crm.Bean.BaseBean;
import in.co.crm.Bean.UserBean;
import in.co.crm.Model.UserModel;
import in.co.crm.Utility.DataUtility;
import in.co.crm.Utility.ServletUtility;

@WebServlet(name = "UserListCtl", urlPatterns = "/userlist")
public class UserListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";

	public UserListCtl() {
		super();
	}

	 protected BaseBean populateBean(HttpServletRequest request) {

			UserBean bean = new UserBean();

			bean.setId(DataUtility.getLong(request.getParameter("id")));
			bean.setName(DataUtility.getString(request.getParameter("name")));
			bean.setEmail(DataUtility.getString(request.getParameter("email")));
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
			bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));
			bean.setRoleid(DataUtility.getLong(request.getParameter("role")));
			if (bean.getRoleid() == 2) {
				bean.setRolename("User");
			} else {
				bean.setRolename("Employee");
			}
			populateDTO(bean, request);
			return bean;

		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		UserBean bean = null;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = (UserBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(CRMView.USER__LIST_CTL, request, response);
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

		return CRMView.USER_LIST_VIEW;
	}

}
