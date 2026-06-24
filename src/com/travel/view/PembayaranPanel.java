package com.travel.view;
import com.travel.model.Pemesanan;
import com.travel.model.Pembayaran;
import com.travel.util.UIHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
public class PembayaranPanel extends JPanel {
    private JTextField txtId;
    private JComboBox<String> cbPemesanan;
    private JTextField txtTanggal;
    private JComboBox<String> cbMetode;
    private JTextField txtJumlah;
    private JComboBox<String> cbStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh;
    private JTable tblPembayaran;
    private DefaultTableModel tableModel;
    private java.util.List<String> pemesananIds = new java.util.ArrayList<>();
    public PembayaranPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
    }
    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Form Pembayaran"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Pembayaran:"), gbc);
        txtId = new JTextField(15);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("ID Pemesanan:"), gbc);
        cbPemesanan = new JComboBox<>();
        cbPemesanan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(cbPemesanan, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Tanggal Bayar (yyyy-MM-dd):"), gbc);
        txtTanggal = new JTextField(15);
        txtTanggal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtTanggal, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Metode Bayar:"), gbc);
        String[] metodeList = {"Transfer Bank", "Tunai", "Kartu Kredit", "E-Wallet"};
        cbMetode = new JComboBox<>(metodeList);
        cbMetode.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(cbMetode, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Jumlah Bayar (Rp):"), gbc);
        txtJumlah = new JTextField(15);
        txtJumlah.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtJumlah, gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Status Pembayaran:"), gbc);
        String[] statusList = {"Lunas", "DP", "Belum Bayar"};
        cbStatus = new JComboBox<>(statusList);
        cbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(cbStatus, gbc);
        JPanel actionPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        btnAdd = UIHelper.createModernButton("Tambah", true);
        btnUpdate = UIHelper.createModernButton("Ubah", false);
        btnDelete = UIHelper.createModernButton("Hapus", false);
        btnClear = UIHelper.createModernButton("Bersihkan", false);
        actionPanel.add(btnAdd);
        actionPanel.add(btnUpdate);
        actionPanel.add(btnDelete);
        actionPanel.add(btnClear);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.weighty = 1.0;
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
        String[] columns = {"ID Pembayaran", "ID Pemesanan", "Pelanggan", "Tanggal Bayar", "Metode Bayar", "Jumlah Bayar", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPembayaran = new JTable(tableModel);
        UIHelper.styleTable(tblPembayaran);
        tablePanel.add(new JScrollPane(tblPembayaran), BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);
    }
    public String getIdPembayaran() { return txtId.getText().trim(); }
    public void setIdPembayaran(String id) { txtId.setText(id); }
    public void setEditMode(boolean edit) { txtId.setEditable(!edit); }
    public String getIdPemesanan() { return cbPemesanan.getSelectedItem() != null ? cbPemesanan.getSelectedItem().toString() : ""; }
    public void setIdPemesanan(String id) { cbPemesanan.setSelectedItem(id); }
    public String getTanggal() { return txtTanggal.getText().trim(); }
    public void setTanggal(String tgl) { txtTanggal.setText(tgl); }
    public String getMetodeBayar() { return cbMetode.getSelectedItem().toString(); }
    public void setMetodeBayar(String metode) { cbMetode.setSelectedItem(metode); }
    public String getJumlahBayar() { return txtJumlah.getText().trim(); }
    public void setJumlahBayar(String jml) { txtJumlah.setText(jml); }
    public String getStatusPembayaran() { return cbStatus.getSelectedItem().toString(); }
    public void setStatusPembayaran(String status) { cbStatus.setSelectedItem(status); }
    public String getSearchKeyword() { return txtSearch.getText().trim(); }
    public void loadPemesananCombo(List<Pemesanan> list) {
        cbPemesanan.removeAllItems();
        pemesananIds.clear();
        for (Pemesanan p : list) {
            String item = p.getIdPemesanan() + " - " + (p.getNamaPelanggan() != null ? p.getNamaPelanggan() : p.getIdPelanggan());
            cbPemesanan.addItem(item);
            pemesananIds.add(p.getIdPemesanan());
        }
    }
    public String getSelectedPemesananId() {
        int idx = cbPemesanan.getSelectedIndex();
        if (idx >= 0 && idx < pemesananIds.size()) {
            return pemesananIds.get(idx);
        }
        return "";
    }
    public void setSelectedPemesananById(String idPemesanan) {
        for (int i = 0; i < pemesananIds.size(); i++) {
            if (pemesananIds.get(i).equals(idPemesanan)) {
                cbPemesanan.setSelectedIndex(i);
                break;
            }
        }
    }
    public void clearForm() {
        txtId.setText("");
        if (cbPemesanan.getItemCount() > 0) cbPemesanan.setSelectedIndex(0);
        txtTanggal.setText("");
        cbMetode.setSelectedIndex(0);
        txtJumlah.setText("");
        cbStatus.setSelectedIndex(0);
        txtId.setEditable(true);
    }
    public void populateTable(List<Pembayaran> list) {
        tableModel.setRowCount(0);
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
        for (Pembayaran p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPembayaran(),
                p.getIdPemesanan(),
                p.getNamaPelanggan(),
                p.getTanggalBayar(),
                p.getMetodeBayar(),
                rupiah.format(p.getJumlahBayar()),
                p.getStatusPembayaran()
            });
        }
    }
    public int getSelectedRowIndex() { return tblPembayaran.getSelectedRow(); }
    public String getSelectedId() {
        int row = getSelectedRowIndex();
        return row != -1 ? tblPembayaran.getValueAt(row, 0).toString() : null;
    }
    public void addAddListener(ActionListener l) { btnAdd.addActionListener(l); }
    public void addUpdateListener(ActionListener l) { btnUpdate.addActionListener(l); }
    public void addDeleteListener(ActionListener l) { btnDelete.addActionListener(l); }
    public void addClearListener(ActionListener l) { btnClear.addActionListener(l); }
    public void addSearchListener(ActionListener l) { btnSearch.addActionListener(l); }
    public void addRefreshListener(ActionListener l) { btnRefresh.addActionListener(l); }
    public void addTableMouseListener(MouseListener l) { tblPembayaran.addMouseListener(l); }
}
