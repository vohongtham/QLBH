package model;

import java.sql.Date;

public class HoaDon {

	private String MaHD;
	private String MaKH;
	private String MaNV;
	private Date ngayLap;
	private Double TongTien;

	public HoaDon(String maHD, String maKH, String maNV, java.util.Date ngayLap, double tongTien) {
		super();
		this.MaHD = maHD;
		this.MaKH = maKH;
		this.MaNV = maNV;
		this.ngayLap = new Date(ngayLap.getTime());
		this.TongTien = tongTien;
	}

	public HoaDon() {
		super();
	}

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		this.MaHD = maHD;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String maKH) {
		this.MaKH = maKH;
	}

	public String getMaNV() {
		return MaNV;
	}

	public void setMaNV(String maNV) {
		this.MaNV = maNV;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public double getTongTien() {
		return TongTien;
	}

	public void setTongTien(double tongTien) {
		this.TongTien = tongTien;
	}
}
