package in.co.crm.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import in.co.crm.Bean.UserBean;
import in.co.crm.Ctl.DatabaseException;
import in.co.crm.Utility.EmailMessage;
import in.co.crm.Utility.EmailUtility;
import in.co.crm.Utility.JDBCDataSource;
import in.com.crm.Exception.ApplicationException;
import in.com.crm.Exception.DuplicateRecordException;

public class UserModel {

	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM USER");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(UserBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getEmail());
			ps.setString(4, bean.getPassword());
			ps.setString(5, bean.getPhoneNo());
			ps.setString(6, bean.getRolename());
			ps.setLong(7, bean.getRoleid());
			System.out.println("222222222222222");
			ps.setString(8, bean.getCreatedby());
			ps.setString(9, bean.getModifiedby());
			ps.setTimestamp(10, bean.getCreatedatetime());
			ps.setTimestamp(11, bean.getModifieddatetime());
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

	public UserBean Authenticate(String Email, String Password) throws Exception {
		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE EMAIL =? AND PASSWORD =?");
			ps.setString(1, Email);
			ps.setString(2, Password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));

				bean.setEmail(rs.getString(3));
				bean.setPassword(rs.getString(4));
				bean.setPhoneNo(rs.getString(5));
				bean.setRolename(rs.getString(6));
				bean.setRoleid(rs.getLong(7));
				bean.setCreatedby(rs.getString(8));
				bean.setModifiedby(rs.getString(9));
				bean.setCreatedatetime(rs.getTimestamp(10));
				bean.setModifieddatetime(rs.getTimestamp(11));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	public List list() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from user");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserBean bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setEmail(rs.getString(3));
			bean.setPassword(rs.getString(4));
			bean.setPhoneNo(rs.getString(5));
			bean.setRolename(rs.getString(6));
			list.add(bean);
		}
		return list;
	}

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from USER where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public UserBean findByPk(long pk) throws in.com.crm.Exception.RecordNotFoundException,in.com.crm.Exception.ApplicationException {

		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setPassword(rs.getString(4));
				bean.setPhoneNo(rs.getString(5));
				bean.setRolename(rs.getString(6));
				bean.setRoleid(rs.getLong(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public UserBean findByLogin(String email) throws Exception {

		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setPassword(rs.getString(4));
				bean.setPhoneNo(rs.getString(5));
				bean.setRolename(rs.getString(6));
				bean.setRoleid(rs.getLong(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	public long Update(UserBean bean) throws DuplicateRecordException,in.com.crm.Exception.ApplicationException{

		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"update user set name=?, email=?,password=?,phoneNo=?,rolename=?, roleid=? where id=?");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getEmail());
			System.out.println("password"+bean.getPassword());
			ps.setString(3, bean.getPassword());
			ps.setString(4, bean.getPhoneNo());
			ps.setString(5, bean.getRolename());
			ps.setLong(6, bean.getRoleid());
			ps.setLong(7, bean.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	public List search(UserBean bean) throws Exception {

		StringBuffer sql = new StringBuffer("SELECT *from user WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND name like '" + bean.getName() + "%'");
			}
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setPassword(rs.getString(4));
				bean.setPhoneNo(rs.getString(5));
				bean.setRolename(rs.getString(6));
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

	public static UserBean FindUserPassword(String Email) {

		Connection con;
		UserBean user = null;
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement("Select password from user where email=?");
			stmt.setString(1, Email);
			ResultSet rs = stmt.executeQuery();
			System.out.println("modal111");
			if (rs.next()) {
				System.out.println("modal222");
				user = new UserBean();
				user.setPassword(rs.getString("password"));
				System.out.println("modal333");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}

	public boolean changePassword(Long id, String oldpassword, String newpassword)
			throws in.com.crm.Exception.RecordNotFoundException,in.com.crm.Exception.ApplicationException{
		System.out.println("in user model");
		boolean flag = false;
		UserBean beanExist = null;
		beanExist = findByPk(id);
		System.out.println("password"+beanExist.getPassword());
		System.out.println("new"+newpassword);
		System.out.println("old"+oldpassword);
		
			if (beanExist != null && beanExist.getPassword().equals(oldpassword)) {
				beanExist.setPassword(newpassword);
				try {
				Update(beanExist);
				}
				catch (DuplicateRecordException e) {
					throw new in.com.crm.Exception.ApplicationException("Login Already exist");
				}
				flag = true;
			} else {
				throw new in.com.crm.Exception.RecordNotFoundException("Old password is invalid");
			}
		return flag;

	}

}
