    package vn.viettuts.qlsv.view;

    import javax.swing.JOptionPane;
    import com.toedter.calendar.JDateChooser;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
import vn.viettuts.qlsv.entity.HangHoa;

    /**
     *
     * @author ADMIN
     */
    public class HangHoaInfoView extends javax.swing.JDialog {

        /**
         * Creates new form StudentInforView
         */
        public HangHoaInfoView(java.awt.Frame parent, boolean modal) {
            super(parent, modal);
            initComponents();
            setLocationRelativeTo(null);
        }

        public HangHoa getHangHoaInfor(){
            // validate student
            if (!validateTenHangHoa()|| !validateDateNgayNhap()|| !validateDonGia()|| !validateDonViTinh()
                    || !validateGiaBan() || !validateSoLuong() ) {
                return null;
            }
            try {
                HangHoa hangHoa = new HangHoa();

                if (MaHangHoaField.getText() != null && !"".equals(MaHangHoaField.getText())) {
                    hangHoa.setMaHangHoa(Integer.parseInt(MaHangHoaField.getText()));
                }
                hangHoa.setTenHangHoa(tenHangHoaField.getText().trim());
                hangHoa.setDonViTinh(donViTinhField.getText().trim());
                hangHoa.setDonGia(Double.parseDouble(donGiaField.getText().trim()));
                hangHoa.setSoLuong(Integer.parseInt(soLuongField.getText().trim()));
                hangHoa.setGiaBan(Double.parseDouble(giaBanField.getText().trim()));
                
                Date dateDoan = ngayNhapDate.getDate();
                if (dateDoan != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = sdf.format(dateDoan);
                    hangHoa.setDateNhapHang(dateString);  // Lưu dưới dạng String
                }

                return hangHoa;
            } catch (Exception e) {
                showMessage(e.getMessage());
            }
            return null;      
        }

        public boolean validateTenHangHoa(){
            String name = tenHangHoaField.getText();
            if (name == null || "".equals(name.trim())) {
                tenHangHoaField.requestFocus();
                showMessage("Tên hàng hóa không được trống.");
                return false;
            }
            return true;
        }

        private boolean validateSoLuong() {
            String address = soLuongField.getText();
            if (address == null || "".equals(address.trim())) {
                soLuongField.requestFocus();
                showMessage("Số lượng hàng hóa không được trống.");
                return false;
            }
            return true;
        }
        
        private boolean validateDonViTinh() {
            String address = donViTinhField.getText();
            if (address == null || "".equals(address.trim())) {
                soLuongField.requestFocus();
                showMessage("Đơn vị tính không được trống.");
                return false;
            }
            return true;
        }

        private boolean validateDonGia() {
            try {
                Double gpa = Double.parseDouble(donGiaField.getText().trim());
                if (gpa < 0) {
                    donGiaField.requestFocus();
                    showMessage("Đơn giá không hợp lệ, đơn giá phải lớn hơn 0.");
                    return false;
                }
            } catch (Exception e) {
                donGiaField.requestFocus();
                showMessage("Dơn giá không hợp lệ!");
                return false;
            }
            return true;
        }

        
        private boolean validateDateNgayNhap() {
            Date date = ngayNhapDate.getDate();  // Lấy giá trị Date từ JDateChooser
            if (date == null) {  // Kiểm tra nếu ngày chưa được chọn
                ngayNhapDate.requestFocus();
                showMessage("Ngày nhập hàng không được trống.");
                return false;
            }
            return true;
        }

        private boolean validateGiaBan() {
            String place = giaBanField.getText();
            if (place == null || "".equals(place.trim())) {
                giaBanField.requestFocus();
                showMessage("Nơi vào Đoàn không được trống.");
                return false;
            }
            return true;
        }

        public String getMaHangHoaField(){
            return MaHangHoaField.getText();
        }

        public void setMaHangHoaField(String s){
            this.MaHangHoaField.setText(s);
        }

        public void setTeHangHoaField(String s){
            this.tenHangHoaField.setText(s);
        }

        public void setDonViTinhField(String s){
            this.donViTinhField.setText(s);
        }

        public void setSoLuongField(String s){
            this.soLuongField.setText(s);
        }

        public void setDonGiaField(String s){
            this.donGiaField.setText(s);
        }

        public void setDateNgayNhapField(String s) {
            try {
                // Định dạng chuỗi ngày tháng trước khi chuyển sang Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  // Định dạng ngày phù hợp
                Date date = sdf.parse(s);  // Chuyển đổi chuỗi s thành Date
                this.ngayNhapDate.setDate(date);  // Đặt Date vào JDateChooser
            } catch (ParseException e) {
                e.printStackTrace();
                showMessage("Ngày không đúng định dạng (dd/MM/yyyy).");
            }
        }

        public void setGiaBanField(String s){
            this.giaBanField.setText(s);
        }

        public void enableSaveBtn(boolean isEnable){
            this.saveBtn.setEnabled(isEnable);
            this.saveBtn.setVisible(isEnable);
        }

        public void enableEditBtn(boolean isEnable){
            this.editButton.setEnabled(isEnable);
            this.editButton.setVisible(isEnable);
        }

        public void enableDeleteBtn(boolean isEnable){
            this.deleteButton.setEnabled(isEnable);
            this.deleteButton.setVisible(isEnable);
        }

        public void showMessage(String message){
            JOptionPane.showMessageDialog(this, message);
        }

        public boolean getSaveBtnAction(){
            return saveAction;
        }

        public void setSaveBtnAction(boolean isEnable){
            this.saveAction = isEnable;
        }

        public boolean getDeleteBtnAction(){
            return deleteAction;
        }

        public void setDeleteBtnAction(boolean isEnable){
            this.deleteAction = isEnable;
        }

        public boolean getEditBtnAction(){
            return editAction;
        }

        public void setEditBtnAction(boolean isEnable){
            this.editAction = isEnable;
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTittle = new javax.swing.JLabel();
        IDjLabel = new javax.swing.JLabel();
        MaHangHoaField = new javax.swing.JTextField();
        NamejLabel = new javax.swing.JLabel();
        jLabelGrade = new javax.swing.JLabel();
        jLabelNgayvaodoan = new javax.swing.JLabel();
        jLabelAge = new javax.swing.JLabel();
        donViTinhField = new javax.swing.JTextField();
        tenHangHoaField = new javax.swing.JTextField();
        soLuongField = new javax.swing.JTextField();
        jLabelGPA = new javax.swing.JLabel();
        donGiaField = new javax.swing.JTextField();
        jLabelNoivandoan = new javax.swing.JLabel();
        giaBanField = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        ngayNhapDate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Information");

        jLabelTittle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTittle.setText("Student Information");

        IDjLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        IDjLabel.setText("Mã hàng:");

        MaHangHoaField.setEditable(false);
        MaHangHoaField.setName("MaHangHoaField"); // NOI18N
        MaHangHoaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaHangHoaFieldActionPerformed(evt);
            }
        });

        NamejLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NamejLabel.setText("Tên hàng:");

        jLabelGrade.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelGrade.setText("Số lượng;");

        jLabelNgayvaodoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelNgayvaodoan.setText("Ngày nhập");

        jLabelAge.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelAge.setText("Đơn vị tính:");

        tenHangHoaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenHangHoaFieldActionPerformed(evt);
            }
        });

        jLabelGPA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelGPA.setText("Đơn giá:");

        jLabelNoivandoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelNoivandoan.setText("Giá bán:");

        editButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editButton.setText("Lưu");
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteButton.setText("Xóa");
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveBtn.setText("Lưu hàng hóa mới");
        saveBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveBtn.setEnabled(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        ngayNhapDate.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NamejLabel)
                            .addComponent(IDjLabel)
                            .addComponent(jLabelGrade)
                            .addComponent(jLabelNgayvaodoan))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngayNhapDate, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tenHangHoaField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaHangHoaField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTittle)
                        .addGap(309, 309, 309))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(saveBtn)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAge)
                                    .addComponent(jLabelNoivandoan, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelGPA, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(giaBanField, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(donGiaField)
                                    .addComponent(donViTinhField))))
                        .addGap(240, 240, 240))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelTittle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDjLabel)
                    .addComponent(MaHangHoaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAge)
                            .addComponent(donViTinhField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelGPA)
                            .addComponent(donGiaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giaBanField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNoivandoan))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NamejLabel)
                            .addComponent(tenHangHoaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelGrade)
                            .addComponent(soLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNgayvaodoan)
                            .addComponent(ngayNhapDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        saveAction = true;
        if (getHangHoaInfor()!= null){
            dispose();
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        deleteAction = true;
        dispose();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        editAction = true;
        if (getHangHoaInfor()!= null){
            dispose();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void tenHangHoaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenHangHoaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenHangHoaFieldActionPerformed

    private void MaHangHoaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaHangHoaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaHangHoaFieldActionPerformed
        private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private boolean saveAction = false;
    private boolean deleteAction = false;
    private boolean editAction = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDjLabel;
    private javax.swing.JTextField MaHangHoaField;
    private javax.swing.JLabel NamejLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField donGiaField;
    private javax.swing.JTextField donViTinhField;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField giaBanField;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelGPA;
    private javax.swing.JLabel jLabelGrade;
    private javax.swing.JLabel jLabelNgayvaodoan;
    private javax.swing.JLabel jLabelNoivandoan;
    private javax.swing.JLabel jLabelTittle;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser ngayNhapDate;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField soLuongField;
    private javax.swing.JTextField tenHangHoaField;
    // End of variables declaration//GEN-END:variables
}
