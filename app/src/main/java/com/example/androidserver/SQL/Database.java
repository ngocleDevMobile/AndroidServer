package com.example.androidserver.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "Preset.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE card(iduser text,_idpreset text, namesp text, price decimal, content text, path text)");

//        db.execSQL("CREATE TABLE loai_chi(id integer primary key autoincrement, ten_loai_chi text)");
//        db.execSQL("CREATE TABLE khoan_chi(id_thu integer primary key autoincrement, ten_muc_chi text, so_tien decimal, don_vi text, ngay_chi date, gio_chi time, danh_gia integer, id_loaichi integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS card");

    }
}
