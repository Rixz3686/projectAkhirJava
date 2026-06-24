package com.travel.dao;
import com.travel.config.DatabaseConnection;
import com.travel.model.Kendaraan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class KendaraanDAO {
    public void insert(Kendaraan k) throws SQLException {
        String sql = "INSERT INTO kendaraan (id_kendaraan, nama_kendaraan, jenis, kapasitas, nomor_polisi, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getIdKendaraan());
            ps.setString(2, k.getNamaKendaraan());
            ps.setString(3, k.getJenis());
            ps.setInt(4, k.getKapasitas());
            ps.setString(5, k.getNomorPolisi());
            ps.setString(6, k.getStatus());
            ps.executeUpdate();
        }
    }
    public void update(Kendaraan k) throws SQLException {
        String sql = "UPDATE kendaraan SET nama_kendaraan = ?, jenis = ?, kapasitas = ?, nomor_polisi = ?, status = ? WHERE id_kendaraan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getNamaKendaraan());
            ps.setString(2, k.getJenis());
            ps.setInt(3, k.getKapasitas());
            ps.setString(4, k.getNomorPolisi());
            ps.setString(5, k.getStatus());
            ps.setString(6, k.getIdKendaraan());
            ps.executeUpdate();
        }
    }
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM kendaraan WHERE id_kendaraan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
    public List<Kendaraan> getAll() throws SQLException {
        List<Kendaraan> list = new ArrayList<>();
        String sql = "SELECT * FROM kendaraan ORDER BY id_kendaraan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Kendaraan(
                    rs.getString("id_kendaraan"),
                    rs.getString("nama_kendaraan"),
                    rs.getString("jenis"),
                    rs.getInt("kapasitas"),
                    rs.getString("nomor_polisi"),
                    rs.getString("status")
                ));
            }
        }
        return list;
    }
    public List<Kendaraan> search(String keyword) throws SQLException {
        List<Kendaraan> list = new ArrayList<>();
        String sql = "SELECT * FROM kendaraan WHERE id_kendaraan LIKE ? OR nama_kendaraan LIKE ? OR jenis LIKE ? OR nomor_polisi LIKE ? ORDER BY id_kendaraan";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String val = "%" + keyword + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, val);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Kendaraan(
                        rs.getString("id_kendaraan"),
                        rs.getString("nama_kendaraan"),
                        rs.getString("jenis"),
                        rs.getInt("kapasitas"),
                        rs.getString("nomor_polisi"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }
    public Kendaraan getById(String id) throws SQLException {
        String sql = "SELECT * FROM kendaraan WHERE id_kendaraan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Kendaraan(
                        rs.getString("id_kendaraan"),
                        rs.getString("nama_kendaraan"),
                        rs.getString("jenis"),
                        rs.getInt("kapasitas"),
                        rs.getString("nomor_polisi"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }
}
