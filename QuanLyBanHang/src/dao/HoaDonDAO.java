package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.QLBHUtil;
import model.HoaDon;

public class HoaDonDAO implements DAOInterface<HoaDon> {

	@Override
	public HoaDon searchBy(String maHD) {
		HoaDon hoaDon = null;
		try {
			// Get connection to the database
			Connection conn = QLBHUtil.getConnection();

			// Prepare the SQL statement for searching by ID
			String sql = "SELECT * FROM hoadon WHERE MaHD=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, maHD);

			// Execute the query
			ResultSet rs = pst.executeQuery();

			// Process the result set
			if (rs.next()) {
				hoaDon = new HoaDon();
				hoaDon.setMaHD(rs.getString("MaHD"));
				hoaDon.setMaKH(rs.getString("MaKH"));
				hoaDon.setMaNV(rs.getString("MaNV"));
				hoaDon.setNgayLap(rs.getDate("NgayLap"));
				hoaDon.setTongTien(rs.getFloat("TongTien"));
			}

			// Close resources
			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoaDon;
	}

	@Override
	public int insert(HoaDon hoaDon) {
		int ketqua = 0;
		try {
			// Get connection to the database
			Connection conn = QLBHUtil.getConnection();

			// Prepare the SQL statement for insertion
			String sql = "INSERT INTO hoadon(MaHD, MaKH, MaNV, NgayLap, TongTien) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, hoaDon.getMaHD());
			pst.setString(2, hoaDon.getMaKH());
			pst.setString(3, hoaDon.getMaNV());
			pst.setDate(4, (Date) hoaDon.getNgayLap());
			pst.setDouble(5, hoaDon.getTongTien());

			// Execute the query
			ketqua = pst.executeUpdate();

			// Close resources
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int update(HoaDon hoaDon) {
		int ketqua = 0;
		try {
			// Get connection to the database
			Connection conn = QLBHUtil.getConnection();

			// Prepare the SQL statement for update
			String sql = "UPDATE hoadon SET MaKH=?, MaNV=?, NgayLap=?, TongTien=? WHERE MaHD=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, hoaDon.getMaKH());
			pst.setString(2, hoaDon.getMaNV());
			pst.setDate(3, (Date) hoaDon.getNgayLap());
			pst.setDouble(4, hoaDon.getTongTien());
			pst.setString(5, hoaDon.getMaHD());

			// Execute the query
			ketqua = pst.executeUpdate();

			// Close resources
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int delete(HoaDon hoaDon) {
		int ketqua = 0;
		try {
			// Get connection to the database
			Connection conn = QLBHUtil.getConnection();

			// Prepare the SQL statement for deletion
			String sql = "DELETE FROM hoadon WHERE MaHD=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, hoaDon.getMaHD());

			// Execute the query
			ketqua = pst.executeUpdate();

			// Close resources
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<HoaDon> getMa() {
		ArrayList<HoaDon> maList = new ArrayList<>();
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT DISTINCT MaHD FROM chitiethd";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				HoaDon hoaDon = new HoaDon();
				hoaDon.setMaHD(rs.getString("MaHD"));
				maList.add(hoaDon);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maList;
	}

	@Override
	public ArrayList<HoaDon> getAll() {
		ArrayList<HoaDon> hoaDonList = new ArrayList<>();
		try {
			// Get connection to the database
			Connection conn = QLBHUtil.getConnection();

			// Prepare the SQL statement for retrieving all records
			String sql = "SELECT * FROM hoadon";
			PreparedStatement pst = conn.prepareStatement(sql);

			// Execute the query
			ResultSet rs = pst.executeQuery();

			// Process the result set
			while (rs.next()) {
				HoaDon hoaDon = new HoaDon();
				hoaDon.setMaHD(rs.getString("MaHD"));
				hoaDon.setMaKH(rs.getString("MaKH"));
				hoaDon.setMaNV(rs.getString("MaNV"));
				hoaDon.setNgayLap(rs.getDate("NgayLap"));
				hoaDon.setTongTien(rs.getFloat("TongTien"));

				hoaDonList.add(hoaDon);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoaDonList;
	}

	@Override
	public ArrayList<HoaDon> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HoaDon selectById(HoaDon t) {
		// TODO Auto-generated method stub
		return null;
	}

	public float getTotalAmountForInvoice(String maHD) {
		float totalAmount = 0;
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT TinhTongTienHoaDon(?) AS TotalAmount";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, maHD);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				totalAmount = rs.getFloat("TotalAmount");
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalAmount;
	}

	public int updateStatus(String maHD, String newStatus) {
		int ketqua = 0;
		try {
			// Get connection to the database
			Connection conn = QLBHUtil.getConnection();

			// Prepare the SQL statement for updating the status
			String sql = "UPDATE hoadon SET TrangThaiHoaDon=? WHERE MaHD=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, newStatus);
			pst.setString(2, maHD);

			// Execute the query
			ketqua = pst.executeUpdate();

			// Close resources
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

//	@Override
//	public ArrayList<HoaDon> selectByCondition(String condition) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
