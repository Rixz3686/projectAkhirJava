package com.travel.model;
import java.sql.Date;
public class Pembayaran {
    private String idPembayaran;
    private String idPemesanan;
    private Date tanggalBayar;
    private String metodeBayar;
    private double jumlahBayar;
    private String statusPembayaran;
    private String namaPelanggan;
    public Pembayaran() {}
    public Pembayaran(String idPembayaran, String idPemesanan, Date tanggalBayar, String metodeBayar, double jumlahBayar, String statusPembayaran) {
        this.idPembayaran = idPembayaran;
        this.idPemesanan = idPemesanan;
        this.tanggalBayar = tanggalBayar;
        this.metodeBayar = metodeBayar;
        this.jumlahBayar = jumlahBayar;
        this.statusPembayaran = statusPembayaran;
    }
    public String getIdPembayaran() {
        return idPembayaran;
    }
    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }
    public String getIdPemesanan() {
        return idPemesanan;
    }
    public void setIdPemesanan(String idPemesanan) {
        this.idPemesanan = idPemesanan;
    }
    public Date getTanggalBayar() {
        return tanggalBayar;
    }
    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }
    public String getMetodeBayar() {
        return metodeBayar;
    }
    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }
    public double getJumlahBayar() {
        return jumlahBayar;
    }
    public void setJumlahBayar(double jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }
    public String getStatusPembayaran() {
        return statusPembayaran;
    }
    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
}
