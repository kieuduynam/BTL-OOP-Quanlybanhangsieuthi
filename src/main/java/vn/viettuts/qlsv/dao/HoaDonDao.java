/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.viettuts.qlsv.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import vn.viettuts.qlsv.entity.HoaDon;
import vn.viettuts.qlsv.entity.HoaDonXML;
import vn.viettuts.qlsv.utils.FileUtils;

/**
 *
 * @author miin
 */
public class HoaDonDao {
    private static final String HOADON_FILE_NAME = "doanVien.xml";
    private List<HoaDon> listHoaDon;  
    
    public HoaDonDao() {
        this.listHoaDon = readListHoaDon();
        if(listHoaDon == null) {
            listHoaDon = new ArrayList<HoaDon>();
        }
    }
    
    public void writeListHoaDon(List<HoaDon> listHoaDon) {
        HoaDonXML hoaDonXML = new HoaDonXML();
        hoaDonXML.setListHoaDon(listHoaDon);
        FileUtils.writeXMLtoFile(HOADON_FILE_NAME, hoaDonXML);
    }
    
    public List<HoaDon> readListHoaDon() {
        List<HoaDon> list = new ArrayList<HoaDon>();
        HoaDonXML hoaDonXML = (HoaDonXML) FileUtils.readXMLFile(
                HOADON_FILE_NAME, HoaDonXML.class);
        if (hoaDonXML != null) {
            list = hoaDonXML.getListHoaDon();
        }
        return list;
    }  
    
    public void add(HoaDon hoaDon) {
        int maHoaDon = 1;
        if (listHoaDon != null && listHoaDon.size() > 0) {
            maHoaDon = listHoaDon.size() + 1;
        }
        hoaDon.setMaHoaDon(maHoaDon);
        listHoaDon.add(hoaDon);
        writeListHoaDon(listHoaDon);
    }
    
    public void edit(HoaDon hoaDon) {
        int size = listHoaDon.size();
        for (int i = 0; i < size; i++) {
            if (listHoaDon.get(i).getMaHoaDon()== hoaDon.getMaHoaDon()) {                
                listHoaDon.get(i).setTenKhachHang(hoaDon.getTenKhachHang());
                listHoaDon.get(i).setSoLuong(hoaDon.getSoLuong());
                listHoaDon.get(i).setGiaBan(hoaDon.getGiaBan());
                listHoaDon.get(i).setDateTaoHoaDon(hoaDon.getDateTaoHoaDon());
                writeListHoaDon(listHoaDon);
                break;
            }
        }
    }
    
    public void delete(HoaDon hangHoa) {
        boolean isFound = false;
        int size = listHoaDon.size();
        for (int i = 0; i < size; i++) {
            if (listHoaDon.get(i).getMaHoaDon()== hangHoa.getMaHoaDon()) {
                hangHoa = listHoaDon.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {      
            for (int k=hangHoa.getMaHoaDon(); k<size; k++){
                listHoaDon.get(k).setMaHoaDon(k);
            }
            listHoaDon.remove(hangHoa);
            writeListHoaDon(listHoaDon);
        }
    }
    
    public void sortHoaDontByMaHoaDon() {
        Collections.sort(listHoaDon, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon hangHoa1, HoaDon hangHoa2) {
                if (hangHoa1.getMaHoaDon() > hangHoa1.getMaHoaDon()) {
                    return 1;
                }
                return -1;
            }
        });
    }
    
    public List<HoaDon> getListHoaDon() {
        return listHoaDon;
    }
    
    public void setListHoaDon(List<HoaDon> listHoaDon) {
        this.listHoaDon = listHoaDon;
    }
    
    
}
