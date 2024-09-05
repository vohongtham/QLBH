package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.HangHoaDAO;
import model.HangHoa;

public class HangHoaForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTable table;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangHoaForm frame = new HangHoaForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initializeTableModel() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new Object[] { "Mã Hàng Hóa", "Tên Hàng Hóa", "Đơn Vị Tính", "Giá Bán" });
		table.setModel(model);
	}

	private void updateTableData() {
		HangHoaDAO hangHoaDAO = new HangHoaDAO();
		ArrayList<HangHoa> hangHoaList = hangHoaDAO.selectAll();

		if (hangHoaList != null) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(new Object[] { "Mã Hàng Hóa", "Tên Hàng Hóa", "Đơn Vị Tính", "Giá Bán" });

			// Clear the existing data in the table
			model.setRowCount(0);

			for (HangHoa hangHoa : hangHoaList) {
				model.addRow(new Object[] { hangHoa.getMaHH(), hangHoa.getTenHH(), hangHoa.getDVTinh(),
						hangHoa.getGiaBan() });
			}

			table.setModel(model);

			// Add ListSelectionListener to the table
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						int selectedRow = table.getSelectedRow();
						if (selectedRow != -1) {
							// Set the selected row data to text fields
							textField.setText(table.getValueAt(selectedRow, 0).toString());
							textField_1.setText(table.getValueAt(selectedRow, 1).toString());
							textField_2.setText(table.getValueAt(selectedRow, 2).toString());
							// textField_3.setText(table.getValueAt(selectedRow, 3).toString());
							textField_4.setText(table.getValueAt(selectedRow, 3).toString());
						}
					}
				}
			});
		} else {
			System.out.println("Error: hangHoaList is null");
		}
	}

	private void displayHangHoaData() {
		// Create an instance of HangHoaDAO
		HangHoaDAO hangHoaDAO = new HangHoaDAO();
		// Get the data from the database
		ArrayList<HangHoa> hangHoaList = hangHoaDAO.getAll();

		if (hangHoaList != null) {
			// Create a DefaultTableModel to manage the data for the JTable
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			// Clear the existing data in the table
			model.setRowCount(0);
			// Populate the table with the fetched data

			for (HangHoa hangHoa : hangHoaList) {
				Object[] rowData = { hangHoa.getMaHH(), hangHoa.getTenHH(), hangHoa.getDVTinh(), hangHoa.getGiaBan() };
				model.addRow(rowData);
			}
		} else {
			System.out.println("Error: hangHoaList is null");
		}
	}

//	private void displayHangHoa(HangHoa hangHoa) {
//		textField.setText(hangHoa.getMaHH()); // Assuming MaHH is the product ID
//		textField_1.setText(hangHoa.getTenHH());
//		textField_2.setText(hangHoa.getDVTinh());
//		textField_3.setText(hangHoa.getSoLuong());
//		textField_4.setText(String.valueOf(hangHoa.getGiaBan()));
//	}
	private void displayHangHoa(List<HangHoa> hangHoaList) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Clear the existing data

		for (HangHoa hangHoa : hangHoaList) {
			model.addRow(
					new Object[] { hangHoa.getMaHH(), hangHoa.getTenHH(), hangHoa.getDVTinh(), hangHoa.getGiaBan() });
		}
	}

	public HangHoaForm() {
		setTitle("formQLHangHoa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("  Quản Lý Hàng Hóa");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(179, 10, 261, 31);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin h\u00E0ng h\u00F3a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 51, 425, 217);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("M\u00E3 H\u00E0ng H\u00F3a");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 25, 130, 26);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("T\u00EAn H\u00E0ng H\u00F3a");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 75, 130, 28);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u0110\u01A1n V\u1ECB T\u00EDnh");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 127, 130, 18);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Gi\u00E1 B\u00E1n");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(10, 170, 110, 26);
		panel.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(150, 25, 269, 26);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(150, 75, 269, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(150, 126, 269, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(150, 174, 269, 24);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Danh s\u00E1ch h\u00E0ng h\u00F3a", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 338, 616, 175);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 596, 145);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MaHH", "T\u00EAnHH", "\u0110V T\u00EDnh", "Gi\u00E1 B\u00E1n" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		initializeTableModel();
		updateTableData();
		displayHangHoaData();

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(437, 51, 189, 217);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HangHoa hangHoa = new HangHoa();
				hangHoa.setMaHH(textField.getText());
				hangHoa.setTenHH(textField_1.getText());
				hangHoa.setDVTinh(textField_2.getText());
				// hangHoa.setSoLuong(textField_3.getText());
				hangHoa.setGiaBan(Double.parseDouble(textField_4.getText()));
				HangHoaDAO hangHoaDAO = new HangHoaDAO();
				int result = hangHoaDAO.insert(hangHoa);

				// Kiểm tra kết quả từ phương thức update
				if (result > 0) {
					// Nếu thành công, cập nhật dữ liệu trực tiếp lên table
					updateTableData();
					JOptionPane.showMessageDialog(null, "Thêm hàng hóa thành công");
				} else {
					// Xử lý khi có lỗi (nếu cần)
					System.out.println("Error inserting data into database.");
					// Hiển thị thông báo lỗi
					JOptionPane.showMessageDialog(null, "Lỗi khi thêm hàng hóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(39, 25, 102, 32);
		panel_2.add(btnNewButton);
		btnNewButton.setForeground(new Color(0, 64, 128));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 17));

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HangHoa hangHoa = new HangHoa();
				hangHoa.setMaHH(textField.getText());
				hangHoa.setTenHH(textField_1.getText());
				hangHoa.setDVTinh(textField_2.getText());
				// hangHoa.setSoLuong(textField_3.getText());
				hangHoa.setGiaBan(Double.parseDouble(textField_4.getText()));
				HangHoaDAO hangHoaDAO = new HangHoaDAO();
				int result = hangHoaDAO.update(hangHoa);

				// Kiểm tra kết quả từ phương thức update
				if (result > 0) {
					// Nếu thành công, cập nhật dữ liệu trực tiếp lên table
					updateTableData();
					JOptionPane.showMessageDialog(null, "Cập nhật hàng hóa thành công");
				} else {
					// Xử lý khi có lỗi (nếu cần)
					System.out.println("Error updating data into database.");
					// Hiển thị thông báo lỗi
					JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật hàng hóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnNewButton_1.setBounds(39, 78, 102, 32);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(0, 64, 128));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 17));

		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hiển thị hộp thoại xác nhận
				int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa hàng hóa này không?",
						"Xác nhận xóa", JOptionPane.YES_NO_OPTION);

				// Kiểm tra xem người dùng đã chọn "Yes" hay không
				if (dialogResult == JOptionPane.YES_OPTION) {
					// Người dùng chọn "Yes", thực hiện xóa
					String maHH = textField.getText();
					String tenHH = textField_1.getText();
					String dvt = textField_2.getText();
					// String soLuong = textField_3.getText();

					// Check for null or empty values
					if (maHH != null && !maHH.isEmpty() && tenHH != null && !tenHH.isEmpty() && dvt != null
							&& !dvt.isEmpty()) {

						HangHoa hangHoa = new HangHoa();
						hangHoa.setMaHH(maHH);
						hangHoa.setTenHH(tenHH);
						hangHoa.setDVTinh(dvt);
						// hangHoa.setSoLuong(soLuong);

						try {
							// Parse the double value, handle NumberFormatException
							hangHoa.setGiaBan(Double.parseDouble(textField_4.getText()));
						} catch (NumberFormatException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Giá bán không hợp lệ", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
							return; // Exit the method if parsing fails
						}

						HangHoaDAO hangHoaDAO = new HangHoaDAO();
						int result = hangHoaDAO.delete(hangHoa);

						// Kiểm tra kết quả từ phương thức delete
						if (result > 0) {
							// Nếu thành công, cập nhật dữ liệu trực tiếp lên table
							updateTableData();
							JOptionPane.showMessageDialog(null, "Xóa hàng hóa thành công");
						} else {
							// Xử lý khi có lỗi (nếu cần)
							System.out.println("Error deleting data into database.");
							// Hiển thị thông báo lỗi
							JOptionPane.showMessageDialog(null, "Lỗi khi xóa hàng hóa", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Thông tin hàng hóa không hợp lệ", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnNewButton_2.setBounds(39, 120, 102, 34);
		panel_2.add(btnNewButton_2);
		btnNewButton_2.setForeground(new Color(0, 64, 128));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 17));

		JButton btnGetAllHangHoa = new JButton("GetALLHangHoa");
		btnGetAllHangHoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create an instance of HangHoaDAO
				HangHoaDAO hangHoaDAO = new HangHoaDAO();

				// Get all HangHoa data from the database
				List<HangHoa> allHangHoaList = hangHoaDAO.selectAll();

				// Check if the list is not null or empty
				if (allHangHoaList != null && !allHangHoaList.isEmpty()) {
					// Display all HangHoa data in the table
					displayHangHoa(allHangHoaList);
					JOptionPane.showMessageDialog(null, "Successfully retrieved all HangHoa data");
				} else {
					JOptionPane.showMessageDialog(null, "No HangHoa data available", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGetAllHangHoa.setForeground(new Color(0, 64, 128));
		btnGetAllHangHoa.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnGetAllHangHoa.setBounds(10, 170, 169, 37);
		panel_2.add(btnGetAllHangHoa);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm theo m\u00E3 h\u00E0ng h\u00F3a",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 278, 616, 55);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		textField_5 = new JTextField();
		textField_5.setBounds(10, 17, 452, 24);
		panel_3.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.setBounds(484, 17, 122, 24);
		panel_3.add(btnNewButton_3);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton_3.setForeground(new Color(0, 64, 128));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy mã hàng hóa từ trường nhập liệu
				String keyword = textField_5.getText();

				// Gọi hàm tìm kiếm trong database
				HangHoaDAO hangHoaDAO = new HangHoaDAO();
				List<HangHoa> hangHoaList = null;
				try {
					// Check if the keyword is empty
					if (keyword.isEmpty()) {
						// If empty, fetch all products
						hangHoaList = hangHoaDAO.getAll();
					} else {
						// Use a generic search method without specifying a column name
						hangHoaList = hangHoaDAO.searchByKeyword(keyword);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					// Handle the exception, show an error message, or log the details
					JOptionPane.showMessageDialog(null, "Error occurred during search: " + e1.getMessage(), "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}

				// Kiểm tra kết quả tìm kiếm
				if (hangHoaList != null && !hangHoaList.isEmpty()) {
					// Nếu tìm thấy, hiển thị thông tin hàng hóa đầu tiên
					displayHangHoa(hangHoaList);
				} else {
					// Nếu không tìm thấy hoặc có lỗi, hiển thị thông báo
					JOptionPane.showMessageDialog(null, "Không tìm thấy hàng hóa với từ khóa: " + keyword, "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

	}
}
