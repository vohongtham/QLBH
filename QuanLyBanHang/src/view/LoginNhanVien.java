package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.NhanVienDAO;
import model.DangNhap;
import model.NhanVien;

public class LoginNhanVien extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private NhanVienDAO nhanVienDAO; // Assuming you have a NhanVienDAO
	DangNhap dangNhap = new DangNhap();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginNhanVien frame = new LoginNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void openDangKyNhanVien() {
		DangKyNhanVien dangkyNV = new DangKyNhanVien();
		dangkyNV.setVisible(true);
		// You can also set the location, size, or other properties of the registration
		// form
	}

	/**
	 * Create the frame.
	 */
	public LoginNhanVien() {
		setTitle("Sign in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblNewLabel.setBounds(148, 0, 147, 35);
		lblNewLabel.setForeground(new Color(0, 0, 160));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		contentPane.add(lblNewLabel);

//		JButton btnNewButton_1 = new JButton("Đăng Ký");
//		btnNewButton_1.setForeground(new Color(0, 64, 128));
//		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// dispose();
//				openDangKyNhanVien();
//			}
//		});
//		btnNewButton_1.setBounds(247, 185, 154, 35);
//		contentPane.add(btnNewButton_1);

		JButton btnNewButton = new JButton("\u0110\u0103ng Nh\u1EADp");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enteredMaNV = textField.getText();
				String enteredPassword = new String(passwordField.getPassword());

				// Validate login
				if (validateLogin(enteredMaNV, enteredPassword)) {
					// Successful login
					JOptionPane.showMessageDialog(null, "Login successful");
					// You can add code here to open a new window or perform other actions upon
					// successful login
				} else {
					// Failed login
					JOptionPane.showMessageDialog(null, "Invalid MaNV or password");
				}
			}

		});
		btnNewButton.setForeground(new Color(0, 64, 128));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton.setBounds(79, 186, 262, 35);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(10, 56, 106, 35);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(10, 110, 106, 35);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(148, 63, 253, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(148, 110, 253, 31);
		contentPane.add(passwordField);
	}

	private boolean validateLogin(String enteredMaNV, String enteredPassword) {
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
		NhanVien nhanVien = nhanVienDAO.selectById(new NhanVien(enteredMaNV, "", "", "", "", "", ""));

		// Check if the NhanVien exists and the password matches
		if (nhanVien != null && enteredPassword.equals(nhanVien.getMatKhau())) {
			// Login successful, open MainApp
			MainApp mainApp = new MainApp();
			mainApp.setVisible(true);

			// Close the current login window
			dispose();

			return true;
		} else {
			// Failed login
			JOptionPane.showMessageDialog(null, "Invalid MaNV or password");
			return false;
		}
	}

}
