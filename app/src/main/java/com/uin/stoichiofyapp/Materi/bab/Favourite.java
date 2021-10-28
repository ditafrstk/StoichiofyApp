package com.uin.stoichiofyapp.Materi.bab;

public class Favourite {
    String judul, tujuan, tujuan2, tujuan3, url;

    public Favourite(String url, String judul, String tujuan, String tujuan2, String tujuan3) {
        this.url = url;
        this.judul = judul;
        this.tujuan = tujuan;
        this.tujuan2 = tujuan2;
        this.tujuan3 = tujuan3;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getUrl() {
        return url;
    }

    public String setUrl(String url) {
        this.url = url;
        return url;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTujuan2() {
        return tujuan2;
    }

    public void setTujuan2(String tujuan2) {
        this.tujuan2 = tujuan2;
    }

    public String getTujuan3() {
        return tujuan3;
    }

    public void setTujuan3(String tujuan3) {
        this.tujuan3 = tujuan3;
    }
}
