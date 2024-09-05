package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.QLBHUtil;
import model.ChiTietHD;

public class ChiTietHDDAO implements DAOInterface<ChiTietHD> {

//	public static String generateInvoiceID() {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//		String timestamp = dateFormat.format(new Date());
//
//		// Tạo một số ngẫu nhiên trong khoảng từ 100 đến 999
//		int randomNum = new Random().nextInt(900) + 100;
//
//		// Kết hợp timestamp và số ngẫu nhiên để tạo mã hóa đơn
//		String invoiceID = timestamp + randomNum;
//
//		return invoiceID;
//	}
//	public ArrayList<ChiTietHD> getChiTietHDByMaHD1(String maHD) {
//		ArrayList<ChiTietHD> chiTietHDList = new ArrayList<>();
//
//		try (Connection connection = QLBHUtil.getConnection();
//				PreparedStatement preparedStatement = connection
//						.prepareStatement("SELECT * FROM chitiethd WHERE MaHD = ?");) {
//			preparedStatement.setString(1, maHD);
//
//			try (ResultSet resultSet = preparedStatement.executeQuery()) {
//				while (resultSet.next()) {
//					ChiTietHD chiTietHD = new ChiTietHD();
//					chiTietHD.setMaHD(resultSet.getString("MaHD"));
//					chiTietHD.setMaHH(resultSet.getString("MaHH"));
//					chiTietHD.setDonGia(resultSet.getFloat("DonGia"));
//					chiTietHD.setSoLuong(resultSet.getInt("SoLuong"));
//					chiTietHDList.add(chiTietHD);
//				}
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//
//		return chiTietHDList;
//	}

	public int insert(ChiTietHD chiTietHD) {
		int result = 0;
		try {
			Connection conn = QLBHUtil.getConnection();
			// String maHD = generateInvoiceID();
			// chiTietHD.setMaHD(maHD);
			String sql = "INSERT INTO chitiethd(MaHD, MaHH, DonGia, SoLuong, TongTienHH) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, chiTietHD.getMaHD());
			pst.setString(2, chiTietHD.getMaHH());
			pst.setDouble(3, chiTietHD.getDonGia());
			pst.setInt(4, chiTietHD.getSoLuong());
			pst.setDouble(5, (chiTietHD.getSoLuong() * chiTietHD.getDonGia()));

			result = pst.executeUpdate();

			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(ChiTietHD chiTietHD) {
		int result = 0;
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "UPDATE chitiethd SET DonGia=?, SoLuong=? WHERE MaHD=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, chiTietHD.getDonGia());
			pst.setInt(2, chiTietHD.getSoLuong());
			pst.setString(3, chiTietHD.getMaHD());

			result = pst.executeUpdate();

			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(String maHD, String maHH) {
		int result = 0;
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "DELETE FROM chitiethd WHERE MaHD=? AND MaHH=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, maHD);
			pst.setString(2, maHH);

			// Log the SQL query and parameter values
			System.out.println("Executing SQL query: " + pst.toString());
			System.out.println("maHD: " + maHD);
			System.out.println("maHH: " + maHH);

			result = pst.executeUpdate();

			// Update the total cost in the hoadon table

			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//	private void updateTotalCost(Connection conn, String maHD, int soLuong) throws SQLException {
//		// You need to implement the logic to update the total cost in the hoadon table
//		// based on the quantity (soLuong) and the product code (maHH).
//
//		// Example logic (you need to adapt this based on your actual database schema):
//		String updateHoaDonSQL = "UPDATE hoadon SET TongTien = TongTien - (SELECT DonGia * ? FROM chitiethd WHERE MaHD = ?) WHERE MaHD = ?";
//		try (PreparedStatement updatePst = conn.prepareStatement(updateHoaDonSQL)) {
//			updatePst.setInt(1, soLuong);
//			updatePst.setString(2, maHD);
//			updatePst.setString(3, maHD);
//
//			int rowsUpdated = updatePst.executeUpdate();
//
//			if (rowsUpdated <= 0) {
//				System.err.println("Failed to update total cost in hoadon for MaHD: " + maHD);
//			}
//		}
//	}

	@Override
	public ArrayList<ChiTietHD> getAll() {
		ArrayList<ChiTietHD> chiTietHDList = new ArrayList<>();
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT * FROM chitiethd";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ChiTietHD chiTietHD = new ChiTietHD();
				chiTietHD.setMaHD(rs.getString("MaHD"));
				chiTietHD.setDonGia(rs.getFloat("DonGia"));
				chiTietHD.setSoLuong(rs.getInt("SoLuong"));

				chiTietHDList.add(chiTietHD);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chiTietHDList;
	}

//	@Override
//	public ChiTietHD searchBy(String maHD) {
//		ChiTietHD chiTietHD = null;
//		try {
//			Connection conn = QLBHUtil.getConnection();
//			String sql = "SELECT * FROM chitiethd WHERE MaHD=?";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1, maHD);
//
//			ResultSet rs = pst.executeQuery();
//
//			if (rs.next()) {
//				chiTietHD = new ChiTietHD();
//				chiTietHD.setMaHD(rs.getString("MaHD"));
//				chiTietHD.setDonGia(rs.getFloat("DonGia"));
//				chiTietHD.setSoLuong(rs.getInt("SoLuong"));
//			}
//
//			rs.close();
//			pst.close();
//			QLBHUtil.closeConnection(conn);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return chiTietHD;
//	}

	@Override
	public ArrayList<ChiTietHD> selectAll() {
		ArrayList<ChiTietHD> chiTietHDList = new ArrayList<>();
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT * FROM chitiethd";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ChiTietHD chiTietHD = new ChiTietHD();
				chiTietHD.setMaHD(rs.getString("MaHD"));
				chiTietHD.setDonGia(rs.getFloat("DonGia"));
				chiTietHD.setSoLuong(rs.getInt("SoLuong"));

				chiTietHDList.add(chiTietHD);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chiTietHDList;
	}

	@Override
	public ArrayList<ChiTietHD> getMa() {
		ArrayList<ChiTietHD> maList = new ArrayList<>();
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT DISTINCT MaHD FROM chitiethd";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ChiTietHD chiTietHD = new ChiTietHD();
				chiTietHD.setMaHD(rs.getString("MaHD"));
				maList.add(chiTietHD);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maList;
	}

//	@Override
//	public ChiTietHD selectById(ChiTietHD t) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public ChiTietHD searchBy(String maHD) {
		ChiTietHD chiTietHD = null;
		try (Connection conn = QLBHUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM chitiethd WHERE MaHD=?")) {

			pst.setString(1, maHD);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					chiTietHD = mapResultSetToChiTietHD(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
		}
		return chiTietHD;
	}

	// ... (other methods)

	private ChiTietHD mapResultSetToChiTietHD(ResultSet rs) throws SQLException {
		ChiTietHD chiTietHD = new ChiTietHD();
		chiTietHD.setMaHD(rs.getString("MaHD"));
		chiTietHD.setDonGia(rs.getFloat("DonGia"));
		chiTietHD.setSoLuong(rs.getInt("SoLuong"));
		return chiTietHD;
	}

//	@Override
//	public ArrayList<ChiTietHD> selectByCondition(String condition) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public ArrayList<ChiTietHD> getChiTietHDByMaHD(String maHD) {
		ArrayList<ChiTietHD> chiTietHDList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Get a database connection
			connection = QLBHUtil.getConnection();
			// SQL query to retrieve ChiTietHD based on MaHD
			String sql = "SELECT * FROM chitiethd WHERE MaHD = ?";

			// Prepare the statement
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maHD);
			// Execute the query
			resultSet = preparedStatement.executeQuery();
			// Process the result set
			while (resultSet.next()) {
				// Create ChiTietHD object and set its attributes
				ChiTietHD chiTietHD = new ChiTietHD();
				chiTietHD.setMaHD(resultSet.getString("MaHD"));
				chiTietHD.setMaHH(resultSet.getString("MaHH"));
				chiTietHD.setDonGia(resultSet.getFloat("DonGia"));
				chiTietHD.setSoLuong(resultSet.getInt("SoLuong"));
				chiTietHD.setTongTienHH(resultSet.getFloat("TongTienHH"));
				// Add ChiTietHD to the list
				chiTietHDList.add(chiTietHD);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			// Close resources in reverse order of their creation to avoid leaks
			// QLBHUtil.closeResultSet(resultSet);
			// QLBHUtil.closePreparedStatement(preparedStatement);
			QLBHUtil.closeConnection(connection);
		}
		return chiTietHDList;
	}

	public float getThanhTien(ArrayList<ChiTietHD> chiTietHDList) {
		float totalCost = 0;
		for (ChiTietHD chiTietHD : chiTietHDList) {
			// Calculate the cost for each item and add it to the total
			totalCost += chiTietHD.getDonGia() * chiTietHD.getSoLuong();
		}
		return totalCost;
	}

	@Override
	public int delete(ChiTietHD t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ChiTietHD selectById(ChiTietHD t) {
		// TODO Auto-generated method stub
		return null;
	}
}
