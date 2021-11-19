package com.example.duan1.ui.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Data extends SQLiteOpenHelper {
    static final String dbName = "PNLIB";
    static final int dbVersion = 1;
    public Data(Context context){super(context,dbName,null,dbVersion);}


    @Override
    public void onCreate(SQLiteDatabase db) {

        //tao bang Nguoidung
        String createTableUSER=
                "create table USER("+
                        "use TEXT PRIMARY KEY,"+
                        "hinh BLOB,"+
                        "name TEXT,"+
                        "pass TEXT,"+
                        "phone TEXT,"+
                        "email TEXT ," +
                        "tienconlai TEXT)";
        db.execSQL(createTableUSER);

        String createTableCHITIEU=
                "create table CHITIEU("+
                        "mact TEXT PRIMARY KEY,"+
                        "tenct TEXT,"+
                        "anhct TEXT,"+
                        "ngayct DATE,"+
                        "tienct TEXT ," +
                        "loaict TEXT ," +
                        "ghichu TEXT ," +
                        "use TEXT REFERENCES USER(use))";
        db.execSQL(createTableCHITIEU);

//        db.execSQL(Datamodel.INSERT_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableUSER = "drop table if exists USER";
        db.execSQL(dropTableUSER);
        String dropTableCHITIEU = "drop table if exists CHITIEU";
        db.execSQL(dropTableCHITIEU);
    }
}
