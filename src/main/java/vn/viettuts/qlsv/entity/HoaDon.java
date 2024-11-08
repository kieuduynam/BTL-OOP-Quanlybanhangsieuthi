/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "doanVien")
@XmlAccessorType(XmlAccessType.FIELD)

public class HoaDon implements Serializable {

    private static final long serialVersionUID = 1L;
    private int maHoaDon;
    private String tenKhachHang;
    private double tongTien;
    private String dateTaoHoaDon;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public HoaDon() {
    }

    public HoaDon(int maHoaDon, int soLuong, String tenKhachHang, double giaBan, String dateTaoHoaDon) {
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.tongTien = giaBan;
        this.dateTaoHoaDon = dateTaoHoaDon;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public double getGiaBan() {
        return tongTien;
    }

    public void setGiaBan(double giaBan) {
        this.tongTien = giaBan;
    }

    public String getDateTaoHoaDon() {
        return dateTaoHoaDon;
    }

    public void setDateTaoHoaDon(String dateTaoHoaDon) {
        this.dateTaoHoaDon = dateTaoHoaDon;
    }

    
    
}


