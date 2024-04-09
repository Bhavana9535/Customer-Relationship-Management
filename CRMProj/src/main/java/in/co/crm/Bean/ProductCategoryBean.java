package in.co.crm.Bean;

public class ProductCategoryBean extends BaseBean{

	private String ProductCategoryName;
	private String description;
	public String getProductCategoryName() {
		return ProductCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		ProductCategoryName = productCategoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
