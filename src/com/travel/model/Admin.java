package com.travel.model;
public class Admin {
    private int idAdmin;
    private String username;
    private String password;
    private String namaLengkap;
    public Admin() {}
    public Admin(int idAdmin, String username, String password, String namaLengkap) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.password = password;
        this.namaLengkap = namaLengkap;
    }
    public int getIdAdmin() {
        return idAdmin;
    }
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNamaLengkap() {
        return namaLengkap;
    }
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }
}
