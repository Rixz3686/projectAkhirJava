package com.travel.view;
import com.travel.model.Pelanggan;
import com.travel.util.UIHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
public class PelangganPanel extends JPanel {
    private JTextField txtId;
    private JTextField txtNama;
    private JTextArea txtAlamat;
    private JTextField txtNoHp;
    private JTextField txtEmail;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnRefresh;
    private JTable tblPelanggan;
    private DefaultTableModel tableModel;
    public PelangganPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
    }
    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Form Pelanggan"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Pelanggan:"), gbc);
        txtId = new JTextField(15);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Nama Pelanggan:"), gbc);
        txtNama = new JTextField(15);
        txtNama.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtNama, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Alamat:"), gbc);
        txtAlamat = new JTextArea(4, 15);
        txtAlamat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        JScrollPane scrollAlamat = new JScrollPane(txtAlamat);
        gbc.gridx = 1;
        formPanel.add(scrollAlamat, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("No HP:"), gbc);
        txtNoHp = new JTextField(15);
        txtNoHp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtNoHp, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Email:"), gbc);
        txtEmail = new JTextField(15);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc);
        JPanel actionPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        btnAdd = UIHelper.createModernButton("Tambah", true);
        btnUpdate = UIHelper.createModernButton("Ubah", false);
        btnDelete = UIHelper.createModernButton("Hapus", false);
        btnClear = UIHelper.createModernButton("Bersihkan", false);
        actionPanel.add(btnAdd);
        actionPanel.add(btnUpdate);
        actionPanel.add(btnDelete);
        actionPanel.add(btnClear);
        gbc.gridx = 0; gbc.gridy = 5;
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
        String[] columns = {"ID Pelanggan", "Nama Pelanggan", "Alamat", "No HP", "Email"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPelanggan = new JTable(tableModel);
        UIHelper.styleTable(tblPelanggan);
        JScrollPane scrollTable = new JScrollPane(tblPelanggan);
        tablePanel.add(scrollTable, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);
    }
    public String getIdPelanggan() {
        return txtId.getText().trim();
    }
    public void setIdPelanggan(String id) {
        txtId.setText(id);
    }
    public void setEditMode(boolean edit) {
        txtId.setEditable(!edit);
    }
    public String getNamaPelanggan() {
        return txtNama.getText().trim();
    }
    public void setNamaPelanggan(String nama) {
        txtNama.setText(nama);
    }
    public String getAlamat() {
        return txtAlamat.getText().trim();
    }
    public void setAlamat(String alamat) {
        txtAlamat.setText(alamat);
    }
    public String getNoHp() {
        return txtNoHp.getText().trim();
    }
    public void setNoHp(String noHp) {
        txtNoHp.setText(noHp);
    }
    public String getEmail() {
        return txtEmail.getText().trim();
    }
    public void setEmail(String email) {
        txtEmail.setText(email);
    }
    public String getSearchKeyword() {
        return txtSearch.getText().trim();
    }
    public void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtId.setEditable(true);
    }
    public void populateTable(List<Pelanggan> list) {
        tableModel.setRowCount(0);
        for (Pelanggan p : list) {
            tableModel.addRow(new Object[]{
                p.getIdPelanggan(),
                p.getNamaPelanggan(),
                p.getAlamat(),
                p.getNoHp(),
                p.getEmail()
            });
        }
    }
    public int getSelectedRowIndex() {
        return tblPelanggan.getSelectedRow();
    }
    public String getSelectedId() {
        int row = getSelectedRowIndex();
        if (row != -1) {
            return tblPelanggan.getValueAt(row, 0).toString();
        }
        return null;
    }
    public void addAddListener(ActionListener l) { btnAdd.addActionListener(l); }
    public void addUpdateListener(ActionListener l) { btnUpdate.addActionListener(l); }
    public void addDeleteListener(ActionListener l) { btnDelete.addActionListener(l); }
    public void addClearListener(ActionListener l) { btnClear.addActionListener(l); }
    public void addSearchListener(ActionListener l) { btnSearch.addActionListener(l); }
    public void addRefreshListener(ActionListener l) { btnRefresh.addActionListener(l); }
    public void addTableMouseListener(MouseListener l) { tblPelanggan.addMouseListener(l); }
}
