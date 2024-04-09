package in.co.crm.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Bean.ProductDetailsBean;
import in.co.crm.Bean.ProductInquiryBean;
import in.co.crm.Utility.JDBCDataSource;
import in.com.crm.Exception.ApplicationException;

public class ProductDetailsModel {

	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM productdetails");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(ProductDetailsBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO productdetails VALUES(?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getProductCode());
			ps.setString(3, bean.getProductName());
			ps.setString(4, bean.getDetails());
			ps.setString(5, bean.getPrice());
			// ps.setLong(6, bean.getProductCategoryid());
			ps.setString(6, bean.getProductCategory());
			ps.setString(7, bean.getImage());
			System.out.println("ookkkkk");
			ps.executeUpdate();
			System.out.println("nooooookkkk");
			conn.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + e.getMessage());
			}
		} finally {
			JDBCDataSource.closeconnection(conn);
		}
		return pk;
	}

	public List list() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		try {
			Connection conn = null;
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT productdetails.id, productdetails.productCode,productName,Details,price,productcategory.productcategoryname,image"
					+ " FROM productdetails INNER JOIN productcategory ON productdetails.productCategoryid=productcategory.id\r\n"
							+ "");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDetailsBean bean = new ProductDetailsBean();
				bean.setId(rs.getLong(1));
				bean.setProductCode(rs.getString(2));
				bean.setProductName(rs.getString(3));
				bean.setDetails(rs.getString(4));
				bean.setPrice(rs.getString(5));
				//bean.setProductCategoryid(rs.getLong(6));
				 bean.setProductCategory(rs.getString(6));
				bean.setImage(rs.getString(7));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	public ProductDetailsBean findByPk(long pk) throws Exception {

		ProductDetailsBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM productdetails WHERE id=?");
			
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new ProductDetailsBean();
				bean.setId(rs.getLong(1));
				bean.setProductCode(rs.getString(2));
				bean.setProductName(rs.getString(3));
				bean.setDetails(rs.getString(4));
				bean.setPrice(rs.getString(5));
				//bean.setProductCategoryid(rs.getLong(6));
				 bean.setProductCategory(rs.getString(6));
				bean.setImage(rs.getString(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	
	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from productdetails where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public List search(ProductDetailsBean bean) throws Exception {
		System.out.println("in search");
		StringBuffer sql = new StringBuffer("SELECT productdetails.id, productdetails.productCode,productName,Details,price,productcategory.productcategoryname,image\"\r\n" + 
				"					+ \" FROM productdetails INNER JOIN productcategory ON productdetails.id=productcategory.id WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getProductName() != null && bean.getProductName().length() > 0) {
				sql.append(" AND productName like '" + bean.getProductName() + "%'");
			}
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductDetailsBean();
				bean.setId(rs.getLong(1));
				bean.setProductCode(rs.getString(2));
				bean.setProductName(rs.getString(3));
				bean.setDetails(rs.getString(4));
				bean.setPrice(rs.getString(5));
				bean.setProductCategory(rs.getString(6));
				bean.setImage(rs.getString(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeconnection(conn);
		}

		return list;

	}
}
