package logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "place_data";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PLACES = "places";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_ADDRESS_TITLE = "address_title";
    private static final String KEY_ADDRESS_STREET = "address_street";
    private static final String KEY_ELEVATION = "elevation";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";



    public DatabaseHandler(Context con){
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_PLACES + "(" + KEY_NAME + " TEXT PRIMARY KEY," + KEY_CATEGORY + " TEXT," + KEY_DESCRIPTION + " TEXT," +
                KEY_ADDRESS_TITLE + " TEXT," + KEY_ADDRESS_STREET + " TEXT," + KEY_ELEVATION + " REAL," + KEY_LONGITUDE + " REAL," + KEY_LATITUDE + " REAL)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
        onCreate(db);

    }

    public void addPlace(PlaceDescription place){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, place.getName());
        cValues.put(KEY_CATEGORY, place.getCategory());
        cValues.put(KEY_DESCRIPTION, place.getDescription());
        cValues.put(KEY_ADDRESS_TITLE, place.getAddressTitle());
        cValues.put(KEY_ADDRESS_STREET, place.getAddressStreet());
        cValues.put(KEY_ELEVATION, place.getElevation());
        cValues.put(KEY_LATITUDE, place.getLatitude());
        cValues.put(KEY_LONGITUDE, place.getLongtitude());
        db.insert(TABLE_PLACES, null, cValues);
        db.close();
    }

    public PlaceDescription getPlace(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_PLACES, new String[] {KEY_NAME, KEY_CATEGORY, KEY_DESCRIPTION, KEY_ADDRESS_TITLE, KEY_ADDRESS_STREET, KEY_ELEVATION, KEY_LATITUDE, KEY_LONGITUDE}, KEY_NAME + "=?",
                new String[] {String.valueOf(name)}, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }

            PlaceDescription description = new PlaceDescription();

            description.setName(c.getString(0));
            description.setCategory(c.getString(1));
            description.setDescription(c.getString(2));
            description.setAddressTitle(c.getString(3));
            description.setAddressStreet(c.getString(4));
            description.setElevation(c.getDouble(5));
            description.setLatitude(c.getDouble(6));
            description.setLongtitude(c.getDouble(7));




        return description;
    }

    public boolean placeExists(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_PLACES, new String[] {KEY_NAME}, KEY_NAME + "=?",
                new String[] {String.valueOf(name)}, null, null, null, null);
        if(c != null) {
            System.out.println("PLACE EXISTS");
            return  true;
        }else{
            System.out.println("PLACE DOES NOT EXIST");
            return false;

        }

    }

    public PlaceLibrary getPlaceLibrary(){
        PlaceLibrary placeLibrary = new PlaceLibrary();

        String getAllQueary = "SELECT * FROM " + TABLE_PLACES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(getAllQueary, null);
        if(c.moveToFirst()){
            do{
                PlaceDescription description = new PlaceDescription();
                description.setName(c.getString(0));
                description.setCategory(c.getString(1));
                description.setDescription(c.getString(2));
                description.setAddressTitle(c.getString(3));
                description.setAddressStreet(c.getString(4));
                description.setElevation(c.getDouble(5));
                description.setLatitude(c.getDouble(6));
                description.setLongtitude(c.getDouble(7));
                placeLibrary.addPlace(description);
            } while(c.moveToNext());
        }
        return placeLibrary;




    }

    public void deletePlace(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLACES, KEY_NAME + " = ?",
                new String[] { String.valueOf(name) });
        db.close();
    }


}
