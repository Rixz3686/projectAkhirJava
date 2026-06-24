package com.travel.controller;
import com.travel.dao.PelangganDAO;
import com.travel.model.Pelanggan;
import com.travel.view.PelangganPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
public class PelangganController {
    private PelangganPanel view;
    private PelangganDAO dao;
    public PelangganController(PelangganPanel view, PelangganDAO dao) {
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
            List<Pelanggan> list = dao.getAll();
            view.populateTable(list);
            view.clearForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengambil data pelanggan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getIdPelanggan();
            String nama = view.getNamaPelanggan();
            String alamat = view.getAlamat();
            String noHp = view.getNoHp();
            String email = view.getEmail();
            if (id.isEmpty() || nama.isEmpty() || alamat.isEmpty() || noHp.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                if (dao.getById(id) != null) {
                    JOptionPane.showMessageDialog(view, "ID Pelanggan '" + id + "' sudah terdaftar!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Pelanggan p = new Pelanggan(id, nama, alamat, noHp, email);
                dao.insert(p);
                JOptionPane.showMessageDialog(view, "Data pelanggan berhasil ditambahkan!");
                refreshTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal menambahkan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getIdPelanggan();
            String nama = view.getNamaPelanggan();
            String alamat = view.getAlamat();
            String noHp = view.getNoHp();
            String email = view.getEmail();
            if (id.isEmpty() || nama.isEmpty() || alamat.isEmpty() || noHp.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Pelanggan p = new Pelanggan(id, nama, alamat, noHp, email);
                dao.update(p);
                JOptionPane.showMessageDialog(view, "Data pelanggan berhasil diubah!");
                refreshTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getIdPelanggan();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus pelanggan " + id + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    dao.delete(id);
                    JOptionPane.showMessageDialog(view, "Data pelanggan berhasil dihapus!");
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
                List<Pelanggan> list = dao.search(keyword);
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
                    Pelanggan p = dao.getById(id);
                    if (p != null) {
                        view.setIdPelanggan(p.getIdPelanggan());
                        view.setNamaPelanggan(p.getNamaPelanggan());
                        view.setAlamat(p.getAlamat());
                        view.setNoHp(p.getNoHp());
                        view.setEmail(p.getEmail());
                        view.setEditMode(true);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Gagal mengambil detail pelanggan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
