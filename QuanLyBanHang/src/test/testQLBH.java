package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.QLBHUtil;

public class testQLBH {
	public static void main(String[] args) {
		Connection conn = QLBHUtil.getConnection();
			
		//QLBHUtil.printInfo(conn);
	
		//QLBHUtil.closeConnection(conn);
		System.out.println("Noi ket thanh cong");
			
		String sql = "SELECT * FROM nhanvien";
		Statement stmt = null;
		ResultSet rs = null;
		try {	
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("Truy van du lieu thanh cong");
			while (rs.next() ) {
				System.out.println("MaNV: " + rs.getString("manv"));
				System.out.println("TenNV: " + rs.getString("tennv"));
//				System.out.println("DVTinh: " + rs.getString("dvtinh"));
//				System.out.println("SoLuong: " + rs.getString("soluong"));
//				System.out.println("GiaBan: " + rs.getString("giaban"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ket noi khong thanh cong");
			e.printStackTrace();
		}
		
		
	}
}
