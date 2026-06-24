package com.travel.config;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class DatabaseConnection {
    private static Connection connection = null;
    private static String host = "localhost";
    private static String port = "3306";
    private static String dbName = "db_travel_231011401570";
    private static String user = "root";
    private static String password = "";
    static {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConnection.class.getResourceAsStream("/db.properties")) {
            if (input != null) {
                properties.load(input);
                host = properties.getProperty("db.host", host);
                port = properties.getProperty("db.port", port);
                dbName = properties.getProperty("db.name", dbName);
                user = properties.getProperty("db.user", user);
                password = properties.getProperty("db.password", password);
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load db.properties, using default values.");
        }
    }
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
                connection = DriverManager.getConnection(url, user, password);
                ensureSchemaAndSeedData(connection);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found!", e);
            }
        }
        return connection;
    }
    private static void ensureSchemaAndSeedData(Connection connection) {
        ensureKuotaColumn(connection);
        ensureAdminData(connection);
        ensurePelangganData(connection);
        ensurePaketData(connection);
        ensureKendaraanData(connection);
        ensurePemesananData(connection);
        ensurePembayaranData(connection);
    }
    private static void ensureKuotaColumn(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("ALTER TABLE paket ADD COLUMN kuota INT NOT NULL DEFAULT 20");
        } catch (Exception ignore) {
        }
    }
    private static void ensureAdminData(Connection connection) {
        String checkSql = "SELECT COUNT(*) FROM admin WHERE username = ?";
        String insertSql = "INSERT INTO admin (username, password, nama_lengkap) VALUES (?, ?, ?)";
        try (PreparedStatement check = connection.prepareStatement(checkSql)) {
            insertAdminIfMissing(connection, check, insertSql, "admin", "admin", "Administrator Utama Travel");
            insertAdminIfMissing(connection, check, insertSql, "petugas", "petugas123", "Petugas Admin Travel");
        } catch (SQLException ignore) {
        }
    }
    private static void insertAdminIfMissing(Connection connection, PreparedStatement check, String insertSql,
            String username, String password, String namaLengkap) throws SQLException {
        check.setString(1, username);
        try (ResultSet rs = check.executeQuery()) {
            if (rs.next() && rs.getInt(1) == 0) {
                try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
                    insert.setString(1, username);
                    insert.setString(2, password);
                    insert.setString(3, namaLengkap);
                    insert.executeUpdate();
                }
            }
        }
    }
    private static void ensurePelangganData(Connection connection) {
        String insertSql = "INSERT IGNORE INTO pelanggan (id_pelanggan, nama_pelanggan, alamat, no_hp, email) VALUES (?, ?, ?, ?, ?)";
        String[][] data = {
            {"PLG-001", "Rizky 231011401570", "Jl. Sudirman No. 12", "081234567890", "rizky@email.com"},
            {"PLG-002", "Budi Santoso", "Jl. Merdeka No. 45", "081298765432", "budi@email.com"},
            {"PLG-003", "Siti Aminah", "Jl. Diponegoro No. 89", "085712345678", "siti@email.com"},
            {"PLG-004", "Dewi Lestari", "Jl. Pemuda No. 34", "087812345678", "dewi@email.com"},
            {"PLG-005", "Joko Widodo", "Jl. Gajah Mada No. 101", "089912345678", "joko@email.com"},
            {"PLG-006", "Andi Syahputra", "Jl. Ahmad Yani No. 12", "08111222333", "andi@email.com"},
            {"PLG-007", "Citra Kirana", "Jl. Melati No. 5", "08222333444", "citra@email.com"},
            {"PLG-008", "Rudi Hermawan", "Jl. Mawar No. 8", "08333444555", "rudi@email.com"},
            {"PLG-009", "Maya Sari", "Jl. Kamboja No. 11", "08444555666", "maya@email.com"},
            {"PLG-010", "Dedi Corbuzier", "Jl. Anggrek No. 2", "08555666777", "dedi@email.com"}
        };
        try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
            for (String[] row : data) {
                insert.setString(1, row[0]);
                insert.setString(2, row[1]);
                insert.setString(3, row[2]);
                insert.setString(4, row[3]);
                insert.setString(5, row[4]);
                insert.executeUpdate();
            }
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("UPDATE pelanggan SET nama_pelanggan = 'Rizky 231011401570' WHERE id_pelanggan = 'PLG-001'");
            }
        } catch (SQLException ignore) {}
    }
    private static void ensurePaketData(Connection connection) {
        String insertSql = "INSERT IGNORE INTO paket (id_paket, nama_paket, tujuan, harga, durasi_hari, kuota) VALUES (?, ?, ?, ?, ?, ?)";
        String[][] paketData = {
            {"PKT-001", "Wisata Alam Bali - Rizky 231011401570", "Bali", "3500000", "4", "25"},
            {"PKT-002", "Explore Borobudur", "Yogyakarta", "1800000", "3", "30"},
            {"PKT-003", "Kawah Ijen & Bromo", "Banyuwangi", "2500000", "3", "20"},
            {"PKT-004", "Diving Bunaken", "Manado", "5500000", "5", "15"},
            {"PKT-005", "City Tour Bandung", "Bandung", "1200000", "2", "35"},
            {"PKT-006", "Labuan Bajo Premium", "Labuan Bajo", "7200000", "5", "12"},
            {"PKT-007", "Raja Ampat Adventure", "Papua Barat", "9500000", "6", "10"},
            {"PKT-008", "Lombok Mandalika Trip", "Lombok", "2800000", "3", "22"},
            {"PKT-009", "Belitung Island Hopping", "Belitung", "2600000", "3", "18"},
            {"PKT-010", "Dieng Sunrise Tour", "Wonosobo", "1500000", "2", "28"}
        };
        try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
            for (String[] row : paketData) {
                insert.setString(1, row[0]);
                insert.setString(2, row[1]);
                insert.setString(3, row[2]);
                insert.setDouble(4, Double.parseDouble(row[3]));
                insert.setInt(5, Integer.parseInt(row[4]));
                insert.setInt(6, Integer.parseInt(row[5]));
                insert.executeUpdate();
            }
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("UPDATE paket SET nama_paket = 'Wisata Alam Bali - Rizky 231011401570' WHERE id_paket = 'PKT-001'");
            }
        } catch (SQLException ignore) {}
    }
    private static void ensureKendaraanData(Connection connection) {
        String insertSql = "INSERT IGNORE INTO kendaraan (id_kendaraan, nama_kendaraan, jenis, kapasitas, nomor_polisi, status) VALUES (?, ?, ?, ?, ?, ?)";
        String[][] data = {
            {"KND-001", "Toyota Hiace Commuter", "Minibus", "15", "B 1234 SAA", "Tersedia"},
            {"KND-002", "Isuzu Elf Long", "Minibus", "19", "B 5678 TBB", "Tersedia"},
            {"KND-003", "Mercedes-Benz Sprinter", "Minibus Luxury", "10", "D 9012 UAA", "Perawatan"},
            {"KND-004", "Toyota Avanza Veloz", "MPV", "7", "AB 3456 CD", "Tersedia"},
            {"KND-005", "Big Bus Hino R260", "Bus", "45", "DK 7890 EF", "Tersedia"},
            {"KND-006", "Medium Bus Mitsubishi", "Bus", "29", "B 1111 XX", "Tersedia"},
            {"KND-007", "Toyota Innova Reborn", "MPV", "7", "D 2222 YY", "Tersedia"},
            {"KND-008", "Suzuki Ertiga", "MPV", "7", "F 3333 ZZ", "Disewa"},
            {"KND-009", "Elf Short", "Minibus", "12", "E 4444 AA", "Tersedia"},
            {"KND-010", "Hiace Premio", "Minibus", "12", "B 5555 BB", "Tersedia"}
        };
        try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
            for (String[] row : data) {
                insert.setString(1, row[0]);
                insert.setString(2, row[1]);
                insert.setString(3, row[2]);
                insert.setInt(4, Integer.parseInt(row[3]));
                insert.setString(5, row[4]);
                insert.setString(6, row[5]);
                insert.executeUpdate();
            }
        } catch (SQLException ignore) {}
    }
    private static void ensurePemesananData(Connection connection) {
        String insertSql = "INSERT IGNORE INTO pemesanan (id_pemesanan, id_pelanggan, id_paket, tanggal_pesan, jumlah_peserta, total_bayar) VALUES (?, ?, ?, ?, ?, ?)";
        String[][] data = {
            {"PMS-001", "PLG-001", "PKT-001", "2026-06-15", "2", "7000000"},
            {"PMS-002", "PLG-002", "PKT-002", "2026-06-16", "3", "5400000"},
            {"PMS-003", "PLG-003", "PKT-003", "2026-06-17", "2", "5000000"},
            {"PMS-004", "PLG-004", "PKT-004", "2026-06-18", "1", "5500000"},
            {"PMS-005", "PLG-005", "PKT-005", "2026-06-19", "4", "4800000"},
            {"PMS-006", "PLG-006", "PKT-006", "2026-06-20", "2", "14400000"},
            {"PMS-007", "PLG-007", "PKT-007", "2026-06-21", "2", "19000000"},
            {"PMS-008", "PLG-008", "PKT-008", "2026-06-22", "5", "14000000"},
            {"PMS-009", "PLG-009", "PKT-009", "2026-06-23", "4", "10400000"},
            {"PMS-010", "PLG-010", "PKT-010", "2026-06-24", "3", "4500000"}
        };
        try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
            for (String[] row : data) {
                insert.setString(1, row[0]);
                insert.setString(2, row[1]);
                insert.setString(3, row[2]);
                insert.setString(4, row[3]);
                insert.setInt(5, Integer.parseInt(row[4]));
                insert.setDouble(6, Double.parseDouble(row[5]));
                insert.executeUpdate();
            }
        } catch (SQLException ignore) {}
    }
    private static void ensurePembayaranData(Connection connection) {
        String insertSql = "INSERT IGNORE INTO pembayaran (id_pembayaran, id_pemesanan, tanggal_bayar, metode_bayar, jumlah_bayar, status_pembayaran) VALUES (?, ?, ?, ?, ?, ?)";
        String[][] data = {
            {"PBY-001", "PMS-001", "2026-06-15", "Transfer Bank", "7000000", "Lunas"},
            {"PBY-002", "PMS-002", "2026-06-16", "Tunai", "2000000", "DP"},
            {"PBY-003", "PMS-003", "2026-06-17", "Transfer Bank", "5000000", "Lunas"},
            {"PBY-004", "PMS-004", "2026-06-18", "Kartu Kredit", "5500000", "Lunas"},
            {"PBY-005", "PMS-005", "2026-06-19", "Transfer Bank", "4800000", "Lunas"},
            {"PBY-006", "PMS-006", "2026-06-20", "Tunai", "14400000", "Lunas"},
            {"PBY-007", "PMS-007", "2026-06-21", "Transfer Bank", "19000000", "Lunas"},
            {"PBY-008", "PMS-008", "2026-06-22", "Transfer Bank", "14000000", "Lunas"},
            {"PBY-009", "PMS-009", "2026-06-23", "Kartu Kredit", "10400000", "Lunas"},
            {"PBY-010", "PMS-010", "2026-06-24", "Transfer Bank", "4500000", "Lunas"}
        };
        try (PreparedStatement insert = connection.prepareStatement(insertSql)) {
            for (String[] row : data) {
                insert.setString(1, row[0]);
                insert.setString(2, row[1]);
                insert.setString(3, row[2]);
                insert.setString(4, row[3]);
                insert.setDouble(5, Double.parseDouble(row[4]));
                insert.setString(6, row[5]);
                insert.executeUpdate();
            }
        } catch (SQLException ignore) {}
    }
}
