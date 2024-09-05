//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import database.QLBHUtil;
//
//public class DangNhapDAO {
//	public boolean checkLogin(String _user, String _pass) {
//		if (_pass.equals(""))
//			return false;
//		DangNhapDAO dbConn = new DangNhapDAO();
//		ResultSet rs;
//		PreparedStatement ps;
//		boolean valid = false;
//		try {
//			ps = dbConn.getConnection().prepareStatement("Select * from DangNhap where TenDangNhap=? and MatKhau=?");
//			ps.setString(1, _user); // DangNhap.TenDangNhap = _user
//			ps.setString(2, _pass); // DangNhap.TenDangNhap = _pass
//
//			rs = ps.executeQuery(); // executes the prepared statement
//			if (rs.next()) { // if meet the where clause
//				valid = true;
//			}
//			rs.close();
//			ps.close();
//			// dbConn.closeConnection();
//			return valid;
//		} catch (SQLException sqlException) {
//			System.out.println(sqlException.getMessage());
//			return valid;
//		}
//	}
//
//	private Connection getConnection() {
//		return QLBHUtil.getConnection();
//	}
//}
