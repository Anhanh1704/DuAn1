package com.example.duan1.ui;

public class ChiTieu {
    public String mact;
    public String tenct;
    public String anhct;
    public String ngayct;
    public String tienct;
    public String loaict;
    public String ghichu;
    public String use;

    public ChiTieu() {
    }

    public ChiTieu(String mact, String tenct, String anhct, String ngayct, String tienct, String loaict, String ghichu, String use) {
        this.mact = mact;
        this.tenct = tenct;
        this.anhct = anhct;
        this.ngayct = ngayct;
        this.tienct = tienct;
        this.loaict = loaict;
        this.ghichu = ghichu;
        this.use = use;
    }

    public String getMact() {
        return mact;
    }

    public void setMact(String mact) {
        this.mact = mact;
    }

    public String getTenct() {
        return tenct;
    }

    public void setTenct(String tenct) {
        this.tenct = tenct;
    }

    public String getAnhct() {
        return anhct;
    }

    public void setAnhct(String anhct) {
        this.anhct = anhct;
    }

    public String getNgayct() {
        return ngayct;
    }

    public void setNgayct(String ngayct) {
        this.ngayct = ngayct;
    }

    public String getTienct() {
        return tienct;
    }

    public void setTienct(String tienct) {
        this.tienct = tienct;
    }

    public String getLoaict() {
        return loaict;
    }

    public void setLoaict(String loaict) {
        this.loaict = loaict;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
}
