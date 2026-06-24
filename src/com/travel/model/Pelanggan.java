package com.travel.model;
public class Pelanggan {
    private String idPelanggan;
    private String namaPelanggan;
    private String alamat;
    private String noHp;
    private String email;
    public Pelanggan() {}
    public Pelanggan(String idPelanggan, String namaPelanggan, String alamat, String noHp, String email) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.noHp = noHp;
        this.email = email;
    }
    public String getIdPelanggan() {
        return idPelanggan;
    }
    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getNoHp() {
        return noHp;
    }
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return idPelanggan + " - " + namaPelanggan;
    }
}
