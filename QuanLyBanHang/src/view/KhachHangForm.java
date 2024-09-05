package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import dao.KhachHangDAO;
import model.KhachHang;

public class KhachHangForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	KhachHang khachHang = new KhachHang();
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHangForm frame = new KhachHangForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initializeTableModel() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new Object[] { "Mã Khách Hàng", "Tên Khách Hàng", "Ngày Sinh", "Địa Chỉ", "SĐT" });
		table.setModel(model);
	}

	private void updateTableData() {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		ArrayList<KhachHang> khachHangList = khachHangDAO.selectAll();

		if (khachHangList != null) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(
					new Object[] { "Mã Khách Hàng", "Tên Khách Hàng", "Ngày Sinh", "Địa Chỉ", "SĐT" });

			// Clear the existing data in the table
			model.setRowCount(0);

			for (KhachHang khachHang : khachHangList) {
				model.addRow(new Object[] { khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getNgaySinh(),
						khachHang.getDiaChi(), khachHang.getSDT() });
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
							textField_3.setText(table.getValueAt(selectedRow, 3).toString());
							textField_4.setText(table.getValueAt(selectedRow, 4).toString());
						}
					}
				}
			});

		} else {
			System.out.println("Error: khachHangList is null");
		}
	}

	private void displayKhachHangData() {
		// Create an instance of KhachHangDAO
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		// Get the data from the database
		ArrayList<KhachHang> khachHangList = khachHangDAO.getAll();

		// Create a DefaultTableModel to manage the data for the JTable
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// Clear the existing data in the table
		model.setRowCount(0);
		// Populate the table with the fetched data

		for (KhachHang khachHang : khachHangList) {
			Object[] rowData = { khachHang.getMaKH(), khachHang.getTenKH(), khachHang.getNgaySinh(),
					khachHang.getDiaChi(), khachHang.getSDT() };
			model.addRow(rowData);
		}
	}

	// Helper method to display KhachHang data in text fields
	private void displayKhachHang(KhachHang khachHang) {
		textField.setText(khachHang.getMaKH()); // Assuming MaKh is the customer ID
		textField_1.setText(khachHang.getTenKH());
		textField_2.setText(khachHang.getNgaySinh());
		textField_3.setText(khachHang.getDiaChi());
		textField_4.setText(khachHang.getSDT());
	}

	/**
	 * Create the frame.
	 */
	public KhachHangForm() {
		setTitle("formQLKhachHang");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản Lý Khách Hàng");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(187, 10, 276, 31);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin h\u00E0ng h\u00F3a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 51, 478, 219);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 20, 144, 27);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 62, 144, 26);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ng\u00E0y Sinh");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 100, 130, 26);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u0110\u1ECBa Ch\u1EC9");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(10, 142, 110, 26);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("S\u0110T");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(10, 178, 110, 25);
		panel.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(164, 20, 285, 24);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(164, 61, 285, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(164, 100, 285, 24);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(164, 138, 285, 25);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(164, 177, 285, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Danh s\u00E1ch h\u00E0ng h\u00F3a", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 348, 616, 171);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 596, 140);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "MaKH", "T\u00EAn Kh\u00E1ch H\u00E0ng",
				"Ng\u00E0y Sinh", "\u0110\u1ECBa Ch\u1EC9", "S\u0110T" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		initializeTableModel();
		updateTableData();
		displayKhachHangData();

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(490, 51, 136, 219);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKH(textField.getText());
				khachHang.setTenKH(textField_1.getText());
				khachHang.setNgaySinh(textField_2.getText());
				khachHang.setDiaChi(textField_3.getText());
				khachHang.setSDT(textField_4.getText());
				KhachHangDAO khachHangD = new KhachHangDAO();
				int result = khachHangD.update(khachHang);

				// Kiểm tra kết quả từ phương thức insert
				if (result > 0) {
					// Nếu thành công, cập nhật dữ liệu trực tiếp lên table
					updateTableData();
					JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công");
				} else {
					// Xử lý khi có lỗi (nếu cần)
					System.out.println("Error updating data into database.");
					// Hiển thị thông báo lỗi
					JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật khách hàng", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_1.setBounds(21, 97, 93, 29);
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(0, 64, 128));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 17));

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy thông tin khách hàng từ các trường nhập liệu
				KhachHang khachHang = new KhachHang();
				khachHang.setMaKH(textField.getText());
				khachHang.setTenKH(textField_1.getText());
				khachHang.setNgaySinh(textField_2.getText());
				khachHang.setDiaChi(textField_3.getText());
				khachHang.setSDT(textField_4.getText());

				// Gọi hàm insert trong database
				KhachHangDAO khachHangD = new KhachHangDAO();
				int result = khachHangD.insert(khachHang);

				// Kiểm tra kết quả từ phương thức insert
				if (result > 0) {
					// Nếu thành công, cập nhật dữ liệu trực tiếp lên table
					updateTableData();
					JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
				} else {
					// Xử lý khi có lỗi (nếu cần)
					System.out.println("Error inserting data into database.");
					// Hiển thị thông báo lỗi
					JOptionPane.showMessageDialog(null, "Lỗi khi thêm khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnNewButton.setBounds(21, 23, 93, 29);
		panel_2.add(btnNewButton);
		btnNewButton.setForeground(new Color(0, 64, 128));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 17));

		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hiển thị hộp thoại xác nhận
				int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa khách hàng này không?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);

				// Kiểm tra lựa chọn của người dùng
				if (dialogResult == JOptionPane.YES_OPTION) {
					// Lấy thông tin khách hàng từ các trường nhập liệu
					KhachHang khachHang = new KhachHang();
					khachHang.setMaKH(textField.getText());
					khachHang.setTenKH(textField_1.getText());
					khachHang.setNgaySinh(textField_2.getText());
					khachHang.setDiaChi(textField_3.getText());
					khachHang.setSDT(textField_4.getText());

					// Gọi hàm xóa trong database
					KhachHangDAO khachHangD = new KhachHangDAO();
					int result = khachHangD.delete(khachHang);

					// Kiểm tra kết quả từ phương thức delete
					if (result > 0) {
						// Nếu thành công, cập nhật dữ liệu trực tiếp lên table
						updateTableData();
						JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
					} else {
						// Xử lý khi có lỗi (nếu cần)
						System.out.println("Error deleting data into database.");
						// Hiển thị thông báo lỗi
						JOptionPane.showMessageDialog(null, "Lỗi khi xóa khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnNewButton_2.setBounds(21, 163, 93, 29);
		panel_2.add(btnNewButton_2);
		btnNewButton_2.setForeground(new Color(0, 64, 128));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 17));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm theo m\u00E3 kh\u00E1ch h\u00E0ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 278, 616, 60);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		textField_5 = new JTextField();
		textField_5.setBounds(20, 20, 444, 22);
		panel_3.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.setBounds(474, 20, 118, 22);
		panel_3.add(btnNewButton_3);
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton_3.setForeground(new Color(0, 64, 128));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy mã khách hàng từ trường nhập liệu
				String maKH = textField_5.getText();

				// Kiểm tra xem mã khách hàng có được nhập hay không
				if (maKH.isEmpty()) {
					// Nếu không có mã khách hàng, hiển thị thông báo lỗi
					JOptionPane.showMessageDialog(null, "Vui lòng nhập Mã Khách Hàng để tìm kiếm.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Gọi hàm tìm kiếm trong database
					KhachHangDAO khachHangD = new KhachHangDAO();
					KhachHang khachHangFound = khachHangD.searchBy(maKH);

					// Kiểm tra kết quả tìm kiếm
					if (khachHangFound != null) {
						// Nếu tìm thấy, hiển thị thông tin khách hàng
						displayKhachHang(khachHangFound);
					} else {
						// Nếu không tìm thấy, hiển thị thông báo
						JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với Mã Khách Hàng " + maKH,
								"Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

//	

	}
}
