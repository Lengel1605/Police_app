package DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.police_app.NewDataBaseHelper;
import com.example.david.police_app.NewPoliceDB;

import java.util.ArrayList;
import java.util.List;

import Constructors.Detail;
import Constructors.Intervention;
import Constructors.Officer;
import Constructors.Team;

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
    public long createOfficer(Officer officer){
        long id;
        ContentValues values = new ContentValues();
        values.put(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME, officer.getFirstname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_LASTNAME, officer.getLastname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_PHONE, officer.getPhone());
        values.put(NewPoliceDB.TableOfficer.OFFICER_TYPE, officer.getType());

        id = this.db.insert(NewPoliceDB.TableOfficer.TABLE_NAME_OFFICER, null, values);

        return id;
    }

    /**
     * Find one Officer by Id
     */
    public Officer getOfficerById(int id){
        String sql = "SELECT * FROM " + NewPoliceDB.TableOfficer.TABLE_NAME_OFFICER +
                " WHERE " + NewPoliceDB.TableOfficer.OFFICER_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

       Officer officer = new Officer();
        officer.setIdOfficer(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID)));
        officer.setFirstname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME)));
        officer.setLastname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_LASTNAME)));
        officer.setPhone(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_PHONE)));
        officer.setType(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_TYPE)));

        return officer;
    }

    /**
     * Find one Officer by Lastname
     */
    public Officer getOfficerByLastname(String lastname){
        String sql = "SELECT * FROM " + NewPoliceDB.TableOfficer.TABLE_NAME_OFFICER +
                " WHERE " + NewPoliceDB.TableOfficer.OFFICER_LASTNAME + " = '" + lastname + "'";

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Officer officer = new Officer();
        officer.setIdOfficer(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID)));
        officer.setFirstname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME)));
        officer.setLastname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_LASTNAME)));
        officer.setPhone(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_PHONE)));
        officer.setType(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_TYPE)));

        return officer;
    }

    /**
     * Get all Officers
     */
    public List<Officer> getAllOfficers(){
        List<Officer> officers = new ArrayList<Officer>();
        String sql = "SELECT * FROM " + NewPoliceDB.TableOfficer.TABLE_NAME_OFFICER + " ORDER BY " + NewPoliceDB.TableOfficer.OFFICER_LASTNAME;

        Cursor cursor = this.db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                Officer officer = new Officer();
                officer.setIdOfficer(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_ID)));
                officer.setFirstname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME)));
                officer.setLastname(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_LASTNAME)));
                officer.setPhone(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_PHONE)));
                officer.setType(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableOfficer.OFFICER_TYPE)));


                officers.add(officer);
            } while(cursor.moveToNext());
        }

        return officers;
    }

    /**
     *  Update a Officer
     */
    public int updateOfficer(Officer officer){
        ContentValues values = new ContentValues();
        values.put(NewPoliceDB.TableOfficer.OFFICER_FIRSTNAME, officer.getFirstname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_LASTNAME, officer.getLastname());
        values.put(NewPoliceDB.TableOfficer.OFFICER_PHONE, officer.getPhone());
        values.put(NewPoliceDB.TableOfficer.OFFICER_TYPE, officer.getType());

            return this.db.update(NewPoliceDB.TableOfficer.TABLE_NAME_OFFICER, values, NewPoliceDB.TableOfficer.OFFICER_ID + " = ?",
                new String[] { String.valueOf(officer.getIdOfficer()) });
    }




    /**
     * Delete a Officer - this will also delete all records
     * for the officer
     */

    public void deleteOfficer(long id) {

        TeamDataSource tds = new TeamDataSource(context);
        InterventionDataSource ids = new InterventionDataSource(context);
        DetailDataSource dds = new DetailDataSource(context);
        //get all records of the officer
        List<Team> teams = tds.getAllTeamsByOfficer(id);
        List<Intervention> interventions = ids.getAllInterventionsByOfficer(id);
        List<Detail> details = dds.getAllDetailsByOfficer(id);

        for (Team team : teams) {
            tds.deleteTeam(team.getIdTeam());
        }

        for (Intervention intervention : interventions) {
            ids.deleteIntervention(intervention.getIdIntervention());
        }

        for (Detail detail : details) {
            dds.deletedetail(detail.getIdDetail());
        }

        //delete the user
        this.db.delete(NewPoliceDB.TableOfficer.TABLE_NAME_OFFICER, NewPoliceDB.TableOfficer.OFFICER_ID + " = ?",
                new String[]{String.valueOf(id)});

    }


}



