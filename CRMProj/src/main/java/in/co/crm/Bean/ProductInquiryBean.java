package in.co.crm.Bean;

public class ProductInquiryBean extends BaseBean{

	private String enqNo;
	private String product;
	private Long productname;
	public Long getProductname() {
		return productname;
	}
	public void setProductname(Long productname) {
		this.productname = productname;
	}
	private String deatils;
	private String userName;
	private long username;
	private String answer;
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public long getUsername() {
		return username;
	}
	public void setUsername(long username) {
		this.username = username;
	}
	public String getEnqNo() {
		return enqNo;
	}
	public void setEnqNo(String enqNo) {
		this.enqNo = enqNo;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDeatils() {
		return deatils;
	}
	public void setDeatils(String deatils) {
		this.deatils = deatils;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
