package com.example.currencyconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class phonestorageClass extends SQLiteOpenHelper {

    private static int DATABASE_LIMIT = 10;

    private static final String DATABASE_NAME = "prevConversion.db";
    private static final String TABLE_NAME = "PREVIOUS_CONVERSIONS_TABLE";

    // DEFINE CONSTANTS TO WORK WITH MYSQL MORE EFFICIENTLY.
    private static final String COLUMN_CURFROM = "CURRENCYFROM";
    private static final String COLUMN_CURFROMAMOUNT = "CURRENCYFROMAMOUNT";
    private static final String COLUMN_CURTO = "CURTO";
    private static final String COLUMN_CURTOAMOUNT = "CURTOAMOUNT";

    // DEFAULT CONSTRUCTOR
    public phonestorageClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    // Counts number of rows in previous conversions database. Private helper method.
    private long countRowsInDataBase() {
        long rowsCount = 0;

        SQLiteDatabase db = getReadableDatabase();
        rowsCount = DatabaseUtils.queryNumEntries(db, TABLE_NAME);


        return rowsCount;
    }


    public void saveToDB(Context context, Double amountFrom, Double amountTo, String currencyFrom, String currencyTo) {

        // First, count number of entries to ensure there are less than 10.
        // If more than 10, must delete the first and shift all entires up
        Log.i("Row count: ", String.valueOf(countRowsInDataBase()));
        SQLiteDatabase db = this.getWritableDatabase();
        if (countRowsInDataBase() <= 10) {

            ContentValues cv = new ContentValues();
            cv.put(COLUMN_CURFROM, currencyFrom);
            cv.put(COLUMN_CURFROMAMOUNT, amountFrom);
            cv.put(COLUMN_CURTO, currencyTo);
            cv.put(COLUMN_CURTOAMOUNT, amountTo);

            long insert = db.insert(TABLE_NAME, null, cv);
            Log.i("Database insertion: ", String.valueOf(insert));


        } else {
            String SQLstr = "DELETE FROM " + TABLE_NAME + " WHERE ROWID IN (SELECT ROWID FROM " + TABLE_NAME + " LIMIT 1)";
            while (countRowsInDataBase() > DATABASE_LIMIT) {
                db.execSQL(SQLstr);
//                db.delete(TABLE_NAME, "ID IN (SELECT ID FROM =? LIMIT =?)", new String[]{TABLE_NAME, String.valueOf(DATABASE_LIMIT)});
            }


        }
        db.close();

    }

    public void fetchFromDBIntoList(Context context, ListView listViewPreviousConversions) {

        String queryString = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        List<String> stringListForPrevList = new ArrayList<>();
        Cursor cursor = db.rawQuery(queryString, null);

        // Only iterate through if table isn't empty.
        if (cursor.moveToFirst()) {
            do { // Fill list of strings to put into previous conversions list
                String curFrom = cursor.getString(1);
                Float curFromAmount = cursor.getFloat(2);
                String curTo = cursor.getString(3);
                Float curToAmount = cursor.getFloat(4);

                // Initialize String to write into list
                String text = String.format("%.2f %s = %.2f %s", curFromAmount, curFrom, curToAmount, curTo);
                stringListForPrevList.add(text);

            } while (cursor.moveToNext());

            for (String s : stringListForPrevList) {
                Log.i("String: ", s);
            }

            // Reverse list to sort by most recent
            Collections.reverse(stringListForPrevList);

            // Init ArrayAdapter to put into list
            ArrayAdapter<String> dataArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, stringListForPrevList);
            listViewPreviousConversions.setAdapter(dataArrayAdapter);
            cursor.close();
        }
        db.close();

    }


    // This is called the first time the database is accessed. Will init tables.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CURFROM + " TEXT, " + COLUMN_CURFROMAMOUNT + " INT, " + COLUMN_CURTO + " TEXT, " + COLUMN_CURTOAMOUNT + " INT)";
        Log.i("Table created: ", createTableStatement);
        db.execSQL(createTableStatement);
    }


    // This is called if the DB version number changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
