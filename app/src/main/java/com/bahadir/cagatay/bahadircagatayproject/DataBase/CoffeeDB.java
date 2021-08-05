package com.bahadir.cagatay.bahadircagatayproject.DataBase;

import android.content.ContentValues;
import android.database.Cursor;

import com.bahadir.cagatay.bahadircagatayproject.Coffee;

import java.util.ArrayList;

public class CoffeeDB {

    public static final String TABLE_NAME ="coffee";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_BRAND = "cofName";
    public static final String FIELD_MODEL = "price";


    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+TABLE_NAME+" ("+FIELD_ID +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+ FIELD_BRAND +" text, "+ FIELD_MODEL +" text "+" );";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Coffee> getAllCars(DatabaseHelper db){

        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        //Cursor cursor  db.getAllRecordsMethod2("SELECT * FROM "+TABLE_NAME, null)
        ArrayList<Coffee> data=new ArrayList<>();
        Coffee coff = null;
        while (cursor.moveToNext()) {
           int id = cursor.getInt(0);
           String cofName = cursor.getString(1);
           String cofPrice = cursor.getString(2);


           coff= new Coffee(id, cofName, cofPrice);
           data.add(coff);
        }
        return data;
    }


    public static long insertCar(DatabaseHelper db, String cofName, String price){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_BRAND, cofName);
        contentValues.put(FIELD_MODEL, price);

        long res = db.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean deleteCar(DatabaseHelper db, int id){
        String where = FIELD_ID +" = "+ id;
        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }
}
