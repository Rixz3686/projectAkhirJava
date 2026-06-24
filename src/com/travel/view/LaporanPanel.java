package com.travel.view;
import com.travel.util.UIHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
public class LaporanPanel extends JPanel {
    private JComboBox<String> cbJenisLaporan;
    private JButton btnTampilkan;
    private JButton btnCetak;
    private JTable tblLaporan;
    private DefaultTableModel tableModel;
    public LaporanPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        initComponents();
    }
    private void initComponents() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Pilih Jenis Laporan"));
        String[] jenisLaporan = {"Laporan Pelanggan", "Laporan Paket Travel", "Laporan Pemesanan", "Laporan Pembayaran"};
        cbJenisLaporan = new JComboBox<>(jenisLaporan);
        cbJenisLaporan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnTampilkan = UIHelper.createModernButton("Tampilkan", true);
        btnCetak = UIHelper.createModernButton("Cetak / Print", false);
        topPanel.add(new JLabel("Jenis Laporan:"));
        topPanel.add(cbJenisLaporan);
        topPanel.add(btnTampilkan);
        topPanel.add(btnCetak);
        add(topPanel, BorderLayout.NORTH);
        tableModel = new DefaultTableModel();
        tblLaporan = new JTable(tableModel);
        UIHelper.styleTable(tblLaporan);
        JScrollPane scrollPane = new JScrollPane(tblLaporan);
        add(scrollPane, BorderLayout.CENTER);
    }
    public String getJenisLaporan() {
        return cbJenisLaporan.getSelectedItem().toString();
    }
    public JTable getTblLaporan() {
        return tblLaporan;
    }
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    public void addTampilkanListener(ActionListener l) { btnTampilkan.addActionListener(l); }
    public void addCetakListener(ActionListener l) { btnCetak.addActionListener(l); }
}
