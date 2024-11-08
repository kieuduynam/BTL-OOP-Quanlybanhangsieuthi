/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.viettuts.qlsv.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import vn.viettuts.qlsv.dao.HangHoaDao;
import vn.viettuts.qlsv.dao.HoaDonDao;
import vn.viettuts.qlsv.entity.HangHoa;
import vn.viettuts.qlsv.entity.HoaDon;
import vn.viettuts.qlsv.view.BanHangView;

/**
 *
 * @author JC
 */
public class BanHangController {
    private HoaDonDao hoaDonDao;
    private HangHoaDao hangHoaDao;
    private BanHangView banHangView;
    
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public BanHangController(HoaDonDao hoaDonDao,HangHoaDao hangHoaDao, BanHangView banHangView) {
        hoaDonDao = new HoaDonDao();
        hangHoaDao = new HangHoaDao();
        this.banHangView = banHangView;
        
        banHangView.addAddVaoHoaDonListener(new addVaoHoaDonListener());
        banHangView.addDeleteKhoiHoaDonListener(new delteKhoiHoaDonListener());
        banHangView.addGetHangHoaFromSelectedRowListener(new GetHangHoaFromSelectedRowListener());
        banHangView.addTaoHoaDonListener(new TaoHoaDonListener());
    }
    
    public void showBanHangView() {
        hoaDonDao.sortHoaDontByMaHoaDon();
        List<HoaDon> hoaDonList = hoaDonDao.getListHoaDon();
        List<HangHoa> hangHoaList = hangHoaDao.getListHangHoa();
        banHangView.setVisible(true);
        banHangView.ShowListHoaDon(hoaDonList);
        banHangView.ShowListHangHoa(hangHoaList);
    }
    
    
    
    
    
}
