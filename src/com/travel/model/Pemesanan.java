package com.travel.model;
import java.sql.Date;
public class Pemesanan {
    private String idPemesanan;
    private String idPelanggan;
    private String idPaket;
    private Date tanggalPesan;
    private int jumlahPeserta;
    private double totalBayar;
    private String namaPelanggan;
    private String namaPaket;
    public Pemesanan() {}
    public Pemesanan(String idPemesanan, String idPelanggan, String idPaket, Date tanggalPesan, int jumlahPeserta, double totalBayar) {
        this.idPemesanan = idPemesanan;
        this.idPelanggan = idPelanggan;
        this.idPaket = idPaket;
        this.tanggalPesan = tanggalPesan;
        this.jumlahPeserta = jumlahPeserta;
        this.totalBayar = totalBayar;
    }
    public String getIdPemesanan() {
        return idPemesanan;
    }
    public void setIdPemesanan(String idPemesanan) {
        this.idPemesanan = idPemesanan;
    }
    public String getIdPelanggan() {
        return idPelanggan;
    }
    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
    public String getIdPaket() {
        return idPaket;
    }
    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }
    public Date getTanggalPesan() {
        return tanggalPesan;
    }
    public void setTanggalPesan(Date tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }
    public int getJumlahPeserta() {
        return jumlahPeserta;
    }
    public void setJumlahPeserta(int jumlahPeserta) {
        this.jumlahPeserta = jumlahPeserta;
    }
    public double getTotalBayar() {
        return totalBayar;
    }
    public void setTotalBayar(double totalBayar) {
        this.totalBayar = totalBayar;
    }
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
    public String getNamaPaket() {
        return namaPaket;
    }
    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }
    @Override
    public String toString() {
        return idPemesanan + " - " + (namaPelanggan != null ? namaPelanggan : idPelanggan);
    }
}
