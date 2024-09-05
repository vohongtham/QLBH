package model;

public class KhachHang {
	private String MaKH;
	private String TenKH;
	private String NgaySinh;
	private String DiaChi;
	private String SDT;

	public KhachHang() {
		super();
	}

	public KhachHang(String maKh, String tenKH, String ngaySinh, String diaChi, String sDT) {
		super();
		this.MaKH = maKh;
		this.TenKH = tenKH;
		this.NgaySinh = ngaySinh;
		this.DiaChi = diaChi;
		this.SDT = sDT;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String maKh) {
		// Trim the input to remove leading and trailing spaces
		this.MaKH = maKh.trim();
	}

	public String getTenKH() {
		return TenKH;
	}

	public void setTenKH(String tenKH) {
		this.TenKH = tenKH;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.NgaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		this.DiaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		this.SDT = sDT;
	}

}
