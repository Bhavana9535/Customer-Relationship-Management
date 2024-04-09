package in.co.crm.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import in.co.crm.Bean.ComplaintBean;
import in.co.crm.Bean.ProductCategoryBean;
import in.co.crm.Bean.UserBean;
import in.co.crm.Utility.JDBCDataSource;
import in.com.crm.Exception.ApplicationException;

public class ComplaintModel {

	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM complaint");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(ComplaintBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO complaint VALUES(?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getComplaintSubject());
			ps.setString(3, bean.getDetails());
			ps.setString(4, bean.getAnswer());
			ps.setLong(5, bean.getUserid());
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
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from complaint");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ComplaintBean bean = new ComplaintBean();
			bean.setId(rs.getLong(1));
			bean.setComplaintSubject(rs.getString(2));
			bean.setDetails(rs.getString(3));
			bean.setAnswer(rs.getString(4));
			bean.setUser(rs.getString(4));
			list.add(bean);
		}
		return list;
	}
	
	
	public List Showlist(long userid) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(
"SELECT complaint.id, complaintsubject, complaint.details,answer,user.name FROM complaint INNER JOIN user ON complaint.userid=user.id where userid=?");
		ps.setLong(1, userid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ComplaintBean bean = new ComplaintBean();
			bean.setId(rs.getLong(1));
			bean.setComplaintSubject(rs.getString(2));
			bean.setDetails(rs.getString(3));
			bean.setAnswer(rs.getString(4));
			bean.setUser(rs.getString(5));
			list.add(bean);
		}
		return list;
	}
	

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from complaint where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public List search(ComplaintBean bean) throws Exception {
System.out.println("in search");
		StringBuffer sql = new StringBuffer("SELECT *from complaint WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getComplaintSubject() != null && bean.getComplaintSubject().length() > 0) {
				sql.append(" AND complaintsubject like '" + bean.getComplaintSubject() + "%'");
			}
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ComplaintBean();
				bean.setId(rs.getLong(1));
				bean.setComplaintSubject(rs.getString(2));
				bean.setDetails(rs.getString(3));
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
	
	public ComplaintBean findByPk(long pk) throws Exception {

		ComplaintBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM complaint WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new ComplaintBean();
				bean.setId(rs.getLong(1));
				bean.setComplaintSubject(rs.getString(2));
				bean.setDetails(rs.getString(3));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public long Update(ComplaintBean bean) {

		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"update complaint set complaintsubject=?, details=?,answer=? where id=?");
			ps.setString(1, bean.getComplaintSubject());
			ps.setString(2, bean.getDetails());
			ps.setString(3, bean.getAnswer());
			ps.setLong(4, bean.getId());
			 ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}
	
	public List Anslist() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from complaint");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ComplaintBean bean = new ComplaintBean();
			bean.setId(rs.getLong(1));
			bean.setComplaintSubject(rs.getString(2));
			bean.setDetails(rs.getString(3));
			bean.setAnswer(rs.getString(4));
			list.add(bean);
		}
		return list;
	}

	
}
