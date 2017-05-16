package DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.david.police_app.NewDataBaseHelper;
import com.example.david.police_app.NewPoliceDB;
import com.example.lionel.police_app.backend.constructors.officerApi.OfficerApi;
import com.example.lionel.police_app.backend.constructors.officerApi.model.Officer;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by David on 27.04.2017.
 */

public class OfficerDataSource {

    private SQLiteDatabase db;
    private Context context;

    public OfficerDataSource(Context context){
        NewDataBaseHelper sqliteHelper = NewDataBaseHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;

    }

    /**
     * Insert a new officer
     */
    public void createOfficer(Officer officer){
        /*long id;
        ContentValues values = new ContentValues();
        values.put(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME, officer.getFirstname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_LASTNAME, officer.getLastname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_PHONE, officer.getPhone());
        values.put(NewPoliceDB.TableOfficer.OFFICER_TYPE, officer.getType());
        values.put(NewPoliceDB.TableOfficer.OFFICER_ID_TEAM, officer.getIdTeam());


        id = this.db.insert(NewPoliceDB.TableOfficer.TABLE_OFFICER, null, values);

        return id;*/
        new EndpointsAsyncTask(officer).execute();
    }

    /**
     * Find one Officer by Id
     */
    public Officer getOfficerById(int id){
        String sql = "SELECT * FROM " + NewPoliceDB.TableOfficer.TABLE_OFFICER +
                " WHERE " + NewPoliceDB.TableOfficer.OFFICER_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

       Officer officer = new Officer();
        officer.setIdOfficer(cursor.getLong(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID)));
        officer.setFirstname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME)));
        officer.setLastname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_LASTNAME)));
        officer.setPhone(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_PHONE)));
        officer.setType(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_TYPE)));
        officer.setIdTeam(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID_TEAM)));


        return officer;
    }

    /**
     * Find one Officer by team
     */
    public Officer getOfficerByTeam(int id_team){
        String sql = "SELECT * FROM " + NewPoliceDB.TableOfficer.TABLE_OFFICER +
                " WHERE " + NewPoliceDB.TableOfficer.OFFICER_ID_TEAM + " = " + id_team;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Officer officer = new Officer();
        officer.setIdOfficer(cursor.getLong(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID)));
        officer.setFirstname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME)));
        officer.setLastname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_LASTNAME)));
        officer.setPhone(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_PHONE)));
        officer.setType(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_TYPE)));
        officer.setIdTeam(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID_TEAM)));

        return officer;
    }

    /**
     * Get all Officers
     */
    public List<Officer> getAllOfficers(){
        List<Officer> officers = new ArrayList<Officer>();
        /*String sql = "SELECT * FROM " + NewPoliceDB.TableOfficer.TABLE_OFFICER + " ORDER BY " + NewPoliceDB.TableOfficer.OFFICER_LASTNAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Officer officer = new Officer();
                officer.setIdOfficer(cursor.getLong(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID)));
                officer.setFirstname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME)));
                officer.setLastname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_LASTNAME)));
                officer.setPhone(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_PHONE)));
                officer.setType(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_TYPE)));
                officer.setIdTeam(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID_TEAM)));


                officers.add(officer);
            } while(cursor.moveToNext());
        }*/
        try {
            officers = new EndpointsAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return officers;
    }

    /**
     * Insert a new officer

    public long createOfficer(Officer officer){
        long id;
        ContentValues values = new ContentValues();
        values.put(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME, officer.getFirstname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_LASTNAME, officer.getLastname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_PHONE, officer.getPhone());
        values.put(NewPoliceDB.TableOfficer.OFFICER_TYPE, officer.getType());
        values.put(NewPoliceDB.TableOfficer.OFFICER_ID_TEAM, officer.getId_Team());


        id = this.db.insert(NewPoliceDB.TableOfficer.TABLE_OFFICER, null, values);

        return id;
    }
     */



    /**
     *  Update a Officer
     */
    public int updateOfficer(Officer officer){
        ContentValues values = new ContentValues();
        values.put(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME, officer.getFirstname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_LASTNAME, officer.getLastname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_PHONE, officer.getPhone());
        values.put(NewPoliceDB.TableOfficer.OFFICER_TYPE, officer.getType());
        values.put(NewPoliceDB.TableOfficer.OFFICER_ID_TEAM, officer.getType());

            return this.db.update(NewPoliceDB.TableOfficer.TABLE_OFFICER, values, NewPoliceDB.TableOfficer.OFFICER_ID + " = ?",
                new String[] { String.valueOf(officer.getIdOfficer()) });
    }

    /**
     * Delete a Officer - this will also delete all records
     * for the officer*/


    public void deleteOfficer(Officer officer) {

        //delete the officer
        this.db.delete(NewPoliceDB.TableOfficer.TABLE_OFFICER, NewPoliceDB.TableOfficer.OFFICER_PHONE + " = ?",
                new String[]{String.valueOf(officer.getPhone())});

    }
}

class EndpointsAsyncTask extends AsyncTask<Void, Void, List<Officer>> {
    private static OfficerApi offApi = null;
    private static final String TAG = EndpointsAsyncTask.class.getName();
    private Officer officer;

    EndpointsAsyncTask(){}

    EndpointsAsyncTask(Officer officer){
        this.officer = officer;
    }

    @Override
    protected List<Officer> doInBackground(Void... params) {

        if(offApi == null){
            // Only do this once
            OfficerApi.Builder builder = new OfficerApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://policeapp-167213.appspot.com/_ah/api/");
            offApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(officer != null){
                offApi.insert(officer).execute();
                Log.i(TAG, "insert officer");
            }
            // and for instance return the list of all officers
            return offApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Officer>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Officer> result){

        /*if(result != null) {
            for (Officer officer : result) {
                Log.i(TAG, "First name: " + officer.getFirstname() + " Last name: "
                        + officer.getLastname());

                for (Phone phone : officer.getPhones()) {
                    Log.i(TAG, "Phone number: " + phone.getNumber() + " Type: " + phone.getType());
                }
            }
        }*/
    }
}
