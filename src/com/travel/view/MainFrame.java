package com.travel.view;
import com.travel.controller.*;
import com.travel.dao.*;
import com.travel.model.Admin;
import com.travel.model.*;
import com.travel.util.UIHelper;
import com.travel.util.ReportManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
public class MainFrame extends JFrame {
    private Admin currentAdmin;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private PelangganPanel pelangganPanel;
    private PaketPanel paketPanel;
    private KendaraanPanel kendaraanPanel;
    private PemesananPanel pemesananPanel;
    private PembayaranPanel pembayaranPanel;
    private LaporanPanel laporanPanel;
    private JButton btnDashboard;
    private JButton btnPelanggan;
    private JButton btnPaket;
    private JButton btnKendaraan;
    private JButton btnPemesanan;
    private JButton btnPembayaran;
    private JButton btnLaporan;
    private JButton btnLogout;
    private JButton activeButton = null;
    public MainFrame(Admin admin) {
        this.currentAdmin = admin;
        setTitle("Travel Management System - " + admin.getNamaLengkap());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 600));
        initComponents();
        initControllers();
    }
    private void initComponents() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBackground(new Color(30, 30, 30));
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        JLabel lblTitle = new JLabel("TRAVEL MGMT", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(UIHelper.COLOR_PRIMARY);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setMaximumSize(new Dimension(200, 40));
        sidebar.add(lblTitle);
        JLabel lblUser = new JLabel("Hi, " + currentAdmin.getNamaLengkap(), JLabel.CENTER);
        lblUser.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblUser.setForeground(new Color(160, 160, 160));
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblUser.setMaximumSize(new Dimension(200, 25));
        sidebar.add(lblUser);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        btnDashboard = createSidebarButton("Dashboard");
        btnPelanggan = createSidebarButton("Pelanggan");
        btnPaket = createSidebarButton("Paket Travel");
        btnKendaraan = createSidebarButton("Kendaraan");
        btnPemesanan = createSidebarButton("Pemesanan");
        btnPembayaran = createSidebarButton("Pembayaran");
        btnLaporan = createSidebarButton("Laporan");
        btnLogout = createSidebarButton("Logout");
        btnLogout.setForeground(UIHelper.COLOR_ACCENT);
        sidebar.add(btnDashboard);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        sidebar.add(btnPelanggan);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        sidebar.add(btnPaket);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        sidebar.add(btnKendaraan);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        sidebar.add(btnPemesanan);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        sidebar.add(btnPembayaran);
        sidebar.add(Box.createRigidArea(new Dimension(0, 5)));
        sidebar.add(btnLaporan);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);
        add(sidebar, BorderLayout.WEST);
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        JPanel dashboardPanel = createDashboardPanel();
        pelangganPanel = new PelangganPanel();
        paketPanel = new PaketPanel();
        kendaraanPanel = new KendaraanPanel();
        pemesananPanel = new PemesananPanel();
        pembayaranPanel = new PembayaranPanel();
        laporanPanel = new LaporanPanel();
        contentPanel.add(dashboardPanel, "Dashboard");
        contentPanel.add(pelangganPanel, "Pelanggan");
        contentPanel.add(paketPanel, "Paket Travel");
        contentPanel.add(kendaraanPanel, "Kendaraan");
        contentPanel.add(pemesananPanel, "Pemesanan");
        contentPanel.add(pembayaranPanel, "Pembayaran");
        contentPanel.add(laporanPanel, "Laporan");
        add(contentPanel, BorderLayout.CENTER);
        btnDashboard.addActionListener(e -> switchCard("Dashboard", btnDashboard));
        btnPelanggan.addActionListener(e -> switchCard("Pelanggan", btnPelanggan));
        btnPaket.addActionListener(e -> switchCard("Paket Travel", btnPaket));
        btnKendaraan.addActionListener(e -> switchCard("Kendaraan", btnKendaraan));
        btnPemesanan.addActionListener(e -> switchCard("Pemesanan", btnPemesanan));
        btnPembayaran.addActionListener(e -> switchCard("Pembayaran", btnPembayaran));
        btnLaporan.addActionListener(e -> switchCard("Laporan", btnLaporan));
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        switchCard("Dashboard", btnDashboard);
    }
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JLabel lblWelcome = new JLabel("Selamat Datang di Aplikasi Manajemen Travel", JLabel.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblWelcome.setForeground(UIHelper.COLOR_PRIMARY);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3; gbc.weighty = 0;
        panel.add(lblWelcome, gbc);
        JLabel lblSub = new JLabel("Kelola data pelanggan, paket travel, kendaraan, pemesanan, dan pembayaran.", JLabel.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy = 1;
        panel.add(lblSub, gbc);
        gbc.gridwidth = 1;
        gbc.weighty = 1.0;
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createInfoCard("Pelanggan", "Kelola data pelanggan travel", new Color(0, 150, 136)), gbc);
        gbc.gridx = 1;
        panel.add(createInfoCard("Paket Travel", "Kelola paket wisata & tujuan", new Color(233, 30, 99)), gbc);
        gbc.gridx = 2;
        panel.add(createInfoCard("Kendaraan", "Kelola armada kendaraan travel", new Color(63, 81, 181)), gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createInfoCard("Pemesanan", "Proses pemesanan paket travel", new Color(255, 152, 0)), gbc);
        gbc.gridx = 1;
        panel.add(createInfoCard("Pembayaran", "Kelola transaksi pembayaran", new Color(76, 175, 80)), gbc);
        gbc.gridx = 2;
        panel.add(createInfoCard("Laporan", "Cetak laporan data travel", new Color(156, 39, 176)), gbc);
        return panel;
    }
    private JPanel createInfoCard(String title, String desc, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setBackground(new Color(40, 40, 40));
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(accentColor);
        JLabel lblDesc = new JLabel("<html>" + desc + "</html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(Color.LIGHT_GRAY);
        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblDesc, BorderLayout.CENTER);
        return card;
    }
    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(30, 30, 30));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (btn != activeButton) {
                    btn.setBackground(new Color(50, 50, 50));
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (btn != activeButton) {
                    btn.setBackground(new Color(30, 30, 30));
                }
            }
        });
        return btn;
    }
    private void switchCard(String name, JButton btn) {
        cardLayout.show(contentPanel, name);
        if (activeButton != null) {
            activeButton.setBackground(new Color(30, 30, 30));
            activeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }
        btn.setBackground(UIHelper.COLOR_PRIMARY_DARK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        activeButton = btn;
    }
    private void initControllers() {
        new PelangganController(pelangganPanel, new PelangganDAO());
        new PaketController(paketPanel, new PaketDAO());
        new KendaraanController(kendaraanPanel, new KendaraanDAO());
        new PemesananController(pemesananPanel, new PemesananDAO(), new PelangganDAO(), new PaketDAO());
        new PembayaranController(pembayaranPanel, new PembayaranDAO(), new PemesananDAO());
        initLaporanController();
    }
    private void initLaporanController() {
        laporanPanel.addTampilkanListener(e -> {
            String jenis = laporanPanel.getJenisLaporan();
            DefaultTableModel model = laporanPanel.getTableModel();
            model.setRowCount(0);
            model.setColumnCount(0);
            NumberFormat rupiah = NumberFormat.getCurrencyInstance(Locale.of("id", "ID"));
            try {
                switch (jenis) {
                    case "Laporan Pelanggan":
                        model.addColumn("ID Pelanggan");
                        model.addColumn("Nama Pelanggan");
                        model.addColumn("Alamat");
                        model.addColumn("No HP");
                        model.addColumn("Email");
                        for (Pelanggan p : new PelangganDAO().getAll()) {
                            model.addRow(new Object[]{p.getIdPelanggan(), p.getNamaPelanggan(), p.getAlamat(), p.getNoHp(), p.getEmail()});
                        }
                        break;
                    case "Laporan Paket Travel":
                        model.addColumn("ID Paket");
                        model.addColumn("Nama Paket");
                        model.addColumn("Tujuan");
                        model.addColumn("Harga");
                        model.addColumn("Durasi (Hari)");
                        for (Paket p : new PaketDAO().getAll()) {
                            model.addRow(new Object[]{p.getIdPaket(), p.getNamaPaket(), p.getTujuan(), rupiah.format(p.getHarga()), p.getDurasiHari()});
                        }
                        break;
                    case "Laporan Pemesanan":
                        model.addColumn("ID Pemesanan");
                        model.addColumn("Pelanggan");
                        model.addColumn("Paket");
                        model.addColumn("Tanggal Pesan");
                        model.addColumn("Jumlah Peserta");
                        model.addColumn("Total Bayar");
                        for (com.travel.model.Pemesanan p : new PemesananDAO().getAll()) {
                            model.addRow(new Object[]{p.getIdPemesanan(), p.getNamaPelanggan(), p.getNamaPaket(), p.getTanggalPesan(), p.getJumlahPeserta(), rupiah.format(p.getTotalBayar())});
                        }
                        break;
                    case "Laporan Pembayaran":
                        model.addColumn("ID Pembayaran");
                        model.addColumn("ID Pemesanan");
                        model.addColumn("Pelanggan");
                        model.addColumn("Tanggal Bayar");
                        model.addColumn("Metode Bayar");
                        model.addColumn("Jumlah Bayar");
                        model.addColumn("Status");
                        for (com.travel.model.Pembayaran p : new PembayaranDAO().getAll()) {
                            model.addRow(new Object[]{p.getIdPembayaran(), p.getIdPemesanan(), p.getNamaPelanggan(), p.getTanggalBayar(), p.getMetodeBayar(), rupiah.format(p.getJumlahBayar()), p.getStatusPembayaran()});
                        }
                        break;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal memuat laporan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        laporanPanel.addCetakListener(e -> {
            String jenis = laporanPanel.getJenisLaporan();
            ReportManager.printTable(laporanPanel.getTblLaporan(), jenis);
        });
    }
}
