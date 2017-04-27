package com.example.david.police_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewDataBaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase db;

    //Infos about database
    private static final String DATABASE_NAME = "Police.db";
    private static final int DATABASE_VERSION = 1;
    private static NewDataBaseHelper instance;


    //use a singleton
    //we want always just one instance of the database
    public NewDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    public static NewDataBaseHelper getInstance(Context context){
        if(instance == null){
            instance = new NewDataBaseHelper(context.getApplicationContext());
            //Enable foreign key support
            instance.db.execSQL("PRAGMA foreign_keys = ON;");
        }
        return instance;
    }
//test
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewPoliceDB.TableOfficer.CREATE_TABLE_OFFICER);
        db.execSQL(NewPoliceDB.TableTeam.CREATE_TABLE_TEAM);
        db.execSQL(NewPoliceDB.TableIntervention.CREATE_TABLE_INTERVENTION);
        db.execSQL(NewPoliceDB.TableDetail.CREATE_TABLE_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + NewPoliceDB.TableOfficer.CREATE_TABLE_OFFICER);
        db.execSQL("DROP TABLE IF EXISTS " + NewPoliceDB.TableTeam.CREATE_TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + NewPoliceDB.TableIntervention.CREATE_TABLE_INTERVENTION);
        db.execSQL("DROP TABLE IF EXISTS " + NewPoliceDB.TableDetail.CREATE_TABLE_DETAIL);

        //create new tables/
        onCreate(db);
    }


}
