package in.co.crm.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import in.co.crm.Bean.ComplaintBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Utility.JDBCDataSource;
import in.com.crm.Exception.ApplicationException;

public class ProductCategoryModel {

	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM productcategory");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(ProductCategoryBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO productcategory VALUES(?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getProductCategoryName());
			ps.setString(3, bean.getDescription());
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
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from productcategory");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ProductCategoryBean bean = new ProductCategoryBean();
			bean.setId(rs.getLong(1));
			bean.setProductCategoryName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			list.add(bean);
		}
		return list;
	}

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from productcategory where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public List search(ProductCategoryBean bean) throws Exception {
		System.out.println("in search");
				StringBuffer sql = new StringBuffer("SELECT *from productcategory WHERE 1=1");
				if (bean != null) {
					if (bean.getId() > 0) {
						sql.append(" AND id = " + bean.getId());
					}
					if (bean.getProductCategoryName() != null && bean.getProductCategoryName().length() > 0) {
						sql.append(" AND productcategoryname like '" + bean.getProductCategoryName() + "%'");
					}
				}

				ArrayList list = new ArrayList();
				Connection conn = null;
				try {
					conn = JDBCDataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						bean = new ProductCategoryBean();
						bean.setId(rs.getLong(1));
						bean.setProductCategoryName(rs.getString(2));
						bean.setDescription(rs.getString(3));
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
