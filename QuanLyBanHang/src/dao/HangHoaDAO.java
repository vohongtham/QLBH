package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.QLBHUtil;
import model.HangHoa;

public class HangHoaDAO implements DAOInterface<HangHoa> {

	public static HangHoaDAO getInstance() {
		return new HangHoaDAO();
	}

	@Override
	public int insert(HangHoa t) {
		int ketqua = 0;
		try {
			// Establish a connection to the database
			Connection conn = QLBHUtil.getConnection();

			// Call the stored procedure ThemHH (Assuming such a stored procedure exists)
			String storedProcedureCall = "{CALL ThemHH(?, ?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(storedProcedureCall);
			cstmt.setString(1, t.getMaHH());
			cstmt.setString(2, t.getTenHH());
			cstmt.setString(3, t.getDVTinh());
			// cstmt.setString(4, t.getSoLuong());
			cstmt.setDouble(4, t.getGiaBan());

			// Execute the stored procedure and get the result
			ketqua = cstmt.executeUpdate();

			System.out.println("Bạn đã thực thi stored procedure ThemHH");

			// Close the connection to avoid memory leaks
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public ArrayList<HangHoa> getMa() {
		ArrayList<HangHoa> hangHoaList = new ArrayList<>();
		try {
			Connection conn = QLBHUtil.getConnection();
			String sql = "SELECT * FROM hanghoa";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				HangHoa hangHoa = new HangHoa();
				hangHoa.setMaHH(rs.getString("MaHH"));
				hangHoa.setTenHH(rs.getString("TenHH"));
				hangHoa.setGiaBan(rs.getDouble("GiaBan"));
				// set other properties...

				hangHoaList.add(hangHoa);
			}

			rs.close();
			pst.close();
			QLBHUtil.closeConnection(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hangHoaList;
	}

	@Override
	public int update(HangHoa t) {
		int ketqua = 0;
		try {
			// Tao ket noi
			Connection conn = QLBHUtil.getConnection();

			// Call the stored procedure
			String sql = "{CALL CapNhatHangHoa(?, ?, ?, ?)}";
			CallableStatement cst = conn.prepareCall(sql);
			cst.setString(1, t.getMaHH());
			cst.setString(2, t.getTenHH());
			cst.setString(3, t.getDVTinh());
			// cst.setString(4, t.getSoLuong());
			cst.setDouble(4, t.getGiaBan());

			ketqua = cst.executeUpdate();

			System.out.println("Ban da thuc thi stored procedure CapNhatHangHoa");
			// Ngat ket noi
			QLBHUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	@Override
	public int delete(HangHoa hangHoa) {
		int result = 0;
		try {
			// Get a connection
			Connection conn = QLBHUtil.getConnection();

			// Prepare the SQL statement
			String sql = "DELETE FROM hanghoa WHERE MaHH = ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			// Set the parameter in the SQL statement
			pst.setString(1, hangHoa.getMaHH());

			// Execute the update
			result = pst.executeUpdate();

			// Close resources
			pst.close();
			QLBHUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<HangHoa> selectAll() {
		ArrayList<HangHoa> ketqua = new ArrayList<HangHoa>();
		try {
			// Tao ket noi
			Connection conn = QLBHUtil.getConnection();

			String sql = "SELECT * FROM hanghoa";

			PreparedStatement pst = conn.prepareStatement(sql);

			System.out.println(sql);
			ResultSet rs = pst.executeQuery(sql);

			while (rs.next()) {
				String MaHH = rs.getString("MaHH");
				String TenHH = rs.getString("TenHH");
				String DVTinh = rs.getString("DVTinh");
				// String SoLuong = rs.getString("SoLuong");
				String GiaBan = rs.getString("GiaBan");

				HangHoa hangHoa = new HangHoa(MaHH, TenHH, DVTinh, GiaBan);
				ketqua.add(hangHoa);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketqua;
	}

//	@Override
//	public ArrayList<HangHoa> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public List<HangHoa> searchByKeyword(String keyword) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<HangHoa> resultList = new ArrayList<>();

		try {
			connection = QLBHUtil.getConnection();

			// Modify the SQL query to use LIKE for partial matches on multiple columns
			String sql = "SELECT * FROM hanghoa WHERE MaHH LIKE ? OR TenHH LIKE ? OR GiaBan LIKE ?";
			statement = connection.prepareStatement(sql);

			// Set parameter for each column
			for (int i = 1; i <= 3; i++) {
				statement.setString(i, "%" + keyword + "%");
			}

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				HangHoa hangHoa = new HangHoa();
				hangHoa.setMaHH(resultSet.getString("MaHH"));
				hangHoa.setTenHH(resultSet.getString("TenHH"));
				hangHoa.setDVTinh(resultSet.getString("DVTinh"));
				// hangHoa.setSoLuong(resultSet.getString("SoLuong"));
				hangHoa.setGiaBan(resultSet.getDouble("GiaBan"));
				resultList.add(hangHoa);
			}
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			QLBHUtil.closeConnection(connection);
		}

		return resultList;
	}

	@Override
	public HangHoa selectById(HangHoa t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HangHoa> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HangHoa searchBy(String t) {
		// TODO Auto-generated method stub
		return null;
	}

}
