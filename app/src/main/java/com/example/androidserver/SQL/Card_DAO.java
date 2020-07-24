package com.example.androidserver.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.androidserver.Modal_SQL.Card;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Card_DAO {
    public SQLiteDatabase db;
    public Database dbh1;

    public Card_DAO(Context c) {
        dbh1 = new Database(c);
    }


    public boolean addCard(Card mt) {
        db = dbh1.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("iduser", mt.iduser);
        values.put("_idpreset", mt._idpreset);
        values.put("namesp", mt.namesp);
        values.put("price", mt.price);
        values.put("content", mt.content);
        values.put("path", mt.path);

        long row =  db.insert("card", null, values);
        if (row<0){
            return false;
        }
        return true;

    }

    public ArrayList<Card> getcard() {
        ArrayList<Card> ds = new ArrayList<Card>();

        db = dbh1.getReadableDatabase();
        Cursor c = db.query("card", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String iduser = c.getString(0);
                String idpreset = c.getString(1);
                String namesp = c.getString(2);
                double price = c.getDouble(3);
                String content = c.getString(4);
                String path = c.getString(5);



                Card mt = new Card(iduser,price, content, idpreset, namesp, path);
                ds.add(mt);

            } while (c.moveToNext());
        }


        return ds;
    }

        public void xoa_card(String id) {
        db = dbh1.getWritableDatabase();

        db.delete("card",
                "_idpreset=?",
                new String[]{id + ""});
    }

    public void xoa_card_all() {
        db = dbh1.getWritableDatabase();

        db.delete("card",null,null);
    }

    public JSONArray getResult(){

        db = dbh1.getReadableDatabase();
        Cursor cursor = db.query("card", null, null, null, null, null, null);
        JSONArray resultSet     = new JSONArray();

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for( int i=0 ;  i< totalColumn ; i++ )
            {
                if( cursor.getColumnName(i) != null )
                {
                    try
                    {
                        if( cursor.getString(i) != null )
                        {
//                            String iduser = cursor.getString(0);
//                            String idpreset = cursor.getString(1);
//                            String namesp = cursor.getString(2);
//                            double price = cursor.getDouble(3);
//                            String content = cursor.getString(4);
//                            String path = cursor.getString(5);

                            //Log.d("TAG_NAME", cursor.getString(i) );
                            rowObject.put(cursor.getColumnName(i) ,  cursor.getString(i) );
                        }
                        else
                        {
                            rowObject.put( cursor.getColumnName(i) ,  "" );
                        }
                    }
                    catch( Exception e )
                    {
                        Log.d("TAG_NAME", e.getMessage()  );
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString() );
        return resultSet;


    }

    public int getTotalExpenses()
    {
        int total = 0;
        db=dbh1.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(price) FROM card", null);
        if (cursor.moveToFirst())
        {
            total = cursor.getInt(0);
        }
        while (cursor.moveToNext());
        return total;
    }
    public ArrayList<String> getArrayId(){
        ArrayList<String> dsid = new ArrayList<String>();
        db = dbh1.getReadableDatabase();
        Cursor c = db.query("card", null, null, null, null, null, null);

        try {
            // read data from the cursor in here
            if (c.moveToFirst()) {
                do {
                    String idpreset = c.getString(1);
                    dsid.add(idpreset);

                } while (c.moveToNext());
            }
        } finally {
            c.close();
        }
        return dsid;
    }




//
//    public void sua_muc_thu(Muc_thu mt) {
//
//        db = dbh1.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("ten_muc_thu", mt.ten_muc_thu);
//        values.put("so_tien", mt.so_tien);
//        values.put("don_vi", mt.don_vi);
//        values.put("ngay_thu", mt.ngay_nhap);
//        values.put("gio_thu", mt.thoi_gian_nhap);
//        values.put("danh_gia", mt.danh_gia);
//        values.put("id_loaithu", mt.id_loai_thu);
//
//        db.update("khoan_thu",
//                values,
//                "id_thu=?",
//                new String[]{mt.id + ""});
//    }
//
//    public void xoa_muc_thu(int id) {
//        db = dbh1.getWritableDatabase();
//
//        db.delete("khoan_thu",
//                "id_thu=?",
//                new String[]{id + ""});
//    }
//    public ArrayList<Muc_thu> history(String startdate, String enddate) {
//        ArrayList<Muc_thu> ds = new ArrayList<Muc_thu>();
//        db = dbh1.getWritableDatabase();
//      //  Cursor mCursor = db.rawQuery("SELECT * FROM "+ KK_AIRLINEBOOK +
//               // " WHERE " + KEY_Bookingdate + " BETWEEN '" + startdate + "' AND '" + enddate + "'", null);
//        Cursor c = db.rawQuery("SELECT * FROM khoan_thu WHERE ngay_thu BETWEEN '"+startdate+"' AND  '"+enddate+"' ", null);
//        if (c.moveToFirst()) {
//            do {
//                int id = c.getInt(0);
//                String ten_muc_thu = c.getString(1);
//                double sotien = c.getDouble(2);
//                String donvi = c.getString(3);
//                String ngaynhap = c.getString(4);
//                String gionhap = c.getString(5);
//                int danhgia = c.getInt(6);
//                int id_loai_thu = c.getInt(7);
//
//
//                Muc_thu mt = new Muc_thu(id, ten_muc_thu, sotien, donvi, ngaynhap, gionhap, danhgia, id_loai_thu);
//                ds.add(mt);
//
//            } while (c.moveToNext());
//        }
//        return ds;
//    }


}
