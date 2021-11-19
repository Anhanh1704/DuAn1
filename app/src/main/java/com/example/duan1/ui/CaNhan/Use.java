package com.example.duan1.ui.CaNhan;

public class Use {
    public String use;
    public byte[] hinh;
    public String name;
    public String pass;
    public String phone;
    public String email;
    public String tienconlai;

    public Use() {
    }

    public Use(String use, String pass) {
        this.use = use;
        this.pass = pass;
    }

    public Use(String use, byte[] hinh, String name, String pass, String phone, String email, String tienconlai) {
        this.use = use;
        this.hinh = hinh;
        this.name = name;
        this.pass = pass;
        this.phone = phone;
        this.email = email;
        this.tienconlai = tienconlai;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTienconlai() {
        return tienconlai;
    }

    public void setTienconlai(String tienconlai) {
        this.tienconlai = tienconlai;
    }
}

