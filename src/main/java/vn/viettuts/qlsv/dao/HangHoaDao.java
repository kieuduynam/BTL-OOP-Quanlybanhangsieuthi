package vn.viettuts.qlsv.dao;
import vn.viettuts.qlsv.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import vn.viettuts.qlsv.entity.HangHoa;
import vn.viettuts.qlsv.entity.HangHoaXML;


/**
 * HangHoaDao class
 * 
 * @author viettuts.vn
 */
public class HangHoaDao {
    private static final String STUDENT_FILE_NAME = "student.xml";
    private List<HangHoa> listHangHoa;

    public HangHoaDao() {
        this.listHangHoa = readListHangHoa();
        if (listHangHoa == null) {
            listHangHoa = new ArrayList<HangHoa>();
        }
    }

    /**
     * Lưu các đối tượng student vào file student.xml
     * 
     * @param students
     */
    public void writeListHangHoa(List<HangHoa> listHangHoa) {
        HangHoaXML hangHoaXML = new HangHoaXML();
        hangHoaXML.setListHangHoa(listHangHoa);
        FileUtils.writeXMLtoFile(STUDENT_FILE_NAME, hangHoaXML);
    }

    /**
     * Đọc các đối tượng student từ file student.xml
     * 
     * @return list student
     */
    public List<HangHoa> readListHangHoa() {
        List<HangHoa> list = new ArrayList<HangHoa>();
        HangHoaXML hangHoaXML = (HangHoaXML) FileUtils.readXMLFile(
                STUDENT_FILE_NAME, HangHoaXML.class);
        if (hangHoaXML != null) {
            list = hangHoaXML.getListHangHoa();
        }
        return list;
    }  

    /**
     * thêm student vào listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void add(HangHoa hangHoa) {
        int maHangHoa = 1;
        if (listHangHoa != null && listHangHoa.size() > 0) {
            maHangHoa = listHangHoa.size() + 1;
        }
        hangHoa.setMaHangHoa(maHangHoa);
        listHangHoa.add(hangHoa);
        writeListHangHoa(listHangHoa);
    }

    /**
     * cập nhật student vào listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void edit(HangHoa hangHoa) {
        int size = listHangHoa.size();
        for (int i = 0; i < size; i++) {
            if (listHangHoa.get(i).getMaHangHoa()== hangHoa.getMaHangHoa()) {                
                listHangHoa.get(i).setTenHangHoa(hangHoa.getTenHangHoa());
                listHangHoa.get(i).setSoLuong(hangHoa.getSoLuong());
                listHangHoa.get(i).setDonGia(hangHoa.getDonGia());
                listHangHoa.get(i).setDonViTinh(hangHoa.getDonViTinh());
                listHangHoa.get(i).setDateNhapHang(hangHoa.getDateNhapHang());
                listHangHoa.get(i).setGiaBan(hangHoa.getGiaBan());                          
                writeListHangHoa(listHangHoa);
                break;
            }
        }
    }

    /**
     * xóa student từ listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void delete(HangHoa hangHoa) {
        boolean isFound = false;
        int size = listHangHoa.size();
        for (int i = 0; i < size; i++) {
            if (listHangHoa.get(i).getMaHangHoa()== hangHoa.getMaHangHoa()) {
                hangHoa = listHangHoa.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {      
            for (int k=hangHoa.getMaHangHoa(); k<size; k++){
                listHangHoa.get(k).setMaHangHoa(k);
            }
            listHangHoa.remove(hangHoa);
            writeListHangHoa(listHangHoa);
        }
    }
    
    /**
     * sắp xếp danh sách student theo ID theo tứ tự tăng dần
     */
    public void sortHangHoatByMaHangHoa() {
        Collections.sort(listHangHoa, new Comparator<HangHoa>() {
            @Override
            public int compare(HangHoa hangHoa1, HangHoa hangHoa2) {
                if (hangHoa1.getMaHangHoa() > hangHoa1.getMaHangHoa()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<HangHoa> getListHangHoa() {
        return listHangHoa;
    }

    public void setListHangHoa(List<HangHoa> listHangHoa) {
        this.listHangHoa = listHangHoa;
    }
}