package com.example.duan1.ui.CaNhan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.ui.database.Data;

import java.util.ArrayList;

public class UseDAO {
    private Data dbHelper;

    public UseDAO(Context context) { dbHelper = new Data(context); }


    public boolean addUse(Use use){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("use",use.getUse());
        values.put("hinh",use.getHinh());
        values.put("name",use.getName());
        values.put("pass",use.getPass());
        values.put("phone",use.getPhone());
        values.put("email",use.getEmail());
        values.put("tienconlai",use.getTienconlai());
        long r = db.insert("USER", null, values);
        try {
            if(r < 0){
                return false;
            }
        }catch (Exception ex){
            Log.e("UseDAO",ex.toString());
        }
        return true;
    }

    //getAll
    public ArrayList<Use> getallUse(){
        ArrayList<Use> dsUse = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM USER", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            try {
                String use = c.getString(0);
                byte[] hinh = c.getBlob(1);
                String name = c.getString(2);
                String pass = c.getString(3);
                String phone =c.getString(4);
                String email = c.getString(5);
                String tienconlai = c.getString(6);
                Use ee = new Use(use, hinh, name, pass,phone, email, tienconlai);
                dsUse.add(ee);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            c.moveToNext();
        }
        c.close();
        return dsUse;

    }
    //update
    public boolean doipassUse(Use use){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("pass",use.getPass());
        int result = db.update("USER",values,"use=?", new String[]{use.getUse()});
        if (result == 0){
            return false;
        }
        return true;
    }
    public boolean updateUse(Use use){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("use",use.getUse());
        values.put("hinh",use.getHinh());
        values.put("name",use.getName());
        values.put("pass",use.getPass());
        values.put("phone",use.getPhone());
        values.put("email",use.getEmail());
        values.put("tienconlai",use.getTienconlai());
        long result = db.update("USER",values,"use=?", new String[]{use.getUse()});
        if (result < 0){
            return false;
        }
        return true;
    }

    //delete
    public boolean delUse(Use use){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete("USER","use=?",new String[]{use.getUse()});
        if (result < 0)
            return false;
        return true;
    }
    public Use getUsepass(String account){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("USER", new String[]{"use", "pass"},
                "use=?", new String[]{account}, null, null, null,null);
        Use s = new Use();
        if(cursor.moveToFirst()){
            s.setUse(cursor.getString(0));
            s.setPass(cursor.getString(1));
        }else{
            s = null;
        }
        cursor.close();
        db.close();
        return s;
    }
}