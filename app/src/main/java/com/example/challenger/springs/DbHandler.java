package com.example.challenger.springs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by CHALLENGER on 11/10/2016.
 */
public class DbHandler extends SQLiteOpenHelper {
    String vivian;
    private static String database_name="the_songs";
    private static int database_version=1;

    private static String item_table="enyimba";
    private static String key_item_id="id";
    private static String key_item_name="title";
    private static String key_item_body="body";


    public DbHandler(Context context) {
        super(context, database_name, null, database_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_hymns_table = "CREATE TABLE " + item_table + "("
                + key_item_id + " INTEGER PRIMARY KEY," + key_item_name + " TEXT,"
                + key_item_body + " TEXT )";
        db.execSQL(create_hymns_table);
            }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+item_table);
        onCreate(db);

    }


    public void close_db() throws SQLException{
        this.close();
    }

    public ArrayList<Song> getAllSongs(String item_name) {
        ArrayList<Song> gottenSongs=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1="SELECT * FROM "+item_table+" WHERE "+key_item_name+ " LIKE '%"+item_name+"%'";
        Cursor c1=db.rawQuery(query1,null);

        while(c1.moveToNext()){
            Song nu =new Song(c1.getInt(0),c1.getString(1),c1.getString(2));
            gottenSongs.add(nu);
        }

        return gottenSongs;
    }

    public Song getSongById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "SELECT * FROM " + item_table + " WHERE " + key_item_id + "=" + id + "";
        Cursor c1 = db.rawQuery(query1, null);

        Song nu = null;
        while (c1.moveToNext()) {
            nu = new Song(c1.getInt(0), c1.getString(1), c1.getString(2));
        }
        return nu;
    }

    // checking wether a last song exists
    public int doesSongExist(){
        int id =1;
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "SELECT * FROM " + item_table + " WHERE " + key_item_id + "=" +id+ "";
        Cursor c1 = db.rawQuery(query1, null);

       int nu = c1.getCount();
        return nu;
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> gottenSongs=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query1="SELECT * FROM "+item_table;
        Cursor c1=db.rawQuery(query1,null);

        while(c1.moveToNext()){
            Song nu =new Song(c1.getInt(0),c1.getString(1),c1.getString(2));
            gottenSongs.add(nu);
        }

        return gottenSongs;
    }

    void addContact(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(key_item_id, song.getId()); //song no
        values.put(key_item_name, song.getSong_title()); // song title
        values.put(key_item_body, song.getSong_body()); // song body

        // Inserting Row
        db.insert(item_table, null, values);
        db.close(); // Closing database connection
    }
    String getSong(String nu_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query1="SELECT "+key_item_body+ "FROM "+item_table+" WHERE "+key_item_name+ "='" + nu_name +"'";
        Cursor c1=db.rawQuery(query1,null);
        if(c1 != null){
           vivian =  c1.getString(0);
        }
        return vivian;

    }
}


