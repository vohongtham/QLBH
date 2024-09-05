package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QLBHUtil {
	public static Connection getConnection() {
		Connection c = null;
		try {
			// Đăng ký MySQL Driver với DriverManager
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/qlbhang";
			String username = "root";
			String password = "";
			// Tạo kết nối
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) { // xử lý ngoại lệ nếu có
			ex.printStackTrace();
		}
		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printInfo(Connection c) {
		try {
			if (c != null) {
				DatabaseMetaData m = c.getMetaData();
				System.out.println(m.getDatabaseProductName());
				System.out.println(m.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
