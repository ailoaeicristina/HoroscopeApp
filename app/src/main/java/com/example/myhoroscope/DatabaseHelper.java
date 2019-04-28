package com.example.myhoroscope;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "zodiac_db";
    private static final String TABLE_SIGNS = "zodiac_table";
    private static final String KEY_ID = "ID";
    private static final String KEY_SIGN = "sign";
    private static final String KEY_DESCRIPTION = "description";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createZodiacTable = "CREATE TABLE " + TABLE_SIGNS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_SIGN + " TEXT, " + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(createZodiacTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SIGNS);

        onCreate(db);
    }

    public void addZodiacSign(ZodiacSign zodiacSign) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SIGN, zodiacSign.getSign());
        values.put(KEY_DESCRIPTION, zodiacSign.getDescription());

        db.insert(TABLE_SIGNS, null, values);
        db.close();
    }

    ZodiacSign getZodiacSign(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SIGNS, new String[] {KEY_ID, KEY_SIGN, KEY_DESCRIPTION}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null,  null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        ZodiacSign zodiacSign = new ZodiacSign(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return zodiacSign;
    }

    public List<ZodiacSign> getAllZodiacSigns() {

        List<ZodiacSign> zodiacSigns = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SIGNS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                ZodiacSign zodiacSign = new ZodiacSign();
                zodiacSign.setId(Integer.parseInt(cursor.getString(0)));
                zodiacSign.setSign(cursor.getString(1));
                zodiacSign.setDescription(cursor.getString(2));

                zodiacSigns.add(zodiacSign);
            } while (cursor.moveToNext());
        }

        return zodiacSigns;
    }

    public int updateZodiacSign(ZodiacSign zodiacSign) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESCRIPTION, zodiacSign.getDescription());

        return db.update(TABLE_SIGNS, values, KEY_ID + "=?",
                new String[] {String.valueOf(zodiacSign.getId())});
    }

    public void deteleZodiacSign(ZodiacSign zodiacSign) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SIGNS, KEY_ID + "=?",
                new String[] {String.valueOf(zodiacSign.getId())});
        db.close();
    }







}
