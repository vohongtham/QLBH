package model;

import java.sql.Date;

public class NhanVien {
	private String MaNV;
	private String TenNV;
	private String NgaySinh;
	private String DiaChi;
	private String ChucVu;
	private String SDT;
	private String MatKhau;
	
	
	public NhanVien() {
		super();
	}
	
	public NhanVien(String maNV, String tenNV, String ngaySinh, String diaChi, String chucVu, String sDT, String matKhau) {
		super();
		this.MaNV = maNV;
		this.TenNV = tenNV;
		this.NgaySinh = ngaySinh;
		this.DiaChi = diaChi;
		this.ChucVu = chucVu;
		this.SDT = sDT;
		this.MatKhau = matKhau;
		
	}
	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		MaNV = maNV;
	}

	public String getTenNV() {
		return TenNV;
	}

	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}
	
	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getChucVu() {
		return ChucVu;
	}

	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	
}
