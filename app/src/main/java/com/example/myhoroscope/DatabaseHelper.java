package com.example.myhoroscope;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "zodiac_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "description";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " + COL3 + " TEXT)";
        db.execSQL(createTable);

        addData("Aries", "As the first sign in the zodiac, the presence of Aries always marks the beginning of something energetic and turbulent. " +
                "They are continuously looking for dynamic, speed and competition, always being the first in everything - from work to social gatherings. " +
                "The Sun in such high dignity gives them excellent organizational skills, so you'll rarely meet an Aries who isn't capable of finishing several things at once," +
                " often before lunch break! Their challenges show when they get impatient, aggressive and vent anger pointing it to other people.");

        addData("Taurus", "Practical and well-grounded, Taurus is the sign that harvests the fruits of labor. They feel the need to always be surrounded by love and beauty, " +
                "turned to the material world, hedonism, and physical pleasures. People born with their Sun in Taurus are sensual and tactile, " +
                "considering touch and taste the most important of all senses. Stable and conservative, this is one of the most reliable signs of the zodiac," +
                " ready to endure and stick to their choices until they reach the point of personal satisfaction.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, name);
        contentValues.put(COL3, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME;

        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
