package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper2 extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "test2.db";

    public DBHelper2(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE INFO(center_id NUM, NAME TEXT, Period TEXT, PerPrice TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Price");
        onCreate(db);
    }

    public void insertPrice(Integer id, String name, String period, String price) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Price VALUES("+ id +",'" + name + "', '" + period + "', '" + price + "')");
        db.close();
    }


    public void updatePrice(String name, String period, String price) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Price SET PerPrice = '" + price + "' " + " WHERE NAME = '" + name + "' AND Period = '" + period + "'");
        db.close();
    }

    public void deletePrice(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Price WHERE NAME = '" + name + "'");
        db.close();
    }

    public String getPerPrice(String name, String period){
        SQLiteDatabase db = getWritableDatabase();
        String id = null;
        String sql = "select * from Price where NAME='"+name+"' AND Period='"+period+"'";//이름과 기간을 받아서 해당하는 가격을 받아온다
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            id = cursor.getString(2);
        }

        return id;
    }
}