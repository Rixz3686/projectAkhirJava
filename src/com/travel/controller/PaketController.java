package com.travel.controller;
import com.travel.dao.PaketDAO;
import com.travel.model.Paket;
import com.travel.view.PaketPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
public class PaketController {
    private PaketPanel view;
    private PaketDAO dao;
    public PaketController(PaketPanel view, PaketDAO dao) {
        this.view = view;
        this.dao = dao;
        this.view.addAddListener(new AddListener());
        this.view.addUpdateListener(new UpdateListener());
        this.view.addDeleteListener(new DeleteListener());
        this.view.addClearListener(e -> view.clearForm());
        this.view.addSearchListener(new SearchListener());
        this.view.addRefreshListener(e -> refreshTable());
        this.view.addTableMouseListener(new TableMouseListener());
        refreshTable();
    }
    private void refreshTable() {
        try {
            List<Paket> list = dao.getAll();
            view.populateTable(list);
            view.clearForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengambil data paket: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getIdPaket();
            String nama = view.getNamaPaket();
            String tujuan = view.getTujuan();
            String hargaStr = view.getHarga();
            String durasiStr = view.getDurasi();
            String kuotaStr = view.getKuota();
            if (id.isEmpty() || nama.isEmpty() || tujuan.isEmpty() || hargaStr.isEmpty() || durasiStr.isEmpty() || kuotaStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                if (dao.getById(id) != null) {
                    JOptionPane.showMessageDialog(view, "Kode Paket '" + id + "' sudah terdaftar!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                double harga = Double.parseDouble(hargaStr);
                int durasi = Integer.parseInt(durasiStr);
                int kuota = Integer.parseInt(kuotaStr);
                if (harga <= 0 || durasi <= 0 || kuota <= 0) {
                    JOptionPane.showMessageDialog(view, "Harga, durasi, dan kuota harus lebih dari 0!", "Input Salah", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Paket p = new Paket(id, nama, tujuan, harga, durasi, kuota);
                dao.insert(p);
                JOptionPane.showMessageDialog(view, "Data paket travel berhasil ditambahkan!");
                refreshTable();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(view, "Harga, durasi, dan kuota harus berupa angka!", "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal menambahkan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getIdPaket();
            String nama = view.getNamaPaket();
            String tujuan = view.getTujuan();
            String hargaStr = view.getHarga();
            String durasiStr = view.getDurasi();
            String kuotaStr = view.getKuota();
            if (id.isEmpty() || nama.isEmpty() || tujuan.isEmpty() || hargaStr.isEmpty() || durasiStr.isEmpty() || kuotaStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                double harga = Double.parseDouble(hargaStr);
                int durasi = Integer.parseInt(durasiStr);
                int kuota = Integer.parseInt(kuotaStr);
                if (harga <= 0 || durasi <= 0 || kuota <= 0) {
                    JOptionPane.showMessageDialog(view, "Harga, durasi, dan kuota harus lebih dari 0!", "Input Salah", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Paket p = new Paket(id, nama, tujuan, harga, durasi, kuota);
                dao.update(p);
                JOptionPane.showMessageDialog(view, "Data paket travel berhasil diubah!");
                refreshTable();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(view, "Harga, durasi, dan kuota harus berupa angka!", "Input Salah", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getIdPaket();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus paket " + id + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    dao.delete(id);
                    JOptionPane.showMessageDialog(view, "Data paket travel berhasil dihapus!");
                    refreshTable();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Gagal menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = view.getSearchKeyword();
            if (keyword.isEmpty()) {
                refreshTable();
                return;
            }
            try {
                List<Paket> list = dao.search(keyword);
                view.populateTable(list);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Pencarian gagal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    class TableMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            String id = view.getSelectedId();
            if (id != null) {
                try {
                    Paket p = dao.getById(id);
                    if (p != null) {
                        view.setIdPaket(p.getIdPaket());
                        view.setNamaPaket(p.getNamaPaket());
                        view.setTujuan(p.getTujuan());
                        view.setHarga(String.valueOf((long)p.getHarga()));
                        view.setDurasi(String.valueOf(p.getDurasiHari()));
                        view.setKuota(String.valueOf(p.getKuota()));
                        view.setEditMode(true);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Gagal mengambil detail paket: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
