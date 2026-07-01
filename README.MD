# Travel Management System

Aplikasi desktop berbasis Java Swing untuk manajemen pemesanan paket wisata (Travel Package). Aplikasi ini dirancang menggunakan arsitektur **MVC (Model-View-Controller)** dan pola **DAO (Data Access Object)** untuk memisahkan logika bisnis, tampilan, dan akses data.

## 🚀 Fitur Utama

- **Manajemen Pelanggan**: Tambah, ubah, hapus, dan cari data pelanggan.
- **Manajemen Paket Travel**: Kelola daftar destinasi wisata, harga, durasi, dan kuota peserta.
- **Manajemen Kendaraan**: Pengaturan armada travel (jenis kendaraan, kapasitas, status).
- **Transaksi Pemesanan**: Input data pemesanan secara otomatis menghitung total bayar.
- **Transaksi Pembayaran**: Input pelunasan/DP, metode pembayaran, dan status pembayaran.
- **Laporan (Report)**: Print out tabel data pelanggan, paket, pemesanan, dan pembayaran.
- **Tampilan Modern**: Menggunakan library [FlatLaf](https://www.formdev.com/flatlaf/) untuk Dark Mode UI.

## 🛠️ Teknologi yang Digunakan

- **Bahasa**: Java 8+
- **GUI Framework**: Java Swing
- **Arsitektur**: MVC (Model-View-Controller) & DAO (Data Access Object)
- **Library UI**: FlatLaf (Dark/Light Look and Feel)
- **Database**: SQLite / MySQL
- **Build Tool**: Apache Ant

## 🔑 Kredensial Login (Default)

Aplikasi memiliki fitur autentikasi sederhana. Saat pertama kali dijalankan, sistem akan men-generate data otomatis. Gunakan akun berikut untuk masuk:

| Role | Username | Password |
| :--- | :--- | :--- |
| **Admin** | `admin` | `admin` |
| **Petugas** | `petugas` | `petugas123` |

## ⚙️ Cara Menjalankan Aplikasi

1. Clone repositori ini.
2. Buka project di IDE (NetBeans / IntelliJ IDEA / Eclipse).
3. Pastikan library `flatlaf.jar` dan driver database (opsional) sudah ada di folder `lib/` atau di-build-path.
4. Build/Compile (atau gunakan Apache Ant).
5. Jalankan class `com.travel.Main`.
6. Ubah kredensial db.properties milik anda
7. Lalu import semua database.sql di phpmyadmin/adminer/ dll

## 📂 Struktur Direktori

```text
src/com/travel/
├── config/       # Konfigurasi Database & Seeding
├── controller/   # Logika interaksi View & Model (MVC)
├── dao/          # Data Access Object untuk operasi CRUD
├── model/        # Entitas / Struktur data (Paket, Pemesanan, Pelanggan, dll)
├── util/         # Utility (Format laporan, helper GUI)
├── view/         # Interface / Panel Swing
└── Main.java     # Entry point aplikasi
```