package com.travel.dao;
import com.travel.config.DatabaseConnection;
import com.travel.model.Pelanggan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PelangganDAO {
    public void insert(Pelanggan p) throws SQLException {
        String sql = "INSERT INTO pelanggan (id_pelanggan, nama_pelanggan, alamat, no_hp, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPelanggan());
            ps.setString(2, p.getNamaPelanggan());
            ps.setString(3, p.getAlamat());
            ps.setString(4, p.getNoHp());
            ps.setString(5, p.getEmail());
            ps.executeUpdate();
        }
    }
    public void update(Pelanggan p) throws SQLException {
        String sql = "UPDATE pelanggan SET nama_pelanggan = ?, alamat = ?, no_hp = ?, email = ? WHERE id_pelanggan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNamaPelanggan());
            ps.setString(2, p.getAlamat());
            ps.setString(3, p.getNoHp());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getIdPelanggan());
            ps.executeUpdate();
        }
    }
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
    public List<Pelanggan> getAll() throws SQLException {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan ORDER BY id_pelanggan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Pelanggan(
                    rs.getString("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("alamat"),
                    rs.getString("no_hp"),
                    rs.getString("email")
                ));
            }
        }
        return list;
    }
    public List<Pelanggan> search(String keyword) throws SQLException {
        List<Pelanggan> list = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan WHERE id_pelanggan LIKE ? OR nama_pelanggan LIKE ? OR alamat LIKE ? OR no_hp LIKE ? OR email LIKE ? ORDER BY id_pelanggan";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String val = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, val);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Pelanggan(
                        rs.getString("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat"),
                        rs.getString("no_hp"),
                        rs.getString("email")
                    ));
                }
            }
        }
        return list;
    }
    public Pelanggan getById(String id) throws SQLException {
        String sql = "SELECT * FROM pelanggan WHERE id_pelanggan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pelanggan(
                        rs.getString("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat"),
                        rs.getString("no_hp"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }
}
