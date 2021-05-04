package com.example.iii.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller extends SQLiteOpenHelper {
    public Controller(Context context) {
        super(context, "ProdiTI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table teman (id integer primary key,nama text,telpon text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists teman");
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onUpgrade(sqLiteDatabase,i, i1);
    }
//    insert data
    public void insertData(HashMap<String,String> queryvalues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama",queryvalues.get("nama"));
        nilai.put("telpon",queryvalues.get("telpon"));
        basisdata.insert("teman",null,nilai);
        basisdata.close();
    }

    public ArrayList<Friends> getAllTeman(){
        ArrayList<Friends> kontakList = new ArrayList<>();
        String selectQuery = "Select * from teman";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Friends kontak = new Friends();
                kontak.setId(cursor.getString(0));
                kontak.setNama(cursor.getString(1));
                kontak.setTelpon(cursor.getString(2));

                kontakList.add(kontak);
            } while (cursor.moveToNext());
        }

        return kontakList;
    }

    public void deleteTeman( String id ){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("teman", "id=?", new String[] {id});
        db.close();

    }
}
