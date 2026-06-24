package com.travel.dao;
import com.travel.config.DatabaseConnection;
import com.travel.model.Pembayaran;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PembayaranDAO {
    public void insert(Pembayaran p) throws SQLException {
        String sql = "INSERT INTO pembayaran (id_pembayaran, id_pemesanan, tanggal_bayar, metode_bayar, jumlah_bayar, status_pembayaran) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPembayaran());
            ps.setString(2, p.getIdPemesanan());
            ps.setDate(3, p.getTanggalBayar());
            ps.setString(4, p.getMetodeBayar());
            ps.setDouble(5, p.getJumlahBayar());
            ps.setString(6, p.getStatusPembayaran());
            ps.executeUpdate();
        }
    }
    public void update(Pembayaran p) throws SQLException {
        String sql = "UPDATE pembayaran SET id_pemesanan = ?, tanggal_bayar = ?, metode_bayar = ?, jumlah_bayar = ?, status_pembayaran = ? WHERE id_pembayaran = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPemesanan());
            ps.setDate(2, p.getTanggalBayar());
            ps.setString(3, p.getMetodeBayar());
            ps.setDouble(4, p.getJumlahBayar());
            ps.setString(5, p.getStatusPembayaran());
            ps.setString(6, p.getIdPembayaran());
            ps.executeUpdate();
        }
    }
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM pembayaran WHERE id_pembayaran = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
    public List<Pembayaran> getAll() throws SQLException {
        List<Pembayaran> list = new ArrayList<>();
        String sql = "SELECT p.*, pl.nama_pelanggan " +
                     "FROM pembayaran p " +
                     "JOIN pemesanan pm ON p.id_pemesanan = pm.id_pemesanan " +
                     "JOIN pelanggan pl ON pm.id_pelanggan = pl.id_pelanggan " +
                     "ORDER BY p.id_pembayaran";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pembayaran p = new Pembayaran(
                    rs.getString("id_pembayaran"),
                    rs.getString("id_pemesanan"),
                    rs.getDate("tanggal_bayar"),
                    rs.getString("metode_bayar"),
                    rs.getDouble("jumlah_bayar"),
                    rs.getString("status_pembayaran")
                );
                p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                list.add(p);
            }
        }
        return list;
    }
    public List<Pembayaran> search(String keyword) throws SQLException {
        List<Pembayaran> list = new ArrayList<>();
        String sql = "SELECT p.*, pl.nama_pelanggan " +
                     "FROM pembayaran p " +
                     "JOIN pemesanan pm ON p.id_pemesanan = pm.id_pemesanan " +
                     "JOIN pelanggan pl ON pm.id_pelanggan = pl.id_pelanggan " +
                     "WHERE p.id_pembayaran LIKE ? OR p.id_pemesanan LIKE ? OR pl.nama_pelanggan LIKE ? " +
                     "ORDER BY p.id_pembayaran";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String val = "%" + keyword + "%";
            ps.setString(1, val);
            ps.setString(2, val);
            ps.setString(3, val);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pembayaran p = new Pembayaran(
                        rs.getString("id_pembayaran"),
                        rs.getString("id_pemesanan"),
                        rs.getDate("tanggal_bayar"),
                        rs.getString("metode_bayar"),
                        rs.getDouble("jumlah_bayar"),
                        rs.getString("status_pembayaran")
                    );
                    p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                    list.add(p);
                }
            }
        }
        return list;
    }
    public Pembayaran getById(String id) throws SQLException {
        String sql = "SELECT p.*, pl.nama_pelanggan " +
                     "FROM pembayaran p " +
                     "JOIN pemesanan pm ON p.id_pemesanan = pm.id_pemesanan " +
                     "JOIN pelanggan pl ON pm.id_pelanggan = pl.id_pelanggan " +
                     "WHERE p.id_pembayaran = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pembayaran p = new Pembayaran(
                        rs.getString("id_pembayaran"),
                        rs.getString("id_pemesanan"),
                        rs.getDate("tanggal_bayar"),
                        rs.getString("metode_bayar"),
                        rs.getDouble("jumlah_bayar"),
                        rs.getString("status_pembayaran")
                    );
                    p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                    return p;
                }
            }
        }
        return null;
    }
}
