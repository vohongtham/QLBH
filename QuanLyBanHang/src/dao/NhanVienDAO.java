package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.QLBHUtil;
import model.NhanVien;

public class NhanVienDAO implements DAOInterface<NhanVien> {

	@Override
	public int insert(NhanVien nhanVien) {
		int ketqua = 0;
		try {
			// Create connection
			Connection conn = QLBHUtil.getConnection();

			// Define the SQL statement for insertion
			String sql = "INSERT INTO nhanvien (MaNV, TenNV, NgaySinh, ChucVu, DiaChi, SDT, MatKhau) VALUES (?, ?, ?, ?, ?, ?, ?)";

			// Create a prepared statement
			PreparedStatement pst = conn.prepareStatement(sql);

			// Set values for the prepared statement
			pst.setString(1, nhanVien.getMaNV());
			pst.setString(2, nhanVien.getTenNV());
			pst.setString(3, nhanVien.getNgaySinh());
			pst.setString(4, nhanVien.getChucVu());
			pst.setString(5, nhanVien.getDiaChi());
			pst.setString(6, nhanVien.getSDT());
			pst.setString(7, nhanVien.getMatKhau());

			// Execute the INSERT statement
			ketqua = pst.executeUpdate();

			// Close the connection
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int update(NhanVien t) {
		int ketqua = 0;
		try {
			// Tao ket noi
			Connection conn = QLBHUtil.getConnection();

			String sql = "UPDATE nhanvien SET TenNV = ?, NgaySinh = ?, ChucVu = ?, DiaChi = ?, SDT = ?, MatKhau = ? WHERE MaNV = ?";

			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1, t.getMaNV());
			pst.setString(2, t.getTenNV());
			pst.setString(3, t.getNgaySinh());
			pst.setString(4, t.getDiaChi());
			pst.setString(5, t.getChucVu());
			pst.setString(6, t.getSDT());
			pst.setString(7, t.getMatKhau());

			ketqua = pst.executeUpdate(sql);

			// System.out.println("Ban da thuc thi: "+ sql);

			// Ngat ket noi
			QLBHUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(NhanVien t) {
		int ketqua = 0;
		try {
			// Tao ket noi
			Connection conn = QLBHUtil.getConnection();

			String sql = "delete from khachhang where MaNV = ?";

			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1, t.getMaNV());
			pst.setString(2, t.getTenNV());
			pst.setString(3, t.getNgaySinh());
			pst.setString(4, t.getDiaChi());
			pst.setString(5, t.getChucVu());
			pst.setString(6, t.getSDT());
			pst.setString(7, t.getMatKhau());

			ketqua = pst.executeUpdate(sql);

			// System.out.println("Ban da thuc thi: "+ sql);

			// Ngat ket noi
			QLBHUtil.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<NhanVien> getMa() {
		ArrayList<NhanVien> nhanVienList = new ArrayList<>();
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT * FROM nhanvien";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNV(rs.getString("MaNV"));
				nhanVien.setTenNV(rs.getString("TenNV"));
				// set other properties...

				nhanVienList.add(nhanVien);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanVienList;
	}

	@Override
	public ArrayList<NhanVien> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NhanVien selectById(NhanVien t) {
		NhanVien nhanVien = null;
		try {
			// Create connection
			Connection conn = QLBHUtil.getConnection();

			// Define the SQL statement for selection
			String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";

			// Create a prepared statement
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, t.getMaNV());

			// Execute the SELECT statement
			ResultSet rs = pst.executeQuery();

			// Process the result set
			if (rs.next()) {
				// Create a new NhanVien object and populate its attributes
				nhanVien = new NhanVien();
				nhanVien.setMaNV(rs.getString("MaNV"));
				nhanVien.setTenNV(rs.getString("TenNV"));
				nhanVien.setNgaySinh(rs.getString("NgaySinh"));
				nhanVien.setChucVu(rs.getString("ChucVu"));
				nhanVien.setDiaChi(rs.getString("DiaChi"));
				nhanVien.setSDT(rs.getString("SDT"));
				nhanVien.setMatKhau(rs.getString("MatKhau"));
			}

			// Close the connection
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanVien;
	}

	@Override
	public ArrayList<NhanVien> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NhanVien searchBy(String t) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ArrayList<NhanVien> selectByCondition(String condition) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
