package com.example.david.police_app;
/**
 * Created by David on 24.04.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "app_Police";

    // Officers table name
    private static final String TABLE_OFFICERS = "officers";
    // Teams table name
    private static final String TABLE_TEAMS = "teams";
    // Details table name
    private static final String TABLE_DETAILS = "details";
    // Interventions table name
    private static final String TABLE_INTERVENTIONS = "interventions";

    // Officers Table Columns names
    private static final String OFFICERS_ID = "id_officers";
    private static final String OFFICERS_FIRSTNAME = "officers_firstname";
    private static final String OFFICERS_LASTNAME = "officers_lastname";
    private static final String OFFICERS_PHONE = "officers_phone";
    private static final String OFFICERS_TYPE = "officers_type";

    // Teams Table Columns names
        private static final String TEAMS_ID = "id_teams";
    private static final String TEAMS_CHIEF = "teams_chef";
    private static final String TEAMS_COMPOSANTS = "teams_composants";


    // Interventions Table Columns names
    private static final String INTERVENTIONS_ID = "id_interventions";
    private static final String INTERVENTIONS_NAME = "interventions_name";
    private static final String INTERVENTIONS_PRIORITY = "interventions_priority";
    private static final String INTERVENTIONS_FINISHED = "interventions_Finished";


    // Details Table Columns names
    private static final String DETAILS_ID = "id_details";
    private static final String DETAILS_NAME = "details_name";
    private static final String DETAILS_DESCRIPTION = "details_description";
    private static final String DETAILS_DATE_BEGIN = "details_date_begin";
    private static final String DETAILS_DATE_END = "details_date_end";
    private static final String DETAILS_LOCALISATION = "details_localisation";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_OFFICERS_TABLE = "CREATE TABLE " + TABLE_TEAMS + "("
        + TEAMS_ID + " INTEGER PRIMARY KEY," + TEAMS_CHIEF + " TEXT,"
        + TEAMS_COMPOSANTS + " TEXT" + ")";
        db.execSQL(CREATE_OFFICERS_TABLE);

        String CREATE_TEAMS_TABLE = "CREATE TABLE " + TABLE_OFFICERS + "("
                + OFFICERS_ID + " INTEGER PRIMARY KEY," + OFFICERS_FIRSTNAME + " TEXT,"
                + OFFICERS_LASTNAME + " TEXT" + ")";
        db.execSQL(CREATE_OFFICERS_TABLE);

        String CREATE_INTERVENTIONS_TABLE = "CREATE TABLE " + TABLE_INTERVENTIONS + "("
                + INTERVENTIONS_ID + " INTEGER PRIMARY KEY," + INTERVENTIONS_NAME + " TEXT,"
                + INTERVENTIONS_PRIORITY + " TEXT"+ INTERVENTIONS_FINISHED + " TEXT" + ")";
        db.execSQL(CREATE_OFFICERS_TABLE);

        String CREATE_DETAILS_TABLE = "CREATE TABLE " + TABLE_DETAILS + "("
                + DETAILS_ID + " INTEGER PRIMARY KEY," + DETAILS_NAME + " TEXT,"
                + DETAILS_DESCRIPTION + " TEXT" + DETAILS_DATE_BEGIN + " TEXT"+
                DETAILS_DATE_END + " TEXT"+ DETAILS_LOCALISATION + " TEXT" + ")";
        db.execSQL(CREATE_OFFICERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFICERS + TABLE_TEAMS + TABLE_INTERVENTIONS +
        TABLE_DETAILS);

// Creating tables again
        onCreate(db);
    }

    // Adding new officer
    public void addOfficer(Officer officer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OFFICERS_ID, officer.getOfficerId()); // officer firstname
        values.put(OFFICERS_FIRSTNAME, officer.getOfficerFirstname()); // officer firstname
        values.put(OFFICERS_LASTNAME, officer.getOfficerLastname()); // officer firstname
        values.put(OFFICERS_PHONE, officer.getOfficerPhone()); // officer firstname
        values.put(OFFICERS_TYPE, officer.getOfficerType()); // officer firstname

// Inserting Row
        db.insert(TABLE_OFFICERS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one officer
    /**public Officer getOfficer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_OFFICERS,
                new String[]{
                             OFFICERS_ID,OFFICERS_FIRSTNAME, OFFICERS_LASTNAME, OFFICERS_PHONE, OFFICERS_TYPE
                            },
                OFFICERS_ID + "=?",
                new String[] {String.valueOf(id) },
                        null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Officer officer = new Officer(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));
// return shop
        return officer;
    }  **/
    // Getting All Officers
    public List<Officer> getAllOfficers() {
        List<Officer> officersList = new ArrayList<Officer>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_OFFICERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Officer officer = new Officer();
                officer.setOfficerId(cursor.getString(0));
                officer.setOfficerFirstname(cursor.getString(1));
                officer.setOfficerLastname(cursor.getString(2));
                officer.setOfficerPhone(cursor.getString(3));
                officer.setOfficerType(cursor.getString(4));
// Adding contact to list
                officersList.add(officer);
            } while (cursor.moveToNext());
        }

// return officer list
        return officersList;
    }
    // Getting officers Count
    public int getOfficersCount() {
        String countQuery = "SELECT * FROM " + TABLE_OFFICERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }
    // Updating a officer
    public int updateShop(Officer officer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OFFICERS_FIRSTNAME, officer.getOfficerFirstname());
        values.put(OFFICERS_LASTNAME, officer.getOfficerLastname());
        values.put(OFFICERS_PHONE, officer.getOfficerPhone());
        values.put(OFFICERS_TYPE, officer.getOfficerType());

// updating row
        return db.update(TABLE_OFFICERS, values, OFFICERS_ID + " = ?",
        new String[]{String.valueOf(officer.getOfficerId())});
    }

    // Deleting a officer
    public void deleteOfficer(Officer officer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OFFICERS, OFFICERS_ID + " = ?",
        new String[] { String.valueOf(officer.getOfficerId()) });
        db.close();
    }


}
