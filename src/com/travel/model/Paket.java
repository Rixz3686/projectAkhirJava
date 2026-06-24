package com.travel.model;
public class Paket {
    private String idPaket;
    private String namaPaket;
    private String tujuan;
    private double harga;
    private int durasiHari;
    private int kuota;
    public Paket() {}
    public Paket(String idPaket, String namaPaket, String tujuan, double harga, int durasiHari, int kuota) {
        this.idPaket = idPaket;
        this.namaPaket = namaPaket;
        this.tujuan = tujuan;
        this.harga = harga;
        this.durasiHari = durasiHari;
        this.kuota = kuota;
    }
    public String getIdPaket() {
        return idPaket;
    }
    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }
    public String getNamaPaket() {
        return namaPaket;
    }
    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }
    public String getTujuan() {
        return tujuan;
    }
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
    public double getHarga() {
        return harga;
    }
    public void setHarga(double harga) {
        this.harga = harga;
    }
    public int getDurasiHari() {
        return durasiHari;
    }
    public void setDurasiHari(int durasiHari) {
        this.durasiHari = durasiHari;
    }
    public int getKuota() {
        return kuota;
    }
    public void setKuota(int kuota) {
        this.kuota = kuota;
    }
    @Override
    public String toString() {
        return idPaket + " - " + namaPaket + " (" + tujuan + ")";
    }
}
