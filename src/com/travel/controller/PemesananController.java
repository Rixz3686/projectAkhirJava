package com.travel.controller;
import com.travel.dao.PaketDAO;
import com.travel.dao.PelangganDAO;
import com.travel.dao.PemesananDAO;
import com.travel.model.Paket;
import com.travel.model.Pelanggan;
import com.travel.model.Pemesanan;
import com.travel.view.PemesananPanel;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
public class PemesananController {
    private PemesananPanel view;
    private PemesananDAO pemesananDAO;
    private PelangganDAO pelangganDAO;
    private PaketDAO paketDAO;
    public PemesananController(PemesananPanel view, PemesananDAO pemesananDAO, PelangganDAO pelangganDAO, PaketDAO paketDAO) {
        this.view = view;
        this.pemesananDAO = pemesananDAO;
        this.pelangganDAO = pelangganDAO;
        this.paketDAO = paketDAO;
        this.view.addAddListener(e -> addAction());
        this.view.addUpdateListener(e -> updateAction());
        this.view.addDeleteListener(e -> deleteAction());
        this.view.addClearListener(e -> view.clearForm());
        this.view.addSearchListener(e -> searchAction());
        this.view.addRefreshListener(e -> refreshTable());
        this.view.addHitungListener(e -> hitungTotal());
        this.view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { tableClickAction(); }
        });
        loadCombos();
        refreshTable();
    }
    private void loadCombos() {
        try {
            view.loadPelangganCombo(pelangganDAO.getAll());
            view.loadPaketCombo(paketDAO.getAll());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal memuat data combo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void refreshTable() {
        try {
            view.populateTable(pemesananDAO.getAll());
            view.clearForm();
            loadCombos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengambil data pemesanan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void hitungTotal() {
        Paket paket = view.getSelectedPaket();
        String jmlStr = view.getJumlahPeserta();
        if (paket == null || jmlStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih paket travel dan isi jumlah peserta terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int jml = Integer.parseInt(jmlStr);
            double total = paket.getHarga() * jml;
            view.setTotalBayar(String.valueOf((long) total));
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(view, "Jumlah peserta harus berupa angka!", "Input Salah", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addAction() {
        String id = view.getIdPemesanan();
        Pelanggan pelanggan = view.getSelectedPelanggan();
        Paket paket = view.getSelectedPaket();
        String tgl = view.getTanggal();
        String jmlStr = view.getJumlahPeserta();
        String totalStr = view.getTotalBayar();
        if (id.isEmpty() || pelanggan == null || paket == null || tgl.isEmpty() || jmlStr.isEmpty() || totalStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi! Tekan 'Hitung Total' terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Pemesanan p = new Pemesanan(id, pelanggan.getIdPelanggan(), paket.getIdPaket(),
                    Date.valueOf(tgl), Integer.parseInt(jmlStr), Double.parseDouble(totalStr));
            pemesananDAO.insert(p);
            JOptionPane.showMessageDialog(view, "Pemesanan berhasil ditambahkan!");
            refreshTable();
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(view, "Format tanggal salah! Gunakan format yyyy-MM-dd", "Input Salah", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal menambahkan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateAction() {
        String id = view.getIdPemesanan();
        Pelanggan pelanggan = view.getSelectedPelanggan();
        Paket paket = view.getSelectedPaket();
        String tgl = view.getTanggal();
        String jmlStr = view.getJumlahPeserta();
        String totalStr = view.getTotalBayar();
        if (id.isEmpty() || pelanggan == null || paket == null || tgl.isEmpty() || jmlStr.isEmpty() || totalStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Pemesanan p = new Pemesanan(id, pelanggan.getIdPelanggan(), paket.getIdPaket(),
                    Date.valueOf(tgl), Integer.parseInt(jmlStr), Double.parseDouble(totalStr));
            pemesananDAO.update(p);
            JOptionPane.showMessageDialog(view, "Pemesanan berhasil diubah!");
            refreshTable();
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(view, "Format tanggal salah! Gunakan format yyyy-MM-dd", "Input Salah", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteAction() {
        String id = view.getIdPemesanan();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus pemesanan " + id + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                pemesananDAO.delete(id);
                JOptionPane.showMessageDialog(view, "Pemesanan berhasil dihapus!");
                refreshTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void searchAction() {
        String keyword = view.getSearchKeyword();
        if (keyword.isEmpty()) { refreshTable(); return; }
        try {
            view.populateTable(pemesananDAO.search(keyword));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Pencarian gagal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tableClickAction() {
        String id = view.getSelectedId();
        if (id != null) {
            try {
                Pemesanan p = pemesananDAO.getById(id);
                if (p != null) {
                    view.setIdPemesanan(p.getIdPemesanan());
                    view.setSelectedPelanggan(p.getIdPelanggan());
                    view.setSelectedPaket(p.getIdPaket());
                    view.setTanggal(p.getTanggalPesan().toString());
                    view.setJumlahPeserta(String.valueOf(p.getJumlahPeserta()));
                    view.setTotalBayar(String.valueOf((long) p.getTotalBayar()));
                    view.setEditMode(true);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal mengambil detail pemesanan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
