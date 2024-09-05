package model;

public class ChiTietHD {

	private String MaHD;
	private String MaHH;
	private double DonGia;
	private int SoLuong;
	private double TongTienHH;

	public ChiTietHD() {
		super();
	}

	public ChiTietHD(String maHD, String maHH, double donGia, int soLuong) {
		super();
		this.MaHD = maHD;
		this.MaHH = maHH;
		this.DonGia = donGia;
		this.SoLuong = soLuong;
		this.TongTienHH = donGia * soLuong;
	}

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		this.MaHD = maHD;
	}

	public String getMaHH() {
		return MaHH;
	}

	public void setMaHH(String maHH) {
		this.MaHH = maHH;
	}

	public double getDonGia() {
		return DonGia;
	}

	public void setDonGia(double thanhTien) {
		this.DonGia = thanhTien;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		this.SoLuong = soLuong;
	}

	public void tinhTongTienHH() {
		this.TongTienHH = this.DonGia * this.SoLuong;
	}

	public void setTongTienHH(double tongTienHH) {
		this.TongTienHH = tongTienHH;
	}

	public double getTongTienHH() {
		return TongTienHH;
	}

}
