package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.mysql.cj.jdbc.CallableStatement;

import database.QLBHUtil;
import model.KhachHang;

public class KhachHangDAO implements DAOInterface<KhachHang> {

	public static KhachHangDAO getInstance() {
		return new KhachHangDAO();
	}

	@Override
	public int insert(KhachHang t) {
		int ketqua = 0;
		try {
			// Tao ket noi
			Connection conn = QLBHUtil.getConnection();

			// Goi stored procedure ThemKH
			String storedProcedureCall = "{CALL ThemKH(?, ?, ?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(storedProcedureCall);
			cstmt.setString(1, t.getMaKH());
			cstmt.setString(2, t.getTenKH());
			cstmt.setString(3, t.getNgaySinh());
			cstmt.setString(4, t.getDiaChi());
			cstmt.setString(5, t.getSDT());

			ketqua = cstmt.executeUpdate();

			System.out.println("Ban da thuc thi stored procedure ThemKH");
			// Ngat ket noi
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int update(KhachHang t) {
		int ketqua = 0;
		try {
			// Tao ket noi
			Connection conn = QLBHUtil.getConnection();

			// Goi stored procedure CapNhatKhachHang
			String storedProcedureCall = "{CALL CapNhatKhachHang(?, ?, ?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(storedProcedureCall);
			cstmt.setString(1, t.getMaKH());
			cstmt.setString(2, t.getTenKH());
			cstmt.setString(3, t.getNgaySinh());
			cstmt.setString(4, t.getDiaChi());
			cstmt.setString(5, t.getSDT());

			ketqua = cstmt.executeUpdate();

			System.out.println("Ban da thuc thi stored procedure CapNhatKhachHang");
			// Ngat ket noi
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<KhachHang> getAll() {
		ArrayList<KhachHang> khachHangList = new ArrayList<>();

		try {
			Connection conn = QLBHUtil.getConnection();

			String sql = "SELECT * FROM khachhang";

			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String maKh = rs.getString("MaKh");
				String tenKH = rs.getString("TenKH");
				String ngaySinh = rs.getString("NgaySinh");
				String diaChi = rs.getString("DiaChi");
				String sDT = rs.getString("SDT");

				KhachHang khachHang = new KhachHang(maKh, tenKH, ngaySinh, diaChi, sDT);
				khachHangList.add(khachHang);
			}

			// Close resources
			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception appropriately, log it, or throw a custom exception
		}

		return khachHangList;
	}

	@Override
	public int delete(KhachHang t) {
		int ketqua = 0;
		try {
			// Tao ket noi
			Connection conn = QLBHUtil.getConnection();

			// Goi stored procedure XoaKH
			String storedProcedureCall = "{CALL XoaKH(?)}";
			CallableStatement cstmt = conn.prepareCall(storedProcedureCall);
			cstmt.setString(1, t.getMaKH());

			ketqua = cstmt.executeUpdate();

			System.out.println("Ban da thuc thi stored procedure XoaKH");
			// Ngat ket noi
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> khachHangList = new ArrayList<KhachHang>();
		try {
			Connection conn = QLBHUtil.getConnection();

			String sql = "SELECT * FROM khachhang";

			PreparedStatement pst = conn.prepareStatement(sql);

			System.out.println(sql);
			ResultSet rs = pst.executeQuery(sql);

			while (rs.next()) {
				String MaKh = rs.getString("MaKh");
				String TenKH = rs.getString("TenKH");
				String NgaySinh = rs.getString("NgaySinh");
				String DiaChi = rs.getString("DiaChi");
				String SDT = rs.getString("SDT");

				KhachHang khachHang = new KhachHang(MaKh, TenKH, NgaySinh, DiaChi, SDT);
				khachHangList.add(khachHang);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception appropriately, log it, or throw a custom exception
		}

		return khachHangList;
	}

	@Override
	public KhachHang selectById(KhachHang t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KhachHang searchBy(String maKH) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		KhachHang khachHang = null;

		try {
			// Kết nối đến cơ sở dữ liệu (Bạn cần điền thông tin kết nối của bạn ở đây)
			connection = QLBHUtil.getConnection();
			// Chuẩn bị truy vấn SQL
			String sql = "SELECT * FROM khachhang WHERE MaKH = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, maKH);

			// Thực hiện truy vấn
			resultSet = statement.executeQuery();

			// Kiểm tra kết quả trả về
			if (resultSet.next()) {
				// Nếu tìm thấy, tạo đối tượng KhachHang từ dữ liệu
				khachHang = new KhachHang();
				khachHang.setMaKH(resultSet.getString("MaKH"));
				khachHang.setTenKH(resultSet.getString("TenKH"));
				khachHang.setNgaySinh(resultSet.getString("NgaySinh"));
				khachHang.setDiaChi(resultSet.getString("DiaChi"));
				khachHang.setSDT(resultSet.getString("SDT"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception appropriately, log it, or throw a custom exception
		} finally {
			// Đóng tất cả các resource (ResultSet, PreparedStatement, Connection)
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return khachHang;
	}

	@Override
	public ArrayList<KhachHang> getMa() {
		ArrayList<KhachHang> khachHangList = new ArrayList<>();
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT * FROM khachhang";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKH(rs.getString("MaKH"));
				khachHang.setTenKH(rs.getString("TenKH"));
				// set other properties...

				khachHangList.add(khachHang);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return khachHangList;
	}

}
