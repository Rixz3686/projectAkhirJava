package com.travel.dao;
import com.travel.config.DatabaseConnection;
import com.travel.model.Paket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PaketDAO {
    public void insert(Paket p) throws SQLException {
        String sql = "INSERT INTO paket (id_paket, nama_paket, tujuan, harga, durasi_hari, kuota) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPaket());
            ps.setString(2, p.getNamaPaket());
            ps.setString(3, p.getTujuan());
            ps.setDouble(4, p.getHarga());
            ps.setInt(5, p.getDurasiHari());
            ps.setInt(6, p.getKuota());
            ps.executeUpdate();
        }
    }
    public void update(Paket p) throws SQLException {
        String sql = "UPDATE paket SET nama_paket = ?, tujuan = ?, harga = ?, durasi_hari = ?, kuota = ? WHERE id_paket = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNamaPaket());
            ps.setString(2, p.getTujuan());
            ps.setDouble(3, p.getHarga());
            ps.setInt(4, p.getDurasiHari());
            ps.setInt(5, p.getKuota());
            ps.setString(6, p.getIdPaket());
            ps.executeUpdate();
        }
    }
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM paket WHERE id_paket = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }
    public List<Paket> getAll() throws SQLException {
        List<Paket> list = new ArrayList<>();
        String sql = "SELECT * FROM paket ORDER BY id_paket";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Paket(
                    rs.getString("id_paket"),
                    rs.getString("nama_paket"),
                    rs.getString("tujuan"),
                    rs.getDouble("harga"),
                    rs.getInt("durasi_hari"),
                    rs.getInt("kuota")
                ));
            }
        }
        return list;
    }
    public List<Paket> search(String keyword) throws SQLException {
        List<Paket> list = new ArrayList<>();
        String sql = "SELECT * FROM paket WHERE id_paket LIKE ? OR nama_paket LIKE ? OR tujuan LIKE ? ORDER BY id_paket";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String val = "%" + keyword + "%";
            ps.setString(1, val);
            ps.setString(2, val);
            ps.setString(3, val);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Paket(
                        rs.getString("id_paket"),
                        rs.getString("nama_paket"),
                        rs.getString("tujuan"),
                        rs.getDouble("harga"),
                        rs.getInt("durasi_hari"),
                        rs.getInt("kuota")
                    ));
                }
            }
        }
        return list;
    }
    public Paket getById(String id) throws SQLException {
        String sql = "SELECT * FROM paket WHERE id_paket = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Paket(
                        rs.getString("id_paket"),
                        rs.getString("nama_paket"),
                        rs.getString("tujuan"),
                        rs.getDouble("harga"),
                        rs.getInt("durasi_hari"),
                        rs.getInt("kuota")
                    );
                }
            }
        }
        return null;
    }
}
