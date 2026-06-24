package com.travel.view;
import com.travel.model.Pelanggan;
import com.travel.model.Paket;
import com.travel.model.Pemesanan;
import com.travel.util.UIHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
public class PemesananPanel extends JPanel {
    private JTextField txtId;
    private JComboBox<Pelanggan> cbPelanggan;
    private JComboBox<Paket> cbPaket;
    private JTextField txtTanggal;
    private JTextField txtJumlahPeserta;
    private JTextField txtTotalBayar;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnHitung;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh;
    private JTable tblPemesanan;
    private DefaultTableModel tableModel;
    public PemesananPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
    }
    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Form Pemesanan"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Pemesanan:"), gbc);
        txtId = new JTextField(15);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Pelanggan:"), gbc);
        cbPelanggan = new JComboBox<>();
        cbPelanggan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(cbPelanggan, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Paket Travel:"), gbc);
        cbPaket = new JComboBox<>();
        cbPaket.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(cbPaket, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Tanggal Pesan (yyyy-MM-dd):"), gbc);
        txtTanggal = new JTextField(15);
        txtTanggal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtTanggal, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Jumlah Peserta:"), gbc);
        txtJumlahPeserta = new JTextField(15);
        txtJumlahPeserta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtJumlahPeserta, gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Total Bayar (Rp):"), gbc);
        txtTotalBayar = new JTextField(15);
        txtTotalBayar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTotalBayar.setEditable(false);
        gbc.gridx = 1;
        formPanel.add(txtTotalBayar, gbc);
        btnHitung = UIHelper.createModernButton("Hitung Total", true);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        formPanel.add(btnHitung, gbc);
        JPanel actionPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        btnAdd = UIHelper.createModernButton("Tambah", true);
        btnUpdate = UIHelper.createModernButton("Ubah", false);
        btnDelete = UIHelper.createModernButton("Hapus", false);
        btnClear = UIHelper.createModernButton("Bersihkan", false);
        actionPanel.add(btnAdd);
        actionPanel.add(btnUpdate);
        actionPanel.add(btnDelete);
        actionPanel.add(btnClear);
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2; gbc.weighty = 1.0;
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
        String[] columns = {"ID Pemesanan", "Pelanggan", "Paket Travel", "Tanggal Pesan", "Jumlah Peserta", "Total Bayar"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPemesanan = new JTable(tableModel);
        UIHelper.styleTable(tblPemesanan);
        tablePanel.add(new JScrollPane(tblPemesanan), BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);
    }
    public String getIdPemesanan() { return txtId.getText().trim(); }
    public void setIdPemesanan(String id) { txtId.setText(id); }
    public void setEditMode(boolean edit) { txtId.setEditable(!edit); }
    public Pelanggan getSelectedPelanggan() { return (Pelanggan) cbPelanggan.getSelectedItem(); }
    public void setSelectedPelanggan(String idPelanggan) {
        for (int i = 0; i < cbPelanggan.getItemCount(); i++) {
            if (cbPelanggan.getItemAt(i).getIdPelanggan().equals(idPelanggan)) {
                cbPelanggan.setSelectedIndex(i);
                break;
            }
        }
    }
    public Paket getSelectedPaket() { return (Paket) cbPaket.getSelectedItem(); }
    public void setSelectedPaket(String idPaket) {
        for (int i = 0; i < cbPaket.getItemCount(); i++) {
            if (cbPaket.getItemAt(i).getIdPaket().equals(idPaket)) {
                cbPaket.setSelectedIndex(i);
                break;
            }
        }
    }
    public String getTanggal() { return txtTanggal.getText().trim(); }
    public void setTanggal(String tgl) { txtTanggal.setText(tgl); }
    public String getJumlahPeserta() { return txtJumlahPeserta.getText().trim(); }
    public void setJumlahPeserta(String jml) { txtJumlahPeserta.setText(jml); }
    public String getTotalBayar() { return txtTotalBayar.getText().trim(); }
    public void setTotalBayar(String total) { txtTotalBayar.setText(total); }
    public String getSearchKeyword() { return txtSearch.getText().trim(); }
    public void loadPelangganCombo(List<Pelanggan> list) {
        cbPelanggan.removeAllItems();
        for (Pelanggan p : list) { cbPelanggan.addItem(p); }
    }
    public void loadPaketCombo(List<Paket> list) {
        cbPaket.removeAllItems();
        for (Paket p : list) { cbPaket.addItem(p); }
    }
    public void clearForm() {
        txtId.setText("");
        if (cbPelanggan.getItemCount() > 0) cbPelanggan.setSelectedIndex(0);
        if (cbPaket.getItemCount() > 0) cbPaket.setSelectedIndex(0);
        txtTanggal.setText("");
        txtJumlahPeserta.setText("");
        txtTotalBayar.setText("");
        txtId.setEditable(true);
    }
    public void populateTable(List<Pemesanan> list) {
        tableModel.setRowCount(0);
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
        for (Pemesanan p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPemesanan(),
                p.getNamaPelanggan(),
                p.getNamaPaket(),
                p.getTanggalPesan(),
                p.getJumlahPeserta(),
                rupiah.format(p.getTotalBayar())
            });
        }
    }
    public int getSelectedRowIndex() { return tblPemesanan.getSelectedRow(); }
    public String getSelectedId() {
        int row = getSelectedRowIndex();
        return row != -1 ? tblPemesanan.getValueAt(row, 0).toString() : null;
    }
    public void addAddListener(ActionListener l) { btnAdd.addActionListener(l); }
    public void addUpdateListener(ActionListener l) { btnUpdate.addActionListener(l); }
    public void addDeleteListener(ActionListener l) { btnDelete.addActionListener(l); }
    public void addClearListener(ActionListener l) { btnClear.addActionListener(l); }
    public void addSearchListener(ActionListener l) { btnSearch.addActionListener(l); }
    public void addRefreshListener(ActionListener l) { btnRefresh.addActionListener(l); }
    public void addHitungListener(ActionListener l) { btnHitung.addActionListener(l); }
    public void addTableMouseListener(MouseListener l) { tblPemesanan.addMouseListener(l); }
}
