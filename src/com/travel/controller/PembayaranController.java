package com.travel.controller;
import com.travel.dao.PembayaranDAO;
import com.travel.dao.PemesananDAO;
import com.travel.model.Pembayaran;
import com.travel.view.PembayaranPanel;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
public class PembayaranController {
    private PembayaranPanel view;
    private PembayaranDAO pembayaranDAO;
    private PemesananDAO pemesananDAO;
    public PembayaranController(PembayaranPanel view, PembayaranDAO pembayaranDAO, PemesananDAO pemesananDAO) {
        this.view = view;
        this.pembayaranDAO = pembayaranDAO;
        this.pemesananDAO = pemesananDAO;
        this.view.addAddListener(e -> addAction());
        this.view.addUpdateListener(e -> updateAction());
        this.view.addDeleteListener(e -> deleteAction());
        this.view.addClearListener(e -> view.clearForm());
        this.view.addSearchListener(e -> searchAction());
        this.view.addRefreshListener(e -> refreshTable());
        this.view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { tableClickAction(); }
        });
        loadCombos();
        refreshTable();
    }
    private void loadCombos() {
        try {
            view.loadPemesananCombo(pemesananDAO.getAll());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal memuat data pemesanan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void refreshTable() {
        try {
            view.populateTable(pembayaranDAO.getAll());
            view.clearForm();
            loadCombos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengambil data pembayaran: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addAction() {
        String id = view.getIdPembayaran();
        String idPemesanan = view.getSelectedPemesananId();
        String tgl = view.getTanggal();
        String metode = view.getMetodeBayar();
        String jmlStr = view.getJumlahBayar();
        String status = view.getStatusPembayaran();
        if (id.isEmpty() || idPemesanan.isEmpty() || tgl.isEmpty() || jmlStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Pembayaran p = new Pembayaran(id, idPemesanan, Date.valueOf(tgl), metode, Double.parseDouble(jmlStr), status);
            pembayaranDAO.insert(p);
            JOptionPane.showMessageDialog(view, "Pembayaran berhasil ditambahkan!");
            refreshTable();
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(view, "Format tanggal salah! Gunakan format yyyy-MM-dd", "Input Salah", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal menambahkan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateAction() {
        String id = view.getIdPembayaran();
        String idPemesanan = view.getSelectedPemesananId();
        String tgl = view.getTanggal();
        String metode = view.getMetodeBayar();
        String jmlStr = view.getJumlahBayar();
        String status = view.getStatusPembayaran();
        if (id.isEmpty() || idPemesanan.isEmpty() || tgl.isEmpty() || jmlStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Pembayaran p = new Pembayaran(id, idPemesanan, Date.valueOf(tgl), metode, Double.parseDouble(jmlStr), status);
            pembayaranDAO.update(p);
            JOptionPane.showMessageDialog(view, "Pembayaran berhasil diubah!");
            refreshTable();
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(view, "Format tanggal salah! Gunakan format yyyy-MM-dd", "Input Salah", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteAction() {
        String id = view.getIdPembayaran();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus pembayaran " + id + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                pembayaranDAO.delete(id);
                JOptionPane.showMessageDialog(view, "Pembayaran berhasil dihapus!");
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
            view.populateTable(pembayaranDAO.search(keyword));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Pencarian gagal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tableClickAction() {
        String id = view.getSelectedId();
        if (id != null) {
            try {
                Pembayaran p = pembayaranDAO.getById(id);
                if (p != null) {
                    view.setIdPembayaran(p.getIdPembayaran());
                    view.setSelectedPemesananById(p.getIdPemesanan());
                    view.setTanggal(p.getTanggalBayar().toString());
                    view.setMetodeBayar(p.getMetodeBayar());
                    view.setJumlahBayar(String.valueOf((long) p.getJumlahBayar()));
                    view.setStatusPembayaran(p.getStatusPembayaran());
                    view.setEditMode(true);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal mengambil detail pembayaran: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
