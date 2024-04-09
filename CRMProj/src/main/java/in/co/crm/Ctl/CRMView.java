package in.co.crm.Ctl;

public interface CRMView {

	public String  APP_CONTEXT = "/CRMProj";
	public String PAGE_FOLDER ="/jsp";
	
	
	//Controller----------------------
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String REGISTRATION_CTL = APP_CONTEXT + "/register";
	public String USER__LIST_CTL = APP_CONTEXT + "/userlist";
	public String USER_CTL = APP_CONTEXT + "/user";
	public String PRODUCT_CATEORY__LIST_CTL = APP_CONTEXT + "/productcategorylist";
	public String PRODUCT_CATEORY_CTL = APP_CONTEXT + "/productcategory";
	public String PRODUCT_DETAILS_LIST_CTL = APP_CONTEXT + "/productdetailslist";
	public String PRODUCT_DETAILS_CTL = APP_CONTEXT + "/productdetails";
	public String COMPLAINT_CTL = APP_CONTEXT + "/complaint";
	public String COMPLAINT_LIST_CTL = APP_CONTEXT + "/complaintlist";
	public String FORGET_CTL = APP_CONTEXT + "/forget";
	public String MYPROFILE_CTL = APP_CONTEXT + "/myprofile";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/changepassword";
	public String ProductInquiry_CTL = APP_CONTEXT + "/ProductInquiry";
	public String ProductInquiry_LIST = APP_CONTEXT + "/productinquirylist";
	
	
	//JSP----------------------
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
    public String LOGIN_VIEW = PAGE_FOLDER + "/Login.jsp"; 
    public String REGISTRATION_VIEW = PAGE_FOLDER + "/Registration.jsp";
    public String USER_LIST_VIEW = PAGE_FOLDER + "/UserList.jsp";
    public String USER_VIEW = PAGE_FOLDER + "/User.jsp";
    public String PRODUCT_CATEORY_LIST_VIEW = PAGE_FOLDER + "/ProductCategoryList.jsp";
    public String PRODUCT_CATEORY_VIEW = PAGE_FOLDER + "/ProductCategory.jsp";
    public String PRODUCT_DETAILS_LIST_VIEW = PAGE_FOLDER + "/ProductDetailsList.jsp";
    public String PRODUCT_DETAILS_VIEW = PAGE_FOLDER + "/ProductDetails.jsp";
    public String COMPLAINT_VIEW = PAGE_FOLDER + "/Complaint.jsp";
    public String COMPLAINT_LIST_VIEW = PAGE_FOLDER + "/ComplaintListView.jsp";
    public String FORGET_VIEW = PAGE_FOLDER + "/ForgetPassword.jsp";
    public String MYPROFILE_VIEW = PAGE_FOLDER + "/MyprofileView.jsp";
    public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
    public String ProductInquiry_VIEW = PAGE_FOLDER + "/ProductInquiryView.jsp";
    public String ProductInquiry_LIST_VIEW = PAGE_FOLDER + "/ProductInquiryListView.jsp";
    
}
