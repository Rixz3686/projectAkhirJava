package com.travel.model;
public class Kendaraan {
    private String idKendaraan;
    private String namaKendaraan;
    private String jenis;
    private int kapasitas;
    private String nomorPolisi;
    private String status;
    public Kendaraan() {}
    public Kendaraan(String idKendaraan, String namaKendaraan, String jenis, int kapasitas, String nomorPolisi, String status) {
        this.idKendaraan = idKendaraan;
        this.namaKendaraan = namaKendaraan;
        this.jenis = jenis;
        this.kapasitas = kapasitas;
        this.nomorPolisi = nomorPolisi;
        this.status = status;
    }
    public String getIdKendaraan() {
        return idKendaraan;
    }
    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }
    public String getNamaKendaraan() {
        return namaKendaraan;
    }
    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public int getKapasitas() {
        return kapasitas;
    }
    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
    public String getNomorPolisi() {
        return nomorPolisi;
    }
    public void setNomorPolisi(String nomorPolisi) {
        this.nomorPolisi = nomorPolisi;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return idKendaraan + " - " + namaKendaraan + " (" + nomorPolisi + ")";
    }
}
