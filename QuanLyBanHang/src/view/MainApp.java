package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MainApp extends JFrame {

	private JPanel contentPane;
	private HangHoaForm hangHoaForm;
	private KhachHangForm khachHangForm;
	private DangKyNhanVien dangkyNhanVienForm;
	private CTHoaDon ctHoaDonForm;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				MainApp frame = new MainApp();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton btnQuanLyNhanVien = new JButton("Đăng ký Nhân Viên");
		btnQuanLyNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnQuanLyNhanVien.setBounds(68, 50, 199, 46);
		JButton btnQuanLyHangHoa = new JButton("Quản Lý Hàng Hóa");
		btnQuanLyHangHoa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnQuanLyHangHoa.setBounds(348, 50, 199, 46);
		JButton btnQuanLyKhachHang = new JButton("Quản Lý Khách Hàng");
		btnQuanLyKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnQuanLyKhachHang.setBounds(348, 139, 220, 46);
		JButton btnQuanLyHoaDon = new JButton("Quản Lý Hóa Đơn");
		btnQuanLyHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnQuanLyHoaDon.setBounds(68, 139, 199, 46);

		btnQuanLyNhanVien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showDangKyNhanVienForm();
			}
		});

		btnQuanLyHangHoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showQuanLyHangHoaForm();
			}
		});

		btnQuanLyKhachHang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showQuanLyKhachHangForm();
			}
		});

		btnQuanLyHoaDon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showQuanLyHoaDonForm();
			}
		});
		contentPane.setLayout(null);

		contentPane.add(btnQuanLyNhanVien);
		contentPane.add(btnQuanLyHangHoa);
		contentPane.add(btnQuanLyKhachHang);
		contentPane.add(btnQuanLyHoaDon);

		setContentPane(contentPane);
	}

	private void showDangKyNhanVienForm() {
		if (dangkyNhanVienForm == null) {
			dangkyNhanVienForm = new DangKyNhanVien();
			dangkyNhanVienForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dangkyNhanVienForm.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					setVisible(true);
				}
			});
		}
		dangkyNhanVienForm.setVisible(true);
		setVisible(false);
	}

	private void showQuanLyHoaDonForm() {
		if (ctHoaDonForm == null) {
			ctHoaDonForm = new CTHoaDon();
			ctHoaDonForm.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					ctHoaDonForm.dispose();
					setVisible(true);
				}
			});
		}

		ctHoaDonForm.setVisible(true);
		setVisible(false);
	}

	private void showQuanLyHangHoaForm() {
		if (hangHoaForm == null) {
			hangHoaForm = new HangHoaForm();

			// Xử lý sự kiện đóng form HangHoaForm
			hangHoaForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			hangHoaForm.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					// Xử lý khi form HangHoaForm đóng
					setVisible(true); // Hiển thị lại MainApp
				}
			});
		}

		// Hiển thị form HangHoaForm
		hangHoaForm.setVisible(true);

		// Ẩn form MainApp khi mở form HangHoaForm
		setVisible(false);
	}

	private void showQuanLyKhachHangForm() {
		if (khachHangForm == null) {
			khachHangForm = new KhachHangForm();
			khachHangForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			khachHangForm.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					setVisible(true);
				}
			});
		}
		khachHangForm.setVisible(true);
		setVisible(false);
	}
}
