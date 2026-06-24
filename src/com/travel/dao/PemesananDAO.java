package com.travel.dao;
import com.travel.config.DatabaseConnection;
import com.travel.model.Pemesanan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PemesananDAO {
    public void insert(Pemesanan p) throws SQLException {
        String sql = "INSERT INTO pemesanan (id_pemesanan, id_pelanggan, id_paket, tanggal_pesan, jumlah_peserta, total_bayar) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPemesanan());
            ps.setString(2, p.getIdPelanggan());
            ps.setString(3, p.getIdPaket());
            ps.setDate(4, p.getTanggalPesan());
            ps.setInt(5, p.getJumlahPeserta());
            ps.setDouble(6, p.getTotalBayar());
            ps.executeUpdate();
        }
    }
    public void update(Pemesanan p) throws SQLException {
        String sql = "UPDATE pemesanan SET id_pelanggan = ?, id_paket = ?, tanggal_pesan = ?, jumlah_peserta = ?, total_bayar = ? WHERE id_pemesanan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPelanggan());
            ps.setString(2, p.getIdPaket());
            ps.setDate(3, p.getTanggalPesan());
            ps.setInt(4, p.getJumlahPeserta());
            ps.setDouble(5, p.getTotalBayar());
            ps.setString(6, p.getIdPemesanan());
            ps.executeUpdate();
        }
    }
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM pemesanan WHERE id_pemesanan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
    public List<Pemesanan> getAll() throws SQLException {
        List<Pemesanan> list = new ArrayList<>();
        String sql = "SELECT p.*, pl.nama_pelanggan, pk.nama_paket " +
                     "FROM pemesanan p " +
                     "JOIN pelanggan pl ON p.id_pelanggan = pl.id_pelanggan " +
                     "JOIN paket pk ON p.id_paket = pk.id_paket " +
                     "ORDER BY p.id_pemesanan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pemesanan p = new Pemesanan(
                    rs.getString("id_pemesanan"),
                    rs.getString("id_pelanggan"),
                    rs.getString("id_paket"),
                    rs.getDate("tanggal_pesan"),
                    rs.getInt("jumlah_peserta"),
                    rs.getDouble("total_bayar")
                );
                p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                p.setNamaPaket(rs.getString("nama_paket"));
                list.add(p);
            }
        }
        return list;
    }
    public List<Pemesanan> search(String keyword) throws SQLException {
        List<Pemesanan> list = new ArrayList<>();
        String sql = "SELECT p.*, pl.nama_pelanggan, pk.nama_paket " +
                     "FROM pemesanan p " +
                     "JOIN pelanggan pl ON p.id_pelanggan = pl.id_pelanggan " +
                     "JOIN paket pk ON p.id_paket = pk.id_paket " +
                     "WHERE p.id_pemesanan LIKE ? OR pl.nama_pelanggan LIKE ? OR pk.nama_paket LIKE ? " +
                     "ORDER BY p.id_pemesanan";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String val = "%" + keyword + "%";
            ps.setString(1, val);
            ps.setString(2, val);
            ps.setString(3, val);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pemesanan p = new Pemesanan(
                        rs.getString("id_pemesanan"),
                        rs.getString("id_pelanggan"),
                        rs.getString("id_paket"),
                        rs.getDate("tanggal_pesan"),
                        rs.getInt("jumlah_peserta"),
                        rs.getDouble("total_bayar")
                    );
                    p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                    p.setNamaPaket(rs.getString("nama_paket"));
                    list.add(p);
                }
            }
        }
        return list;
    }
    public Pemesanan getById(String id) throws SQLException {
        String sql = "SELECT p.*, pl.nama_pelanggan, pk.nama_paket " +
                     "FROM pemesanan p " +
                     "JOIN pelanggan pl ON p.id_pelanggan = pl.id_pelanggan " +
                     "JOIN paket pk ON p.id_paket = pk.id_paket " +
                     "WHERE p.id_pemesanan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pemesanan p = new Pemesanan(
                        rs.getString("id_pemesanan"),
                        rs.getString("id_pelanggan"),
                        rs.getString("id_paket"),
                        rs.getDate("tanggal_pesan"),
                        rs.getInt("jumlah_peserta"),
                        rs.getDouble("total_bayar")
                    );
                    p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                    p.setNamaPaket(rs.getString("nama_paket"));
                    return p;
                }
            }
        }
        return null;
    }
}
