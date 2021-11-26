package com.example.duan1.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.ui.CaNhan.Use;
import com.example.duan1.ui.database.Data;

import java.util.ArrayList;

public class ChiTieuDAO {

    private Data dbHelper;

    public ChiTieuDAO(Context context) { dbHelper = new Data(context); }


    public boolean addchitieu(ChiTieu chiTieu){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mact",chiTieu.getMact());
        values.put("tenct",chiTieu.getTenct());
        values.put("anhct",chiTieu.getAnhct());
        values.put("ngayct",chiTieu.getNgayct());
        values.put("tienct",chiTieu.getTienct());
        values.put("loaict",chiTieu.getLoaict());
        values.put("ghichu",chiTieu.getGhichu());
        values.put("use",chiTieu.getUse());
        long r = db.insert("mact", null, values);
        try {
            if(r < 0){
                return false;
            }
        }catch (Exception ex){
            Log.e("ChiTieuDAO",ex.toString());
        }
        return true;
    }

    //getAll
    public ArrayList<ChiTieu> getallchitieu(String user){
        ArrayList<ChiTieu> dsChitieu = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM CHITIEU WHERE use = '"+ user +"'", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            try {
                String mact = c.getString(0);
                String tenct = c.getString(1);
                String anhct = c.getString(2);
                String ngayct = c.getString(3);
                String tienct =c.getString(4);
                String loaict = c.getString(5);
                String ghichu = c.getString(6);
                String use = c.getString(7);
                ChiTieu ee = new ChiTieu(mact, tenct, anhct, ngayct,tienct, loaict, ghichu, use);
                dsChitieu.add(ee);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            c.moveToNext();
        }
        c.close();
        return dsChitieu;

    }
    //update
    public boolean updatechitieu(ChiTieu chiTieu){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenct",chiTieu.getTenct());
        values.put("anhct",chiTieu.getAnhct());
        values.put("ngayct",chiTieu.getNgayct());
        values.put("tienct",chiTieu.getTienct());
        values.put("loaict",chiTieu.getLoaict());
        values.put("ghichu",chiTieu.getGhichu());
        values.put("use",chiTieu.getUse());
        long result = db.update("CHITIEU",values,"mact=?", new String[]{chiTieu.getMact()});
        if (result < 0){
            return false;
        }
        return true;
    }


    //delete
    public boolean delchitieu(ChiTieu chiTieu){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete("CHITIEU","mact=?",new String[]{chiTieu.getMact()});
        if (result < 0)
            return false;
        return true;
    }

}
