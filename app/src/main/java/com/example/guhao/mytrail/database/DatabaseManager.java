package com.example.guhao.mytrail.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

/*    public void insertMovieInfo(String title, String release, float vote, float popularity, String overview, String poster, String backdrop, int like) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_NAME_TITLE, title);
        values.put(DBOpenHelper.COLUMN_NAME_RELEASE_DATE, release);
        values.put(DBOpenHelper.COLUMN_NAME_VOTE, vote);
        values.put(DBOpenHelper.COLUMN_NAME_POPUlARITY, popularity);
        values.put(DBOpenHelper.COLUMN_NAME_OVERVIEW, overview);
        values.put(DBOpenHelper.COLUMN_NAME_POSTER, poster);
        values.put(DBOpenHelper.COLUMN_NAME_BACKDROP, backdrop);
        values.put(DBOpenHelper.COLUMN_NAME_LIKE, like);
        database.insert(DBOpenHelper.TABLE_NAME, null, values);
    }
    public void updateMovie(String[] title, int like){
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_NAME_LIKE, like);
        database.update(DBOpenHelper.TABLE_NAME, values,DBOpenHelper.COLUMN_NAME_TITLE + " LIKE ?", title );
    }
    public Movie getTitle (String[] key){
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

                }, DBOpenHelper.COLUMN_NAME_TITLE + "=?", key , null, null, null, null);
        cursor.moveToFirst();
        Movie movie = new Movie();
        while (!cursor.isAfterLast()) {

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

        }
        return movie;

    }
    public List<Movie> filterMovies (){
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

    }
    public List<Movie> getAllRecordsOrderedBy(String key) {
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
    }

    public List<Movie> getAllRecords() {
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

                }, null, null, null, null, null, null);
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

    public void deleteAll() {
        if (database.isOpen()) {
            database.execSQL("DELETE FROM " + DBOpenHelper.PLACE_TABLE );
        }
    }
}
