package vn.viettuts.qlsv.controller;

import vn.viettuts.qlsv.view.HangHoaInfoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.viettuts.qlsv.dao.HangHoaDao;
import vn.viettuts.qlsv.entity.HangHoa;
import vn.viettuts.qlsv.view.FilterView;
import vn.viettuts.qlsv.view.HomeView;
import vn.viettuts.qlsv.view.HangHoaView;

public class HangHoaController {

    private HangHoaDao hangHoaDao;
    private HangHoaView hangHoaView;
    private HangHoaInfoView hangHoaInfoView;
    private FilterView filterView;

    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public HangHoaController(HangHoaView view, FilterView filterView) {
        this.hangHoaView = view;
        this.filterView = filterView;
        hangHoaDao = new HangHoaDao();

        view.addAddHangHoaListener(new AddHangHoaListener());
        view.addFilterHangHoaListener(new FilterListener());
        view.addDeleteHangHoaListener(new DeleteHangHoaListener());
        view.addHangHoaInfoListener(new SuaThongTinHangHoaListener());
        view.addSortByMaHangListener(new SortByMaHangHoaListener());
        view.addSortByDonGiaListener(new SortHangHoaByDonGiaListener());
        view.addSortByGiaBanListener(new SortHangHoaByGiaBanListener());
        view.addSortBySoLuongListener(new SortBySoLuongListener());
        view.addSortByTenHangListener(new SortHangHoaByTenHangListener());
        view.addShowAllHangHoaListener(new ShowAllHangHoaListener());
        view.addSearchByMaHangListener(new SearchHangHoaByMaHangListener());
        view.addSearchByTenHangListener(new SearchHangHoaByTenHangListener());
        view.addSearchByDonViTinhListener(new SearchHangHoaByDonViTinhListener());
        view.addListHangHoaSelectionListener(new GetHangHoaFromSelectedRowListener());
    }

    public void showHangHoaView() {
        hangHoaDao.sortHangHoatByMaHangHoa();
        List<HangHoa> hangHoaList = hangHoaDao.getListHangHoa();
        hangHoaView.setVisible(true);
        hangHoaView.showListHangHoa(hangHoaList);
    }

    public List<HangHoa> getStudentListSort() {
        List<HangHoa> hangHoaList = new ArrayList<>();
        List<Integer> maHangHoaList = hangHoaView.getListMahangHoaTable();
        for (int id : maHangHoaList) {
            hangHoaList.add(searchHangHoaByMaHang(id));
        }
        return hangHoaList;
    }

    public HangHoa searchHangHoaByMaHang(int maHangHoa) {
        List<HangHoa> hangHoaList = hangHoaDao.getListHangHoa();
        try {
            for (HangHoa hangHoa : hangHoaList) {
                if (maHangHoa == hangHoa.getMaHangHoa()) {
                    return hangHoa;
                }
            }
            throw new IllegalArgumentException("không tìm thấy mã hàng hóa.");
        } catch (IllegalArgumentException e) {
            hangHoaView.showMessage("Không tìm thấy hàng hóa có mã: " + maHangHoa);
        }
        return null;
    }

    public List<HangHoa> searchHangHoatByTenHangHoa(String name) {
        List<HangHoa> hangHoaList = hangHoaDao.getListHangHoa();
        List<HangHoa> tenHangHoaList = new ArrayList<>();
        try {
            for (HangHoa hangHoa : hangHoaList) {
                if (hangHoa.getTenHangHoa().toLowerCase().contains(name.toLowerCase())) {
                    tenHangHoaList.add(hangHoa);
                }
            }
            if (!tenHangHoaList.isEmpty()) {
                return tenHangHoaList;
            } else {
                throw new IllegalArgumentException("Không tìm thấy hàng hóa có tên chứa: " + name);
            }
        } catch (IllegalArgumentException e) {
            hangHoaView.showMessage("Không tìm thấy hàngh hóa có tên chứa: " + name);
        }
        return null;
    }

    public List<HangHoa> searchHangHoaByDonViTinh(String donViTinh) {
        List<HangHoa> hangHoaList = hangHoaDao.getListHangHoa();
        List<HangHoa> tenHangHoaList = new ArrayList<>();
        try {
            for (HangHoa hangHoa : hangHoaList) {
                if (hangHoa.getTenHangHoa().toLowerCase().contains(donViTinh.toLowerCase())) {
                    tenHangHoaList.add(hangHoa);
                }
            }
            if (!tenHangHoaList.isEmpty()) {
                return tenHangHoaList;
            } else {
                throw new IllegalArgumentException("Không tìm thấy hàng hóa có đơn vị tính chứa: " + donViTinh);
            }
        } catch (IllegalArgumentException e) {
            hangHoaView.showMessage("Không tìm thấy hàngh hóa có đơn vị tính chứa: " + donViTinh);
        }
        return null;
    }

    /**
     * Lớp AddStudentListener chứa cài đặt cho sự kiện click button "Add"
     */
    class AddHangHoaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hangHoaInfoView = new HangHoaInfoView(hangHoaView, true);
            hangHoaInfoView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            hangHoaInfoView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    hangHoaInfoView.setSaveBtnAction(false);
                }
            });

            hangHoaInfoView.enableSaveBtn(true);
            hangHoaInfoView.enableDeleteBtn(false);
            hangHoaInfoView.enableEditBtn(false);

            hangHoaInfoView.setVisible(true);

            if (hangHoaInfoView.getSaveBtnAction()) {
                HangHoa student = hangHoaInfoView.getHangHoaInfor();
                if (student != null) {
                    hangHoaDao.add(student);
                    hangHoaInfoView.setSaveBtnAction(false);
                    hangHoaView.showListHangHoa(hangHoaDao.getListHangHoa());
                    hangHoaView.showMessage("Thêm thành công!");
                }
            }
        }
    }

    /**
     * Lớp AddStudentListener chứa cài đặt cho sự kiện click button "Filter"
     */
    class FilterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            filterView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            // Hiển thị cửa sổ lọc
            filterView.setVisible(true);
        }
    }

    class FindHangHoaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<HangHoa> result = searchHangHoa();
            if (result != null && !result.isEmpty()) {
                hangHoaView.showListHangHoa(result);
                hangHoaView.setEnableSuaThongTinHangHoa(false);
                hangHoaView.setEnableDleteHangHoaBtn(false);
            } else {
                hangHoaView.showMessage("Không tìm thấy hàng hóa theo tiêu chí lọc");
            }
        }

    }

    public List<HangHoa> searchHangHoa() {
        List<HangHoa> filteredStudents = new ArrayList<>();
        List<HangHoa> students = hangHoaDao.getListHangHoa();

        // Lấy dữ liệu từ FilterView
        String tenHangHoa = filterView.getTenHangHoa();
        String donViTinh = filterView.getDonViTinh();
        Integer fromSoLuong = filterView.getSoLuongFrom();
        Integer toSoLuong = filterView.getSoLuongTo();
        Double fromDonGia = filterView.getDonGiaFrom();
        Double toDonGia = filterView.getDonGiaTo();
        Double fromGiaBan = filterView.getGiaBanFrom();
        Double toGiaBan = filterView.getGiaBanTo();
        String fromDateNhap = filterView.getFromDateNgayNhap();
        String toDateNhap = filterView.getToDateNgayNhap();


        // Duyệt qua danh sách sinh viên để lọc
        for (HangHoa student : students) {
            boolean matches = true;

            // Kiểm tra từng điều kiện lọc (nếu điều kiện đó không rỗng hoặc không null)
            if (tenHangHoa != null && !tenHangHoa.isEmpty() && !student.getTenHangHoa().toLowerCase().contains(tenHangHoa.toLowerCase())) {
                matches = false;
            }
            if (donViTinh != null && !donViTinh.isEmpty() && !student.getDonViTinh().toLowerCase().equals(donViTinh.toLowerCase())) {
                matches = false;
            }
            if (fromSoLuong != null && student.getSoLuong()< fromSoLuong) {
                matches = false;
            }
            if (toSoLuong != null && student.getSoLuong() > toSoLuong) {
                matches = false;
            }
            if (fromDonGia != null && student.getDonGia()< fromDonGia) {
                matches = false;
            }
            if (toDonGia != null && student.getDonGia()> toDonGia) {
                matches = false;
            }
            if (fromGiaBan != null && student.getGiaBan()< fromGiaBan) {
                matches = false;
            }
            if (toGiaBan != null && student.getGiaBan()> toGiaBan) {
                matches = false;
            }
            if (fromDateNhap != null && !fromDateNhap.isEmpty() && !isDateInRange(student.getDateNhapHang(), fromDateNhap, toDateNhap)) {
                matches = false;
            }


            // Nếu sinh viên đáp ứng tất cả điều kiện, thêm vào danh sách kết quả
            if (matches) {
                filteredStudents.add(student);
            }
        }

        return filteredStudents;
    }

    // Các hàm hỗ trợ chuyển đổi và kiểm tra ngày tháng
    private Integer parseInteger(String value) {
        try {
            return value != null && !value.isEmpty() ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDouble(String value) {
        try {
            return value != null && !value.isEmpty() ? Double.parseDouble(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isDateInRange(String dateToCheck, String startDate, String endDate) {
        try {
            if (dateToCheck == null || dateToCheck.isEmpty()) {
                return false;
            }
            Date date = df.parse(dateToCheck);
            Date start = (startDate != null && !startDate.isEmpty()) ? df.parse(startDate) : null;
            Date end = (endDate != null && !endDate.isEmpty()) ? df.parse(endDate) : null;

            // Kiểm tra nếu ngày cần kiểm tra nằm trong phạm vi cho phép
            return (start == null || !date.before(start)) && (end == null || !date.after(end));
        } catch (ParseException e) {
            return false; // Nếu ngày tháng không hợp lệ, trả về false
        }
    }

    /**
     * Lớp DeleteStudentListener chứa cài đặt cho sự kiện click button "Delete"
     */
    class DeleteHangHoaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable studentTable = hangHoaView.getHangHoaTable();
            int row = hangHoaView.getHangHoaFromSelectedRow();
            HangHoa student = searchHangHoaByMaHang(Integer.parseInt(studentTable.getModel().getValueAt(row, 0).toString()));
            if (student != null) {
                hangHoaDao.delete(student);
                hangHoaView.showListHangHoa(hangHoaDao.getListHangHoa());
                hangHoaView.showMessage("Xóa thành công!");
            }
            hangHoaView.setEnableDleteHangHoaBtn(false);
            hangHoaView.setEnableSuaThongTinHangHoa(false);
        }
    }

    /**
     * Lớp SortStudentIDListener chứa cài đặt cho sự kiện click button "Sort By
     * ID"
     */
    class SortByMaHangHoaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<HangHoa> studentList = getStudentListSort();

            if (hangHoaView.enableDESCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Integer.compare(student2.getMaHangHoa(), student1.getMaHangHoa()));
            } else if (hangHoaView.enableASCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Integer.compare(student1.getMaHangHoa(), student2.getMaHangHoa()));
            }

            hangHoaView.showListHangHoa(studentList);
            hangHoaView.setEnableSuaThongTinHangHoa(false);
            hangHoaView.setEnableDleteHangHoaBtn(false);
        }
    }
    
    class SortBySoLuongListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<HangHoa> studentList = getStudentListSort();

            if (hangHoaView.enableDESCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Integer.compare(student2.getSoLuong(), student1.getSoLuong()));
            } else if (hangHoaView.enableASCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Integer.compare(student1.getSoLuong(), student2.getSoLuong()));
            }

            hangHoaView.showListHangHoa(studentList);
            hangHoaView.setEnableSuaThongTinHangHoa(false);
            hangHoaView.setEnableDleteHangHoaBtn(false);
        }
    }
    
    class SortHangHoaByGiaBanListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<HangHoa> studentList = getStudentListSort();

            if (hangHoaView.enableDESCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Double.compare(student2.getGiaBan(), student1.getGiaBan()));
            } else if (hangHoaView.enableASCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Double.compare(student1.getGiaBan(), student2.getGiaBan()));
            }

            hangHoaView.showListHangHoa(studentList);
            hangHoaView.setEnableSuaThongTinHangHoa(false);
            hangHoaView.setEnableDleteHangHoaBtn(false);
        }
    }

    /**
     * Lớp SortStudentGPAListener chứa cài đặt cho sự kiện click button "Sort By
     * GPA"
     */
    class SortHangHoaByDonGiaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<HangHoa> studentList = getStudentListSort();

            if (hangHoaView.enableDESCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Double.compare(student2.getDonGia(), student1.getDonGia()));
            } else if (hangHoaView.enableASCBtn()) {
                Collections.sort(studentList, (student1, student2) -> Double.compare(student1.getDonGia(), student2.getDonGia()));
            }

            hangHoaView.showListHangHoa(studentList);
            hangHoaView.setEnableSuaThongTinHangHoa(false);
            hangHoaView.setEnableDleteHangHoaBtn(false);
        }
    }

    /**
     * Lớp SortStudentGPAListener chứa cài đặt cho sự kiện click button "Sort By
     * Name"
     */
    class SortHangHoaByTenHangListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<HangHoa> studentList = getStudentListSort();

            if (hangHoaView.enableDESCBtn()) {
                Collections.sort(studentList, (student1, student2) -> {
                    String firstnameStu1 = student1.getTenHangHoa().split(" ")[student1.getTenHangHoa().split(" ").length - 1];
                    String firstnameStu2 = student2.getTenHangHoa().split(" ")[student2.getTenHangHoa().split(" ").length - 1];
                    return firstnameStu2.compareTo(firstnameStu1);
                });
            } else if (hangHoaView.enableASCBtn()) {
                Collections.sort(studentList, (student1, student2) -> {
                    String firstnameStu1 = student1.getTenHangHoa().split(" ")[student1.getTenHangHoa().split(" ").length - 1];
                    String firstnameStu2 = student2.getTenHangHoa().split(" ")[student2.getTenHangHoa().split(" ").length - 1];
                    return firstnameStu1.compareTo(firstnameStu2);
                });
            }

            hangHoaView.showListHangHoa(studentList);
            hangHoaView.setEnableSuaThongTinHangHoa(false);
            hangHoaView.setEnableDleteHangHoaBtn(false);
        }
    }

    /**
     * Lớp ShowAllStudentsListener chứa cài đặt cho sự kiện click button "Show
     * All Students"
     */
    class ShowAllHangHoaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hangHoaDao.sortHangHoatByMaHangHoa();
            hangHoaView.showListHangHoa(hangHoaDao.getListHangHoa());
            hangHoaView.setEnableSuaThongTinHangHoa(false);
            hangHoaView.setEnableDleteHangHoaBtn(false);
        }
    }

    /**
     * Lớp SearchStudentIDListener chứa cài đặt cho sự kiện click button "Search
     * By ID"
     */
    class SearchHangHoaByMaHangListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(null, "Nhập vào mã hàng hóa", "Mã Hàng Input", 1);
            if (input == null || input.equals("")) {
                // code nhu nay de khong bi bao loi input = null
            } else {
                int id = Integer.parseInt(input);
                List<HangHoa> listIDStudent = new ArrayList<>();
                if (searchHangHoaByMaHang(id) != null) {
                    listIDStudent.add(searchHangHoaByMaHang(id));
                    hangHoaView.showListHangHoa(listIDStudent);
                    hangHoaView.setEnableSuaThongTinHangHoa(false);
                    hangHoaView.setEnableDleteHangHoaBtn(false);
                }
            }
        }
    }

    /**
     * Lớp SearchStudentNameListener chứa cài đặt cho sự kiện click button
     * "Search By Name"
     */
    class SearchHangHoaByTenHangListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(null, "Nhập tên hàng hóa", "Tên hàng Input", JOptionPane.QUESTION_MESSAGE);
            if (input == null || input.trim().isEmpty()) {
                return; // Dừng nếu người dùng không nhập gì
            }

            List<HangHoa> result = searchHangHoatByTenHangHoa(input);
            if (result != null) {
                hangHoaView.showListHangHoa(result);
                hangHoaView.setEnableSuaThongTinHangHoa(false);
                hangHoaView.setEnableDleteHangHoaBtn(false);
            }
        }
    }

    /**
     * Lớp SearchStudentGradeListener chứa cài đặt cho sự kiện click button
     * "Search By Grade"
     */
    class SearchHangHoaByDonViTinhListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(null, "Nhập vào Lớp đơn vị tính của hàng hóa", "Đơn vị tính Input", JOptionPane.QUESTION_MESSAGE);
            if (input == null || input.trim().isEmpty()) {
                return; // Dừng nếu người dùng không nhập gì
            }

            List<HangHoa> result = searchHangHoaByDonViTinh(input);
            if (result != null) {
                hangHoaView.showListHangHoa(result);
                hangHoaView.setEnableSuaThongTinHangHoa(false);
                hangHoaView.setEnableDleteHangHoaBtn(false);
            }
        }
    }

    /**
     * Lớp ListStudentSelectionListener chứa cài đặt cho sự kiện chọn student
     * trong bảng student
     */
    class GetHangHoaFromSelectedRowListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            hangHoaView.setEnableDleteHangHoaBtn(true);
            hangHoaView.setEnableSuaThongTinHangHoa(true);
        }
    }

    /**
     * Lớp StudentInformation chứa cài đặt cho sự kiện mở dialog thông tin sinh
     * viên
     */
    class SuaThongTinHangHoaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hangHoaInfoView = new HangHoaInfoView(hangHoaView, true);
            hangHoaInfoView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            hangHoaInfoView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    hangHoaInfoView.setEditBtnAction(false);
                }
            });

            hangHoaInfoView.enableSaveBtn(false);

            JTable studentTable = hangHoaView.getHangHoaTable();
            int row = hangHoaView.getHangHoaFromSelectedRow();
            if (row >= 0) {
                hangHoaInfoView.setMaHangHoaField(studentTable.getModel().getValueAt(row, 0).toString());
                hangHoaInfoView.setTeHangHoaField(studentTable.getModel().getValueAt(row, 1).toString());
                hangHoaInfoView.setSoLuongField(studentTable.getModel().getValueAt(row, 2).toString());
                hangHoaInfoView.setDonViTinhField(studentTable.getModel().getValueAt(row, 3).toString());
                hangHoaInfoView.setDonGiaField(studentTable.getModel().getValueAt(row, 4).toString());
                hangHoaInfoView.setGiaBanField(studentTable.getModel().getValueAt(row, 6).toString());

                if (studentTable.getModel().getValueAt(row, 5) != null) {
                    hangHoaInfoView.setDateNgayNhapField(studentTable.getModel().getValueAt(row, 5).toString());
                }
            }

            hangHoaInfoView.setVisible(true);

            if (hangHoaInfoView.getDeleteBtnAction()) {
                HangHoa student = searchHangHoaByMaHang(Integer.parseInt(hangHoaInfoView.getMaHangHoaField()));
                if (student != null) {
                    hangHoaDao.delete(student);
                    hangHoaView.showListHangHoa(hangHoaDao.getListHangHoa());
                    hangHoaView.showMessage("Xóa thành công!");
                }
                hangHoaInfoView.setDeleteBtnAction(false);
                hangHoaInfoView.setEditBtnAction(false);
                hangHoaView.setEnableDleteHangHoaBtn(false);
                hangHoaView.setEnableSuaThongTinHangHoa(false);
            }

            if (hangHoaInfoView.getEditBtnAction()) {
                HangHoa student = hangHoaInfoView.getHangHoaInfor();
                if (student != null) {
                    hangHoaDao.edit(student);
                    hangHoaView.showListHangHoa(hangHoaDao.getListHangHoa());
                    hangHoaView.showMessage("Update thông tin thành công!");
                }
                hangHoaInfoView.setEditBtnAction(false);
                hangHoaView.setEnableSuaThongTinHangHoa(false);
                hangHoaView.setEnableDleteHangHoaBtn(false);
            }
        }
    }
}
