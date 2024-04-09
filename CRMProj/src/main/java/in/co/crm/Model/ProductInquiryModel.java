package in.co.crm.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import in.co.crm.Bean.ComplaintBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Bean.ProductInquiryBean;
import in.co.crm.Utility.JDBCDataSource;
import in.com.crm.Exception.ApplicationException;

public class ProductInquiryModel {

	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM productinquiry");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(ProductInquiryBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO productinquiry VALUES(?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getEnqNo());
			ps.setLong(3, bean.getProductname());
			ps.setString(4, bean.getDeatils());
			ps.setLong(5, bean.getUsername());
			ps.setString(6, bean.getAnswer());
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

	
	public List Showlist(long username) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		try {
			Connection conn = null;
			conn = JDBCDataSource.getConnection();
			String sql = "SELECT productinquiry.id, enqno,productName,productinquiry.details,email,answer FROM productinquiry INNER JOIN productdetails ON productinquiry.product=productdetails.id  inner join user on productinquiry.userName=user.id where userName=?";
			PreparedStatement ps = conn.prepareStatement(sql);
//"SELECT productinquiry.id, enqno,productName,productinquiry.details,email,answer FROM productinquiry INNER JOIN productdetails ON productinquiry.product=productdetails.id  inner join user on productinquiry.userName=user.id where userName=?");
		System.out.println(sql);
			ps.setLong(1, username);
			
			System.out.println("UserName:"+username);
			System.out.println("in Show  Model");
			//ProductInquiryBean bean1 = new ProductInquiryBean();
			//System.out.println("in bean1:"+bean1.getEnqNo());
			//System.out.println("in bean2:"+bean1.getProduct());
			//System.out.println("in bean3:"+bean1.getDeatils());
			//System.out.println("in bean4:"+bean1.getUserName());
			//System.out.println("in bean5:"+bean1.getAnswer());
			
			ResultSet rs = ps.executeQuery();
			System.out.println("SQL:"+rs);
			while (rs.next()) {
				System.out.println("in bean");
				ProductInquiryBean bean = new ProductInquiryBean();
				bean.setId(rs.getLong(1));
				System.out.println("in do get");
				bean.setEnqNo(rs.getString(2));
				bean.setProduct(rs.getString(3));
				bean.setDeatils(rs.getString(4));
				bean.setAnswer(rs.getString(5));
				bean.setUserName(rs.getString(6));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public List list() throws Exception {
	//	System.out.println("in model list");
		ArrayList list = new ArrayList();
		try {
			Connection conn = null;
			conn = JDBCDataSource.getConnection();
PreparedStatement pstmt = conn.prepareStatement("SELECT productinquiry.id, enqno,productName,productinquiry.details,email,answer \r\n" + 
		"FROM productinquiry INNER JOIN productdetails ON productinquiry.product=productdetails.id  inner join user on productinquiry.userName=user.id");
			

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductInquiryBean bean = new ProductInquiryBean();
				bean.setId(rs.getLong(1));
				bean.setEnqNo(rs.getString(2));
				bean.setProduct(rs.getString(3));
				bean.setDeatils(rs.getString(4));
				bean.setUserName(rs.getString(5));
				list.add(bean);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from productinquiry where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	
	public ProductInquiryBean findByPk(long pk) throws Exception {

		ProductInquiryBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM productinquiry WHERE id=?");
			
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new ProductInquiryBean();
				bean.setId(rs.getLong(1));
				bean.setEnqNo(rs.getString(2));
				bean.setProduct(rs.getString(3));
				bean.setDeatils(rs.getString(4));
				bean.setUserName(rs.getString(5));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public long Update(ProductInquiryBean bean) {

		System.out.println("in model update method");
		System.out.println("USerNAme:"+bean.getUsername());
		//System.out.println("product333:"+bean.getProductname());
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"update productinquiry set enqno=?, product=?,details=?,userName=?,answer=? where id=?");
			ps.setString(1, bean.getEnqNo());
			
			ps.setLong(2, bean.getProductname());
			ps.setString(3, bean.getDeatils());
			ps.setLong(4, bean.getUsername());
			ps.setString(5, bean.getAnswer());
			ps.setLong(6,bean.getId());
			 ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}
	
}
