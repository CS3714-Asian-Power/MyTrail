package com.example.guhao.mytrail.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shuo on 6/7/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyTrail2.db";
    private static final int DATABASE_VERSION = 1;
    public static final String WEATHER_TABLE  = "weather";
    public static final String PLACE_TABLE = "places";
    public static final String FAVORITE_TABLE  = "favorites";

    // column for places and favorites
    public static final String COLUMN_PLACE_ID = "place_id";
    public static final String COLUMN_NAME= "name";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_THUMBNAIL = "photo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_ADDRESS = "address";

    public static final int RESULT_TABLE_ID = 1;
    public static final int FAVORITE_TABLE_ID = 2;

    private static final String SQL_CREATE_RESULT =
            "CREATE TABLE " + PLACE_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_PLACE_ID + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_RATING + " FLOAT," +
                    COLUMN_LONGITUDE + " FLOAT," +
                    COLUMN_LATITUDE + " FLOAT," +
                    COLUMN_THUMBNAIL+ " TEXT)" ;

    private static final String SQL_CREATE_FAVORITE =
            "CREATE TABLE " + FAVORITE_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_PLACE_ID + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_RATING + " FLOAT," +
                    COLUMN_LONGITUDE + " FLOAT," +
                    COLUMN_LATITUDE + " FLOAT," +
                    COLUMN_THUMBNAIL+ " TEXT)" ;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RESULT);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLACE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVORITE_TABLE);
        onCreate(sqLiteDatabase);
    }

}
