package in.co.crm.Bean;

public class ProductDetailsBean extends BaseBean{

	private String productCode;
	private String productName;
	private String Details;
	private String price;
	private String image;
	private long productCategoryid;
	private String productCategory;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getProductCategoryid() {
		return productCategoryid;
	}
	public void setProductCategoryid(long productCategoryid) {
		this.productCategoryid = productCategoryid;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	
	
}
