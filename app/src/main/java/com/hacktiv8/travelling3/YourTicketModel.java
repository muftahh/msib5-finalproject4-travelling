package com.hacktiv8.travelling3;

public class YourTicketModel {
    private String namaPenumpang;
    private String asalBis;
    private String tujuanBis;
    private String tanggalKeberangkatan;
    private String classBis;
    private String noKursi;
    private String namaBis;
    private String jamPergi;
    private String harga;
    private String tanggalPembelian;
    private String nomerBooking;


    public YourTicketModel(String namaPenumpang, String asalBis, String tujuanBis,
                           String tanggalKeberangkatan, String classBis,
                           String noKursi, String namaBis, String jamPergi, String harga, String tanggalPembelian , String nomerBooking) {
        this.namaPenumpang = namaPenumpang;
        this.asalBis = asalBis;
        this.tujuanBis = tujuanBis;
        this.tanggalKeberangkatan = tanggalKeberangkatan;
        this.classBis = classBis;
        this.noKursi = noKursi;
        this.namaBis = namaBis;
        this.jamPergi = jamPergi;
        this.harga = harga;
        this.tanggalPembelian = tanggalPembelian;
        this.nomerBooking = nomerBooking;
    }

    public String getTanggalPembelian() {
        return tanggalPembelian;
    }

    public void setTanggalPembelian(String tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    public String getNomerBooking() {
        return nomerBooking;
    }

    public void setNomerBooking(String nomerBooking) {
        this.nomerBooking = nomerBooking;
    }

    public String getNamaPenumpang() {
        return namaPenumpang;
    }

    public String getAsalBis() {
        return asalBis;
    }

    public String getTujuanBis() {
        return tujuanBis;
    }

    public String getTanggalKeberangkatan() {
        return tanggalKeberangkatan;
    }

    public String getClassBis() {
        return classBis;
    }

    public String getNoKursi() {
        return noKursi;
    }

    public String getNamaBis() {
        return namaBis;
    }

    public String getJamPergi() {
        return jamPergi;
    }

    public String getHarga() {
        return harga;
    }

    public void setNamaPenumpang(String namaPenumpang) {
        this.namaPenumpang = namaPenumpang;
    }

    public void setAsalBis(String asalBis) {
        this.asalBis = asalBis;
    }

    public void setTujuanBis(String tujuanBis) {
        this.tujuanBis = tujuanBis;
    }

    public void setTanggalKeberangkatan(String tanggalKeberangkatan) {
        this.tanggalKeberangkatan = tanggalKeberangkatan;
    }

    public void setClassBis(String classBis) {
        this.classBis = classBis;
    }

    public void setNoKursi(String noKursi) {
        this.noKursi = noKursi;
    }

    public void setNamaBis(String namaBis) {
        this.namaBis = namaBis;
    }

    public void setJamPergi(String jamPergi) {
        this.jamPergi = jamPergi;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
