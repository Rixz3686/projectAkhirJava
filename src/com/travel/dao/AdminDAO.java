package com.travel.dao;
import com.travel.config.DatabaseConnection;
import com.travel.model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AdminDAO {
    public Admin login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                        rs.getInt("id_admin"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nama_lengkap")
                    );
                }
            }
        }
        return null;
    }
}
