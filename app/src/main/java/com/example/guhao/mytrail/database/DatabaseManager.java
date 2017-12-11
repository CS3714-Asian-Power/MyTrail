package com.example.guhao.mytrail.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.guhao.mytrail.data.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuo on 6/7/2017.
 */

public class DatabaseManager {
    private SQLiteOpenHelper dbOpenHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void open() {
        database = dbOpenHelper.getWritableDatabase();
    }

    public void close() {
        dbOpenHelper.close();
    }

    public void insertPlaceInfo(String name, String place_id, float rating, String thumbnail, double longitude, double latitude ){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_NAME, name);
        values.put(DBOpenHelper.COLUMN_PLACE_ID, place_id);
        values.put(DBOpenHelper.COLUMN_RATING, rating);
        values.put(DBOpenHelper.COLUMN_THUMBNAIL, thumbnail);
        values.put(DBOpenHelper.COLUMN_LONGITUDE, longitude);
        values.put(DBOpenHelper.COLUMN_LATITUDE, latitude);
        database.insert(DBOpenHelper.PLACE_TABLE, null, values);

    }
    public void insertPlaceFavorite(String name, String place_id, float rating, String thumbnail, double longitude, double latitude ){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_NAME, name);
        values.put(DBOpenHelper.COLUMN_PLACE_ID, place_id);
        values.put(DBOpenHelper.COLUMN_RATING, rating);
        values.put(DBOpenHelper.COLUMN_THUMBNAIL, thumbnail);
        values.put(DBOpenHelper.COLUMN_LONGITUDE, longitude);
        values.put(DBOpenHelper.COLUMN_LATITUDE, latitude);
        database.insert(DBOpenHelper.FAVORITE_TABLE, null, values);

    }


//    public void updateMovie(String[] title, int like){
//        ContentValues values = new ContentValues();
//        values.put(DBOpenHelper.COLUMN_NAME_LIKE, like);
//        database.update(DBOpenHelper.TABLE_NAME, values,DBOpenHelper.COLUMN_NAME_TITLE + " LIKE ?", title );
//    }
    public Place getPlace (String[] key, int tableID){
        String table_name;
        if(tableID == DBOpenHelper.FAVORITE_TABLE_ID){
            table_name = DBOpenHelper.FAVORITE_TABLE;
        }
        else {
            table_name = DBOpenHelper.PLACE_TABLE;
        }
        Cursor cursor = database.query(table_name,
                new String[]{
                        DBOpenHelper.COLUMN_ID,
                        DBOpenHelper.COLUMN_NAME,
                        DBOpenHelper.COLUMN_PLACE_ID,
                        DBOpenHelper.COLUMN_RATING,
                        DBOpenHelper.COLUMN_LONGITUDE,
                        DBOpenHelper.COLUMN_LATITUDE,
                        DBOpenHelper.COLUMN_THUMBNAIL,


                }, DBOpenHelper.COLUMN_NAME + "=?", key , null, null, null, null);
        cursor.moveToFirst();
        Place place = new Place();
        while (!cursor.isAfterLast()) {

            place.setName(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME)));
            place.setPlace_id(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_PLACE_ID)));
            place.setRating(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_RATING)));
            place.setThumbnail(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_THUMBNAIL)));
            place.setLongitude(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_LONGITUDE)));
            place.setLatitude(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_LATITUDE)));
            place.setId(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_ID)));

            cursor.moveToNext();

        }
        return place;

    }
    /*public List<Place> filterMovies (){
        String[] likes = {"1"};
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME,
                new String[]{
                        DBOpenHelper.COLUMN_ID,
                        DBOpenHelper.COLUMN_NAME_TITLE,
                        DBOpenHelper.COLUMN_NAME_RELEASE_DATE,
                        DBOpenHelper.COLUMN_NAME_VOTE,
                        DBOpenHelper.COLUMN_NAME_POPUlARITY,
                        DBOpenHelper.COLUMN_NAME_OVERVIEW,
                        DBOpenHelper.COLUMN_NAME_POSTER,
                        DBOpenHelper.COLUMN_NAME_BACKDROP,
                        DBOpenHelper.COLUMN_NAME_LIKE

                }, DBOpenHelper.COLUMN_NAME_LIKE + "= ?", likes , null, null, null, null);
        cursor.moveToFirst();
        Movie movie = new Movie();
        List<Movie> result =new  ArrayList<Movie>();
        while (!cursor.isAfterLast()) {
            movie = new Movie();
            movie.setTitle(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_TITLE)));
            movie.setDate(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_RELEASE_DATE)));
            movie.setRating(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_VOTE)));
            movie.setPopularity(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_POPUlARITY)));
            movie.setPoster(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_POSTER)));
            movie.setBackdrop(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_BACKDROP)));
            movie.setOverview(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_OVERVIEW)));
            movie.setId(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_ID)));
            movie.setLike(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_LIKE)));
            cursor.moveToNext();
            result.add(movie);

        }
        return result;

    }*/
    /*public List<Place> getAllRecordsOrderedBy(String key) {
        String order = " DESC";
        if( key == DBOpenHelper.COLUMN_NAME_TITLE){
            order = " ASC";
        }
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME,
                new String[]{
                        DBOpenHelper.COLUMN_ID,
                        DBOpenHelper.COLUMN_NAME_TITLE,
                        DBOpenHelper.COLUMN_NAME_RELEASE_DATE,
                        DBOpenHelper.COLUMN_NAME_VOTE,
                        DBOpenHelper.COLUMN_NAME_POPUlARITY,
                        DBOpenHelper.COLUMN_NAME_OVERVIEW,
                        DBOpenHelper.COLUMN_NAME_POSTER,
                        DBOpenHelper.COLUMN_NAME_BACKDROP,
                        DBOpenHelper.COLUMN_NAME_LIKE

                }, null, null, null, null, key+order, null);
        cursor.moveToFirst();
        Movie movie;
        List<Movie> result =new  ArrayList<Movie>();
        while (!cursor.isAfterLast()) {
            movie = new Movie();
            movie.setTitle(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_TITLE)));

            movie.setDate(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_RELEASE_DATE)));
            movie.setRating(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_VOTE)));
            movie.setPopularity(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_POPUlARITY)));
            movie.setPoster(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_POSTER)));
            movie.setBackdrop(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_BACKDROP)));
            movie.setOverview(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_OVERVIEW)));
            movie.setId(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_ID)));
            movie.setLike(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME_LIKE)));
            cursor.moveToNext();
            result.add(movie);
        }
        return result;
    }*/

    public List<Place> getAllRecords(int tableID) {
        String table_name;
        if(tableID == DBOpenHelper.FAVORITE_TABLE_ID){
            table_name = DBOpenHelper.FAVORITE_TABLE;
        }
        else {
            table_name = DBOpenHelper.PLACE_TABLE;
        }
        Cursor cursor = database.query(table_name,
                new String[]{
                        DBOpenHelper.COLUMN_ID,
                        DBOpenHelper.COLUMN_NAME,
                        DBOpenHelper.COLUMN_PLACE_ID,
                        DBOpenHelper.COLUMN_RATING,
                        DBOpenHelper.COLUMN_LONGITUDE,
                        DBOpenHelper.COLUMN_LATITUDE,
                        DBOpenHelper.COLUMN_THUMBNAIL,

                }, null, null, null, null, null, null);
        cursor.moveToFirst();
        Place place;
        List<Place> result =new ArrayList<Place>();
        while (!cursor.isAfterLast()) {
            place = new Place();
            place.setName(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_NAME)));
            place.setPlace_id(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_PLACE_ID)));
            place.setRating(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_RATING)));
            place.setThumbnail(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_THUMBNAIL)));
            place.setLongitude(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_LONGITUDE)));
            place.setLatitude(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_LATITUDE)));
            place.setId(
                    cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLUMN_ID)));
            cursor.moveToNext();
            result.add(place);
        }
        return result;
    }

    public void deleteAllResult() {
        if (database.isOpen()) {
            database.execSQL("DELETE FROM " + DBOpenHelper.PLACE_TABLE );
        }
    }

    public void deleteAllFavorite() {
        if (database.isOpen()) {
            database.execSQL("DELETE FROM " + DBOpenHelper.FAVORITE_TABLE );
        }
    }
}
