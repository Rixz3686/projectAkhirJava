package com.travel.view;
import com.travel.model.Paket;
import com.travel.util.UIHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
public class PaketPanel extends JPanel {
    private JTextField txtId;
    private JTextField txtNama;
    private JTextField txtTujuan;
    private JTextField txtHarga;
    private JTextField txtDurasi;
    private JTextField txtKuota;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnRefresh;
    private JTable tblPaket;
    private DefaultTableModel tableModel;
    public PaketPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
    }
    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Form Paket Travel"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Paket:"), gbc);
        txtId = new JTextField(15);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Nama Paket:"), gbc);
        txtNama = new JTextField(15);
        txtNama.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtNama, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Tujuan Wisata:"), gbc);
        txtTujuan = new JTextField(15);
        txtTujuan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtTujuan, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Harga (Rp):"), gbc);
        txtHarga = new JTextField(15);
        txtHarga.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtHarga, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Durasi (Hari):"), gbc);
        txtDurasi = new JTextField(15);
        txtDurasi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtDurasi, gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Kuota:"), gbc);
        txtKuota = new JTextField(15);
        txtKuota.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtKuota, gbc);
        JPanel actionPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        btnAdd = UIHelper.createModernButton("Tambah", true);
        btnUpdate = UIHelper.createModernButton("Ubah", false);
        btnDelete = UIHelper.createModernButton("Hapus", false);
        btnClear = UIHelper.createModernButton("Bersihkan", false);
        actionPanel.add(btnAdd);
        actionPanel.add(btnUpdate);
        actionPanel.add(btnDelete);
        actionPanel.add(btnClear);
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        formPanel.add(actionPanel, gbc);
        add(formPanel, BorderLayout.WEST);
        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Pencarian"));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnSearch = UIHelper.createModernButton("Cari", false);
        btnRefresh = UIHelper.createModernButton("Refresh", false);
        searchPanel.add(new JLabel("Kata Kunci:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnRefresh);
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        String[] columns = {"Kode Paket", "Nama Paket", "Destinasi", "Harga", "Durasi (Hari)", "Kuota"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPaket = new JTable(tableModel);
        UIHelper.styleTable(tblPaket);
        JScrollPane scrollTable = new JScrollPane(tblPaket);
        tablePanel.add(scrollTable, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);
    }
    public String getIdPaket() { return txtId.getText().trim(); }
    public void setIdPaket(String id) { txtId.setText(id); }
    public void setEditMode(boolean edit) { txtId.setEditable(!edit); }
    public String getNamaPaket() { return txtNama.getText().trim(); }
    public void setNamaPaket(String nama) { txtNama.setText(nama); }
    public String getTujuan() { return txtTujuan.getText().trim(); }
    public void setTujuan(String tujuan) { txtTujuan.setText(tujuan); }
    public String getHarga() { return txtHarga.getText().trim(); }
    public void setHarga(String harga) { txtHarga.setText(harga); }
    public String getDurasi() { return txtDurasi.getText().trim(); }
    public void setDurasi(String durasi) { txtDurasi.setText(durasi); }
    public String getKuota() { return txtKuota.getText().trim(); }
    public void setKuota(String kuota) { txtKuota.setText(kuota); }
    public String getSearchKeyword() { return txtSearch.getText().trim(); }
    public void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtTujuan.setText("");
        txtHarga.setText("");
        txtDurasi.setText("");
        txtKuota.setText("");
        txtId.setEditable(true);
    }
    public void populateTable(List<Paket> list) {
        tableModel.setRowCount(0);
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
        for (Paket p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPaket(),
                p.getNamaPaket(),
                p.getTujuan(),
                rupiah.format(p.getHarga()),
                p.getDurasiHari(),
                p.getKuota()
            });
        }
    }
    public int getSelectedRowIndex() { return tblPaket.getSelectedRow(); }
    public String getSelectedId() {
        int row = getSelectedRowIndex();
        if (row != -1) {
            return tblPaket.getValueAt(row, 0).toString();
        }
        return null;
    }
    public void addAddListener(ActionListener l) { btnAdd.addActionListener(l); }
    public void addUpdateListener(ActionListener l) { btnUpdate.addActionListener(l); }
    public void addDeleteListener(ActionListener l) { btnDelete.addActionListener(l); }
    public void addClearListener(ActionListener l) { btnClear.addActionListener(l); }
    public void addSearchListener(ActionListener l) { btnSearch.addActionListener(l); }
    public void addRefreshListener(ActionListener l) { btnRefresh.addActionListener(l); }
    public void addTableMouseListener(MouseListener l) { tblPaket.addMouseListener(l); }
}
