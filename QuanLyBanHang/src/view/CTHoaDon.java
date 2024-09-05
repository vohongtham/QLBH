package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHDDAO;
import dao.HangHoaDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import model.ChiTietHD;
import model.HangHoa;
import model.HoaDon;
import model.KhachHang;
import model.NhanVien;

public class CTHoaDon extends JFrame {

	JPanel contentPane;
	JTextField textField_4;
	JTextField textField_6;
	JTable table;

	JComboBox<Object> comboBox;
	JComboBox<Object> comboBox_1;
	JComboBox<Object> comboBox_2;
	MainApp mainApp = new MainApp();

	private JSpinner spinner = new JSpinner();
	String stringMaHangHoa;
	double thanhTien = 0;
	int soLuong = 1;
	double donGia = 0;
	String maHoaDon = "";
	private JTextField btnTongHoaDon;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CTHoaDon frame = new CTHoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void updateThanhTien() {
		Object selectedObject = comboBox_2.getSelectedItem();

		if (selectedObject != null) {
			System.out.println("Selected Object: " + selectedObject.toString());

			if (selectedObject instanceof HangHoa) {
				HangHoa selectedHangHoa = (HangHoa) selectedObject;
				soLuong = (int) spinner.getValue();
				donGia = selectedHangHoa.getGiaBan();
				thanhTien = donGia * soLuong;

				// Lưu trữ mã hàng hóa vào stringMaHangHoa
				stringMaHangHoa = selectedHangHoa.getMaHH();

				if (textField_6 != null) {
					textField_6.setText(String.format("%.2f", thanhTien));
				} else {
					System.err.println("Error: textField_6 is null.");
				}
			} else if (selectedObject instanceof String) {

				textField_6.setText(""); // or any default value you want
			} else {
				System.err.println("Error: Selected item is not an instance of HangHoa or String.");
			}
		} else {
			System.err.println("No item selected in comboBox_2.");

			if (textField_6 != null) {
				textField_6.setText("");
			} else {
				System.err.println("Error: textField_6 is null.");
			}
		}
	}

//	private void initializeHoaDonTableModel() {
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		model.setColumnIdentifiers(new Object[] { "Mã Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Mã Hàng Hóa",
//				"Ngày Lập", "Đơn giá", "Số Lượng", "Thành Tiền" });
//		table.setModel(model);
//	}

//	private void displayHoaDonData() {
//		// Create an instance of HoaDonDAO
//		HoaDonDAO hoaDonDAO = new HoaDonDAO();
//
//		// Get the data from the database
//		ArrayList<HoaDon> hoaDonList = hoaDonDAO.getAll();
//
//		// Create a DefaultTableModel to manage the data for the JTable
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//		// Clear the existing data in the table
//		model.setRowCount(0);
//
//		// Populate the table with the fetched data
//		for (HoaDon hoaDon : hoaDonList) {
//			Object[] rowData = { hoaDon.getMaHD(), hoaDon.getMaNV(), hoaDon.getMaKH(), hoaDon.getNgayLap(),
//					hoaDon.getTongTien() };
//			model.addRow(rowData);
//		}
//	}
//
//	private void displayHoaDon(HoaDon hoaDon) {
//		// textField.setText(hoaDon.getMaHD()); // Assuming MaHD is the order ID
//
//		// Set the selected item for comboBox_1 (MaNV)
//		for (int i = 0; i < comboBox_1.getItemCount(); i++) {
//			String item = comboBox_1.getItemAt(i).toString();
//			if (item.startsWith(hoaDon.getMaNV())) {
//				comboBox_1.setSelectedIndex(i);
//				break;
//			}
//		}
//
//		// Set the selected item for comboBox (MaKH)
//		for (int i = 0; i < comboBox.getItemCount(); i++) {
//			String item = comboBox.getItemAt(i).toString();
//			if (item.startsWith(hoaDon.getMaKH())) {
//				comboBox.setSelectedIndex(i);
//				break;
//			}
//		}
//		// textField_4.setText(hoaDon.getNgayLap());
//		textField_6.setText(String.valueOf(hoaDon.getTongTien()));
//	}

	/**
	 * Create the frame.
	 */

	public CTHoaDon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("H\u00F3a \u0110\u01A1n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(314, 10, 123, 25);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 45, 716, 202);
		contentPane.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox<>();
		comboBox.setBounds(148, 27, 235, 21);
		panel.add(comboBox);

		comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(148, 63, 235, 21);
		panel.add(comboBox_1);

		comboBox_2 = new JComboBox<>();
		comboBox_2.setBounds(148, 98, 235, 21);
		panel.add(comboBox_2);
		comboBox_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateThanhTien();
			}
		});
		populateComboBox();
		updateThanhTien();

		JLabel lblNewLabel_2 = new JLabel("H\u1ECD T\u00EAn KH");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 25, 99, 21);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("H\u1ECD T\u00EAn NV");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 59, 99, 24);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Hàng Hóa");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(10, 93, 125, 25);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Ng\u00E0y L\u1EADp");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(10, 128, 99, 21);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_7 = new JLabel("S\u1ED1 L\u01B0\u1EE3ng");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_7.setBounds(10, 159, 86, 24);
		panel.add(lblNewLabel_7);

		textField_4 = new JTextField();
		textField_4.setBounds(148, 131, 235, 18);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton btnXoaHH = new JButton("Xóa HH");
		btnXoaHH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();

				if (selectedRow != -1) {
					ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();
					DefaultTableModel model = (DefaultTableModel) table.getModel();

					String maHD = model.getValueAt(selectedRow, 0).toString();
					String maHH = model.getValueAt(selectedRow, 1).toString();
					// int slHH = model.getValueAt(selectedRow, 3).toString();

					int result = chiTietHDDAO.delete(maHD, maHH);

					if (result > 0) {
						JOptionPane.showMessageDialog(CTHoaDon.this, "ChiTietHD removed successfully.", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						updateTableModel(maHD);
						updateTotalCost();
					} else {
						JOptionPane.showMessageDialog(CTHoaDon.this, "Failed to remove ChiTietHD.\n" + "Error", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(CTHoaDon.this, "Please select a row to remove.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnXoaHH.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnXoaHH.setBounds(483, 108, 223, 31);
		panel.add(btnXoaHH);

		spinner.setBounds(148, 164, 84, 20);
		panel.add(spinner);
		spinner.setValue(1);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateThanhTien();
			}
		});

		JLabel lblNewLabel_8 = new JLabel("Đơn Giá HH");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_8.setBounds(278, 159, 115, 24);
		panel.add(lblNewLabel_8);

		textField_6 = new JTextField("0");
		textField_6.setBounds(403, 164, 135, 19);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(148, 63, 223, 21);
		panel.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(148, 98, 223, 21);
		panel.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(148, 98, 235, 21);
		panel.add(comboBox_2);

		JButton btnAddHangHoa = new JButton("Thêm HH");
		btnAddHangHoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Get the selected values from the UI components
				System.out.println("Ma hang hoa selected: " + stringMaHangHoa);
				System.out.println("Thanh tien: " + thanhTien);
				System.out.println("So luong: " + soLuong);

				ChiTietHD chiTietHD = new ChiTietHD();
				chiTietHD.setMaHD(maHoaDon);
				chiTietHD.setMaHH(stringMaHangHoa);
				chiTietHD.setSoLuong(soLuong);
				chiTietHD.setDonGia(donGia);

				ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();
				int result = chiTietHDDAO.insert(chiTietHD);

				if (result > 0) {
					JOptionPane.showMessageDialog(CTHoaDon.this, "ChiTietHD added successfully.", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.print(maHoaDon);
					updateTableModel(maHoaDon);
					updateTotalCost();

				} else {
					JOptionPane.showMessageDialog(CTHoaDon.this,
							"Failed to add ChiTietHD - hàng hóa đã được thêm vào rồi\n" + "Error", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddHangHoa.setEnabled(false);

		btnAddHangHoa.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnAddHangHoa.setBounds(483, 67, 223, 31);
		panel.add(btnAddHangHoa);

		JButton btnAddHD = new JButton("Thêm HĐ mới");
		btnAddHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Add HoaDon to the database
				String maHD = addHoaDonToDatabase();

				// Check if HoaDon was added successfully
				if (maHD != null) {
					// Do any additional actions needed after adding HoaDon
					System.out.println("HoaDon added successfully. MaHD: " + maHoaDon);
					JOptionPane.showMessageDialog(CTHoaDon.this, "HoaDon added successfully.", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					updateTableModel(maHoaDon);
					btnAddHD.setEnabled(false);
//					comboBox.setEnabled(false);
//					comboBox_1.setEnabled(false);
					btnAddHangHoa.setEnabled(true);

				} else {

					JOptionPane.showMessageDialog(CTHoaDon.this, "Failed to add MAHD.\n" + "Failed", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAddHD.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnAddHD.setBounds(483, 18, 223, 39);
		panel.add(btnAddHD);

		JButton btnDatHang = new JButton("Đặt Hàng");
		btnDatHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Assuming you have the invoiceId available
				String invoiceId = maHoaDon; // Assuming maHoaDon is the invoice ID

				HoaDonDAO hoaDonDAO = new HoaDonDAO();
				// Call the method to update the status to "Xác nhận"
				int result = hoaDonDAO.updateStatus(invoiceId, "Xác nhận");

				// Check the result of the update
				if (result > 0) {
					// Update was successful
					JOptionPane.showMessageDialog(null, "Đã xác nhận đơn hàng");
					dispose();
					if (mainApp != null) {
						// Make the MainApp frame visible
						mainApp.setVisible(true);
					} else {
						System.err.println("MainApp instance is null.");
					}
				} else {
					// Update failed
					JOptionPane.showMessageDialog(null, "Failed to update status.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDatHang.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnDatHang.setBounds(548, 163, 158, 31);
		panel.add(btnDatHang);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Danh s\u00E1ch th\u00F4ng tin chi ti\u1EBFt h\u00F3a \u0111\u01A1n",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 257, 716, 272);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// Set giá trị ngày hiện tại cho textField_4
				setNgayLapTextField();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 696, 187);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "MaH\u0110", "MaHH",
				"\u0110\u01A1n Gi\u00E1", "S\u1ED1 L\u01B0\u1EE3ng", "T\u1ED5ng ti\u1EC1n HH" }));

		btnTongHoaDon = new JTextField("0");
		btnTongHoaDon.setColumns(10);
		btnTongHoaDon.setBounds(541, 231, 152, 19);
		panel_1.add(btnTongHoaDon);

		JLabel lblNewLabel_8_1 = new JLabel("Tổng Hóa Đơn");
		lblNewLabel_8_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_8_1.setBounds(376, 226, 152, 24);
		panel_1.add(lblNewLabel_8_1);

	}

	private void setNgayLapTextField() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);
		textField_4.setText(formattedDate);
	}

	private void populateComboBox() {
		// Populate comboBox with MaKH and TenKH
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		ArrayList<KhachHang> khachHangList = khachHangDAO.getMa();
		for (KhachHang khachHang : khachHangList) {
			comboBox.addItem(khachHang.getMaKH() + " - " + khachHang.getTenKH());
		}
		// Populate comboBox_1 with MaNV and TenNV
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
		ArrayList<NhanVien> nhanVienList = nhanVienDAO.getMa();
		for (NhanVien nhanVien : nhanVienList) {
			comboBox_1.addItem(nhanVien.getMaNV() + " - " + nhanVien.getTenNV());
		}
		// Populate comboBox_2 with HangHoa objects
		HangHoaDAO hangHoaDAO = new HangHoaDAO();
		ArrayList<HangHoa> hangHoaList = hangHoaDAO.getMa();
		for (HangHoa hangHoa : hangHoaList) {
			comboBox_2.addItem(hangHoa);
		}
		// Call updateThanhTien to calculate the default value
		// updateThanhTien();
	}

	private void updateTotalCost() {
		// Assuming chiTietHDDAO is an instance of your ChiTietHDDAO class
		ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();

		// Get the details for the specified invoice
		ArrayList<ChiTietHD> chiTietHDList = chiTietHDDAO.getChiTietHDByMaHD(maHoaDon);

		// Calculate the total cost
		double totalCost = 0;
		for (ChiTietHD chiTietHD : chiTietHDList) {
			totalCost += chiTietHD.getDonGia() * chiTietHD.getSoLuong();
		}

		// Update the total cost in the UI
		btnTongHoaDon.setText(String.format("%.2f", totalCost));
	}

	private String addHoaDonToDatabase() {
		// Get the selected values from the UI components
		// String maHD = textField.getText();
		String ngayLapText = textField_4.getText();
		double tongTien;

		try {
			// Attempt to parse the total cost
			tongTien = Double.parseDouble(textField_6.getText());
		} catch (NumberFormatException e) {
			System.err.println("Invalid total cost format.");
			return null;
		}

		Object selectedKH = comboBox.getSelectedItem();
		String maKH = (selectedKH != null) ? selectedKH.toString().split(" - ")[0] : null;

		Object selectedNV = comboBox_1.getSelectedItem();
		String maNV = (selectedNV != null) ? selectedNV.toString().split(" - ")[0] : null;

		// Check if required fields are empty
		if (ngayLapText.isEmpty() || maKH == null || maNV == null) {
			System.err.println("Some required fields are empty.");
			// Handle the case where a required field is empty
			return null;
		}

		// Try parsing the date
		String dateFormat = "yyyy-MM-dd";
		Date ngayLap;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			ngayLap = sdf.parse(ngayLapText);
		} catch (ParseException e) {
			System.err.println("Invalid date format. Please use " + dateFormat + ".");
			return null;
		}

		// Proceed with adding HoaDon to the database
		// Create a new HoaDon instance
		HoaDon hoaDon = new HoaDon(generateInvoiceID(), maKH, maNV, ngayLap, tongTien);
		maHoaDon = hoaDon.getMaHD();
		// Insert the HoaDon into the database
		HoaDonDAO hoaDonDAO = new HoaDonDAO();
		int resultHoaDon = hoaDonDAO.insert(hoaDon);

		// Check the result of the HoaDon insertion
		if (resultHoaDon > 0) {
			// If successful, return the maHD for later use
			return hoaDon.getMaHD();
		} else {
			// If unsuccessful, return null or handle the error as needed
			System.err.println("Failed to add HoaDon to the database.");
			return null;
		}
	}

	// Hàm tự động sinh ra mã hóa đơn
	public static String generateInvoiceID() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = dateFormat.format(new Date());

		// Tạo một số ngẫu nhiên trong khoảng từ 100 đến 999
		int randomNum = new Random().nextInt(900) + 100;

		// Kết hợp timestamp và số ngẫu nhiên để tạo mã hóa đơn
		return timestamp + randomNum;
	}

	private void updateTableModel(String maHD) {
		// Assuming chiTietHDDAO is an instance of your ChiTietHDDAO class
		ChiTietHDDAO chiTietHDDAO = new ChiTietHDDAO();

		// Get the details for the specified invoice
		ArrayList<ChiTietHD> chiTietHDList = chiTietHDDAO.getChiTietHDByMaHD(maHD);

		// Assuming your table is named 'table' and has a DefaultTableModel
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		// Clear the existing data in the table
		model.setRowCount(0);

		// Update the table with the details
		for (ChiTietHD chiTietHD : chiTietHDList) {
			model.addRow(new Object[] { chiTietHD.getMaHD(), chiTietHD.getMaHH(), chiTietHD.getDonGia(),
					chiTietHD.getSoLuong(), chiTietHD.getTongTienHH() });
		}

		// Set the updated model to the table
		table.setModel(model);
	}
}
