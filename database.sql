
CREATE DATABASE IF NOT EXISTS db_travel_231011401570;
USE db_travel_231011401570;
CREATE TABLE IF NOT EXISTS admin (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nama_lengkap VARCHAR(100) NOT NULL
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS pelanggan (
    id_pelanggan VARCHAR(20) PRIMARY KEY,
    nama_pelanggan VARCHAR(100) NOT NULL,
    alamat TEXT NOT NULL,
    no_hp VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS paket (
    id_paket VARCHAR(20) PRIMARY KEY,
    nama_paket VARCHAR(100) NOT NULL,
    tujuan VARCHAR(100) NOT NULL,
    harga DECIMAL(12,2) NOT NULL,
    durasi_hari INT NOT NULL,
    kuota INT NOT NULL
) ENGINE=InnoDB;
ALTER TABLE paket ADD COLUMN IF NOT EXISTS kuota INT NOT NULL DEFAULT 20;
CREATE TABLE IF NOT EXISTS kendaraan (
    id_kendaraan VARCHAR(20) PRIMARY KEY,
    nama_kendaraan VARCHAR(100) NOT NULL,
    jenis VARCHAR(50) NOT NULL,
    kapasitas INT NOT NULL,
    nomor_polisi VARCHAR(20) UNIQUE NOT NULL,
    status VARCHAR(20) NOT NULL
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS pemesanan (
    id_pemesanan VARCHAR(20) PRIMARY KEY,
    id_pelanggan VARCHAR(20) NOT NULL,
    id_paket VARCHAR(20) NOT NULL,
    tanggal_pesan DATE NOT NULL,
    jumlah_peserta INT NOT NULL,
    total_bayar DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_pemesanan_pelanggan FOREIGN KEY (id_pelanggan) 
        REFERENCES pelanggan(id_pelanggan) ON DELETE CASCADE,
    CONSTRAINT fk_pemesanan_paket FOREIGN KEY (id_paket) 
        REFERENCES paket(id_paket) ON DELETE CASCADE
) ENGINE=InnoDB;
CREATE TABLE IF NOT EXISTS pembayaran (
    id_pembayaran VARCHAR(20) PRIMARY KEY,
    id_pemesanan VARCHAR(20) NOT NULL,
    tanggal_bayar DATE NOT NULL,
    metode_bayar VARCHAR(50) NOT NULL,
    jumlah_bayar DECIMAL(12,2) NOT NULL,
    status_pembayaran VARCHAR(50) NOT NULL,
    CONSTRAINT fk_pembayaran_pemesanan FOREIGN KEY (id_pemesanan) 
        REFERENCES pemesanan(id_pemesanan) ON DELETE CASCADE
) ENGINE=InnoDB;
INSERT INTO admin (username, password, nama_lengkap) VALUES 
('admin', 'admin', 'Administrator Utama Travel'),
('petugas', 'petugas123', 'Petugas Admin Travel');
INSERT INTO pelanggan (id_pelanggan, nama_pelanggan, alamat, no_hp, email) VALUES
('PLG-001', 'Rizky Pratama', 'Jl. Sudirman No. 12, Jakarta', '081234567890', 'rizky.pratama@email.com'),
('PLG-002', 'Budi Santoso', 'Jl. Merdeka No. 45, Bandung', '081298765432', 'budi.santoso@email.com'),
('PLG-003', 'Siti Aminah', 'Jl. Diponegoro No. 89, Surabaya', '085712345678', 'siti.aminah@email.com'),
('PLG-004', 'Dewi Lestari', 'Jl. Pemuda No. 34, Semarang', '087812345678', 'dewi.lestari@email.com'),
('PLG-005', 'Joko Widodo', 'Jl. Gajah Mada No. 101, Yogyakarta', '089912345678', 'joko.widodo@email.com');
INSERT INTO paket (id_paket, nama_paket, tujuan, harga, durasi_hari, kuota) VALUES
('PKT-001', 'Wisata Alam Bali - Rizky 231011401570', 'Bali', 3500000.00, 4, 25),
('PKT-002', 'Explore Borobudur', 'Yogyakarta', 1800000.00, 3, 30),
('PKT-003', 'Kawah Ijen & Bromo', 'Banyuwangi', 2500000.00, 3, 20),
('PKT-004', 'Diving Bunaken', 'Manado', 5500000.00, 5, 15),
('PKT-005', 'City Tour Bandung', 'Bandung', 1200000.00, 2, 35),
('PKT-006', 'Labuan Bajo Premium', 'Labuan Bajo', 7200000.00, 5, 12),
('PKT-007', 'Raja Ampat Adventure', 'Papua Barat', 9500000.00, 6, 10),
('PKT-008', 'Lombok Mandalika Trip', 'Lombok', 2800000.00, 3, 22),
('PKT-009', 'Belitung Island Hopping', 'Belitung', 2600000.00, 3, 18),
('PKT-010', 'Dieng Sunrise Tour', 'Wonosobo', 1500000.00, 2, 28);
INSERT INTO kendaraan (id_kendaraan, nama_kendaraan, jenis, kapasitas, nomor_polisi, status) VALUES
('KND-001', 'Toyota Hiace Commuter', 'Minibus', 15, 'B 1234 SAA', 'Tersedia'),
('KND-002', 'Isuzu Elf Long', 'Minibus', 19, 'B 5678 TBB', 'Tersedia'),
('KND-003', 'Mercedes-Benz Sprinter', 'Minibus Luxury', 10, 'D 9012 UAA', 'Perawatan'),
('KND-004', 'Toyota Avanza Veloz', 'MPV', 7, 'AB 3456 CD', 'Tersedia'),
('KND-005', 'Big Bus Hino R260', 'Bus', 45, 'DK 7890 EF', 'Tersedia');
INSERT INTO pemesanan (id_pemesanan, id_pelanggan, id_paket, tanggal_pesan, jumlah_peserta, total_bayar) VALUES
('PMS-001', 'PLG-001', 'PKT-001', '2026-06-15', 2, 7000000.00),
('PMS-002', 'PLG-002', 'PKT-002', '2026-06-16', 3, 5400000.00),
('PMS-003', 'PLG-003', 'PKT-003', '2026-06-17', 2, 5000000.00),
('PMS-004', 'PLG-004', 'PKT-004', '2026-06-18', 1, 5500000.00),
('PMS-005', 'PLG-005', 'PKT-005', '2026-06-19', 4, 4800000.00);
INSERT INTO pembayaran (id_pembayaran, id_pemesanan, tanggal_bayar, metode_bayar, jumlah_bayar, status_pembayaran) VALUES
('PBY-001', 'PMS-001', '2026-06-15', 'Transfer Bank', 7000000.00, 'Lunas'),
('PBY-002', 'PMS-002', '2026-06-16', 'Tunai', 2000000.00, 'DP'),
('PBY-003', 'PMS-003', '2026-06-17', 'Transfer Bank', 5000000.00, 'Lunas'),
('PBY-004', 'PMS-004', '2026-06-18', 'Kartu Kredit', 5500000.00, 'Lunas'),
('PBY-005', 'PMS-005', '2026-06-19', 'Transfer Bank', 4800000.00, 'Lunas');
