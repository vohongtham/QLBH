package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.NhanVienDAO;
import model.NhanVien;

public class DangKyNhanVien extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKyNhanVien frame = new DangKyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void openLoginNhanVien() {
		LoginNhanVien loginNV = new LoginNhanVien();
		loginNV.setVisible(true);
		// You can also set the location, size, or other properties of the registration
		// form
	}

	/**
	 * Create the frame.
	 */
	public DangKyNhanVien() {
		setTitle("Sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u0110\u0103ng K\u00FD Nh\u00E2n Vi\u00EAn");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(78, 0, 268, 34);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã Nhân Viên");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(10, 50, 163, 34);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tên Nhân Viên");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(10, 105, 163, 34);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ngày Sinh");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_3.setBounds(10, 168, 163, 34);
		contentPane.add(lblNewLabel_3);

		textField = new JTextField();
		textField.setBounds(204, 44, 232, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(204, 413, 232, 40);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("\u0110\u0103ng K\u00FD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setChucVu(textField_4.getText());
				nhanVien.setMaNV(textField.getText());
				nhanVien.setTenNV(textField_1.getText());
				nhanVien.setNgaySinh(textField_2.getText());
				nhanVien.setSDT(textField_5.getText());
				nhanVien.setMatKhau(passwordField.getText());
				nhanVien.setDiaChi(textField_3.getText());
				NhanVienDAO nhanVienD = new NhanVienDAO();
				nhanVienD.insert(nhanVien);
			}
		});
//		JButton btnNewButton = new JButton("\u0110\u0103ng K\u00FD");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// System.out.println(passwordField.getText());
//				NhanVien nhanVien = new NhanVien();
//				nhanVien.setChucVu(textField_4.getText());
//				nhanVien.setMaNV(textField.getText());
//				nhanVien.setTenNV(textField_1.getText());
//				nhanVien.setNgaySinh(textField_2.getText());
//				nhanVien.setSDT(textField_5.getText());
//				nhanVien.setMatKhau(passwordField.getText());
//				nhanVien.setDiaChi(textField_3.getText());
//
//				NhanVienDAO nhanVienD = new NhanVienDAO();
//				boolean registrationSuccess = nhanVienD.insert(nhanVien);
//
//				if (registrationSuccess) {
//					JOptionPane.showMessageDialog(DangKyNhanVien.this, "Đăng ký thành công");
//				} else {
//					JOptionPane.showMessageDialog(DangKyNhanVien.this, "Đăng ký thất bại");
//				}
//			}
//		});

		btnNewButton.setForeground(new Color(0, 64, 128));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton.setBounds(110, 463, 220, 40);
		contentPane.add(btnNewButton);

//		JButton btnNewButton_1 = new JButton("Đăng Nhập");
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				openLoginNhanVien();
//				// dispose();
//			}
//		});
//		btnNewButton_1.setForeground(new Color(0, 64, 128));
//		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
//		btnNewButton_1.setBounds(241, 463, 163, 40);
//		contentPane.add(btnNewButton_1);

		textField_1 = new JTextField();
		textField_1.setBounds(204, 106, 232, 40);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Địa Chỉ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_4.setBounds(10, 233, 145, 34);
		contentPane.add(lblNewLabel_4);

		textField_2 = new JTextField();
		textField_2.setBounds(204, 168, 232, 42);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(204, 234, 232, 40);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Chức Vụ");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_5.setBounds(10, 295, 145, 37);
		contentPane.add(lblNewLabel_5);

		textField_4 = new JTextField();
		textField_4.setBounds(204, 290, 232, 42);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Mật Khẩu");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_6.setBounds(10, 412, 145, 34);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("SĐT");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_7.setBounds(10, 358, 145, 26);
		contentPane.add(lblNewLabel_7);

		textField_5 = new JTextField();
		textField_5.setBounds(204, 355, 232, 40);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
	}
}
