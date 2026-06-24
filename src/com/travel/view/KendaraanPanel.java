package com.travel.view;
import com.travel.model.Kendaraan;
import com.travel.util.UIHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
public class KendaraanPanel extends JPanel {
    private JTextField txtId;
    private JTextField txtNama;
    private JComboBox<String> cbJenis;
    private JTextField txtKapasitas;
    private JTextField txtNoPolisi;
    private JComboBox<String> cbStatus;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnRefresh;
    private JTable tblKendaraan;
    private DefaultTableModel tableModel;
    public KendaraanPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
    }
    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Form Kendaraan"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Kendaraan:"), gbc);
        txtId = new JTextField(15);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Nama Armada:"), gbc);
        txtNama = new JTextField(15);
        txtNama.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtNama, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Jenis Kendaraan:"), gbc);
        String[] jenisList = {"Minibus", "Bus", "MPV", "Sedan", "SUV", "Minibus Luxury"};
        cbJenis = new JComboBox<>(jenisList);
        cbJenis.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(cbJenis, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Kapasitas (Seat):"), gbc);
        txtKapasitas = new JTextField(15);
        txtKapasitas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtKapasitas, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("No. Polisi:"), gbc);
        txtNoPolisi = new JTextField(15);
        txtNoPolisi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtNoPolisi, gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Status:"), gbc);
        String[] statusList = {"Tersedia", "Perawatan", "Sedang Jalan"};
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
        String[] columns = {"ID Kendaraan", "Nama Kendaraan", "Jenis", "Kapasitas", "Nomor Polisi", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblKendaraan = new JTable(tableModel);
        UIHelper.styleTable(tblKendaraan);
        JScrollPane scrollTable = new JScrollPane(tblKendaraan);
        tablePanel.add(scrollTable, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);
    }
    public String getIdKendaraan() { return txtId.getText().trim(); }
    public void setIdKendaraan(String id) { txtId.setText(id); }
    public void setEditMode(boolean edit) { txtId.setEditable(!edit); }
    public String getNamaKendaraan() { return txtNama.getText().trim(); }
    public void setNamaKendaraan(String nama) { txtNama.setText(nama); }
    public String getJenis() { return cbJenis.getSelectedItem().toString(); }
    public void setJenis(String jenis) { cbJenis.setSelectedItem(jenis); }
    public String getKapasitas() { return txtKapasitas.getText().trim(); }
    public void setKapasitas(String kap) { txtKapasitas.setText(kap); }
    public String getNoPolisi() { return txtNoPolisi.getText().trim(); }
    public void setNoPolisi(String noPol) { txtNoPolisi.setText(noPol); }
    public String getStatus() { return cbStatus.getSelectedItem().toString(); }
    public void setStatus(String status) { cbStatus.setSelectedItem(status); }
    public String getSearchKeyword() { return txtSearch.getText().trim(); }
    public void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        cbJenis.setSelectedIndex(0);
        txtKapasitas.setText("");
        txtNoPolisi.setText("");
        cbStatus.setSelectedIndex(0);
        txtId.setEditable(true);
    }
    public void populateTable(List<Kendaraan> list) {
        tableModel.setRowCount(0);
        for (Kendaraan k : list) {
            tableModel.addRow(new Object[]{
                k.getIdKendaraan(),
                k.getNamaKendaraan(),
                k.getJenis(),
                k.getKapasitas(),
                k.getNomorPolisi(),
                k.getStatus()
            });
        }
    }
    public int getSelectedRowIndex() { return tblKendaraan.getSelectedRow(); }
    public String getSelectedId() {
        int row = getSelectedRowIndex();
        if (row != -1) {
            return tblKendaraan.getValueAt(row, 0).toString();
        }
        return null;
    }
    public void addAddListener(ActionListener l) { btnAdd.addActionListener(l); }
    public void addUpdateListener(ActionListener l) { btnUpdate.addActionListener(l); }
    public void addDeleteListener(ActionListener l) { btnDelete.addActionListener(l); }
    public void addClearListener(ActionListener l) { btnClear.addActionListener(l); }
    public void addSearchListener(ActionListener l) { btnSearch.addActionListener(l); }
    public void addRefreshListener(ActionListener l) { btnRefresh.addActionListener(l); }
    public void addTableMouseListener(MouseListener l) { tblKendaraan.addMouseListener(l); }
}
