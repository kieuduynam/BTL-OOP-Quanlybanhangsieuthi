package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class HangHoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private int maHangHoa, soLuong;
    private String tenHangHoa, donViTinh;
    private String dateNhapHang;
    private double donGia, giaBan;
    /* điểm trung bình của sinh viên */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public HangHoa() {
    }

    public HangHoa(int maHangHoa, int soLuong, String tenHangHoa, String donViTinh, String dateNhapHang, double donGia, double giaBan) {
        this.maHangHoa = maHangHoa;
        this.soLuong = soLuong;
        this.tenHangHoa = tenHangHoa;
        this.donViTinh = donViTinh;
        this.dateNhapHang = dateNhapHang;
        this.donGia = donGia;
        this.giaBan = giaBan;
    }

    public int getMaHangHoa() {
        return maHangHoa;
    }

    public void setMaHangHoa(int maHangHoa) {
        this.maHangHoa = maHangHoa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenHangHoa() {
        return tenHangHoa;
    }

    public void setTenHangHoa(String tenHangHoa) {
        this.tenHangHoa = tenHangHoa;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getDateNhapHang() {
        return dateNhapHang;
    }

    public void setDateNhapHang(String dateNhapHang) {
        this.dateNhapHang = dateNhapHang;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    
    
    
}
