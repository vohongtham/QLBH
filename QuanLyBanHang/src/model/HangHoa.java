package model;

public class HangHoa {
	private String MaHH;
	private String TenHH;
	private String DVTinh;
//	private String SoLuong;
	private String GiaBan;
	private double giaBan;

	public HangHoa() {
		super();
	}

	public HangHoa(String maHH, String tenHH, String dVTinh, String giaBan) {
		super();
		this.MaHH = maHH;
		this.TenHH = tenHH;
		this.DVTinh = dVTinh;
		this.GiaBan = giaBan;
		this.giaBan = Double.parseDouble(giaBan); // Parse string to double
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
		this.GiaBan = String.valueOf(giaBan); // Convert double to string
	}

	public String getMaHH() {
		return MaHH;
	}

	public void setMaHH(String maHH) {
		this.MaHH = maHH;
	}

	public String getTenHH() {
		return TenHH;
	}

	public void setTenHH(String tenHH) {
		this.TenHH = tenHH;
	}

	public String getDVTinh() {
		return DVTinh;
	}

	public void setDVTinh(String dVTinh) {
		this.DVTinh = dVTinh;
	}

//	public String getSoLuong() {
//		return SoLuong;
//	}

//	public void setSoLuong(String soLuong) {
//		this.SoLuong = soLuong;
//	}

	public String getGiaBanString() {
		return GiaBan;
	}

	public void setGiaBanString(String giaBan) {
		this.GiaBan = giaBan;
		this.giaBan = Double.parseDouble(giaBan); // Parse string to double
	}

	public String toString() {
		return getMaHH() + " - " + getTenHH() + " - " + getGiaBan();
	}
}
