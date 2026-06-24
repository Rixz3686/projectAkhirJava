package com.travel.controller;
import com.travel.dao.KendaraanDAO;
import com.travel.model.Kendaraan;
import com.travel.view.KendaraanPanel;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
public class KendaraanController {
    private KendaraanPanel view;
    private KendaraanDAO dao;
    public KendaraanController(KendaraanPanel view, KendaraanDAO dao) {
        this.view = view;
        this.dao = dao;
        this.view.addAddListener(e -> addAction());
        this.view.addUpdateListener(e -> updateAction());
        this.view.addDeleteListener(e -> deleteAction());
        this.view.addClearListener(e -> view.clearForm());
        this.view.addSearchListener(e -> searchAction());
        this.view.addRefreshListener(e -> refreshTable());
        this.view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableClickAction();
            }
        });
        refreshTable();
    }
    private void refreshTable() {
        try {
            List<Kendaraan> list = dao.getAll();
            view.populateTable(list);
            view.clearForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengambil data kendaraan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void addAction() {
        String id = view.getIdKendaraan();
        String nama = view.getNamaKendaraan();
        String jenis = view.getJenis();
        String kapStr = view.getKapasitas();
        String noPol = view.getNoPolisi();
        String status = view.getStatus();
        if (id.isEmpty() || nama.isEmpty() || kapStr.isEmpty() || noPol.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            if (dao.getById(id) != null) {
                JOptionPane.showMessageDialog(view, "ID Kendaraan '" + id + "' sudah terdaftar!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int kap = Integer.parseInt(kapStr);
            Kendaraan k = new Kendaraan(id, nama, jenis, kap, noPol, status);
            dao.insert(k);
            JOptionPane.showMessageDialog(view, "Data kendaraan berhasil ditambahkan!");
            refreshTable();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(view, "Kapasitas harus berupa angka!", "Input Salah", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal menambahkan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateAction() {
        String id = view.getIdKendaraan();
        String nama = view.getNamaKendaraan();
        String jenis = view.getJenis();
        String kapStr = view.getKapasitas();
        String noPol = view.getNoPolisi();
        String status = view.getStatus();
        if (id.isEmpty() || nama.isEmpty() || kapStr.isEmpty() || noPol.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int kap = Integer.parseInt(kapStr);
            Kendaraan k = new Kendaraan(id, nama, jenis, kap, noPol, status);
            dao.update(k);
            JOptionPane.showMessageDialog(view, "Data kendaraan berhasil diubah!");
            refreshTable();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(view, "Kapasitas harus berupa angka!", "Input Salah", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteAction() {
        String id = view.getIdKendaraan();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus kendaraan " + id + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                dao.delete(id);
                JOptionPane.showMessageDialog(view, "Data kendaraan berhasil dihapus!");
                refreshTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void searchAction() {
        String keyword = view.getSearchKeyword();
        if (keyword.isEmpty()) {
            refreshTable();
            return;
        }
        try {
            List<Kendaraan> list = dao.search(keyword);
            view.populateTable(list);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Pencarian gagal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tableClickAction() {
        String id = view.getSelectedId();
        if (id != null) {
            try {
                Kendaraan k = dao.getById(id);
                if (k != null) {
                    view.setIdKendaraan(k.getIdKendaraan());
                    view.setNamaKendaraan(k.getNamaKendaraan());
                    view.setJenis(k.getJenis());
                    view.setKapasitas(String.valueOf(k.getKapasitas()));
                    view.setNoPolisi(k.getNomorPolisi());
                    view.setStatus(k.getStatus());
                    view.setEditMode(true);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal mengambil detail kendaraan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
