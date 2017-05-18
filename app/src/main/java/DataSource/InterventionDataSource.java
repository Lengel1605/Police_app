package DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.police_app.NewDataBaseHelper;
import com.example.david.police_app.NewPoliceDB;

import java.util.ArrayList;
import java.util.List;

import Constructors.Intervention;


public class InterventionDataSource {
    private SQLiteDatabase db;
    private Context context;


    public InterventionDataSource(Context context) {
        NewDataBaseHelper sqliteHelper = NewDataBaseHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
        this.context = context;
    }

    /**
     * Insert a new intervention
     */

    /**
     * inserts a new intervention
     */
    public long createIntervention(Intervention intervention) {

        long id;
        ContentValues values = new ContentValues();

        values.put(NewPoliceDB.TableIntervention.INTERVENTION_NAME, intervention.getInterName());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_DESCRIPTION, intervention.getIntDescription());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_DATE_BEGIN, intervention.getDateBegin());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_LOCALISATION, intervention.getLocalisation());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_ID_TEAM, intervention.getIdTeam());

        id = this.db.insert(NewPoliceDB.TableIntervention.TABLE_INTERVENTION, null, values);

        intervention.setIdIntervention(id);

        new InterventionAsyncTask(intervention).execute();

        return id;
    }

    /**
     * show all interventions
     */
    public List<Intervention> getAllInterventions() {
        List<Intervention> interventions = new ArrayList<Intervention>();
        String sql = "SELECT * FROM " + NewPoliceDB.TableIntervention.TABLE_INTERVENTION + " ORDER BY " + NewPoliceDB.TableIntervention.INTERVENTION_NAME + " DESC ";

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Intervention intervention = new Intervention();
                intervention.setIdIntervention(cursor.getLong(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_ID)));
                intervention.setInterName(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_NAME)));
                intervention.setIntDescription(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_DESCRIPTION)));
                intervention.setDateBegin(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_DATE_BEGIN)));
                intervention.setLocalisation(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_LOCALISATION)));
                intervention.setIdTeam(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_ID_TEAM)));

                interventions.add(intervention);
            } while (cursor.moveToNext());
        }

        return interventions;
    }

    /**
     * show interventions by officer id
     */
    public Intervention getInterventionById(long id) {
        String sql = "SELECT * FROM " + NewPoliceDB.TableIntervention.TABLE_INTERVENTION +
                " WHERE " + NewPoliceDB.TableIntervention.INTERVENTION_ID + " = " + id;

        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Intervention intervention = new Intervention();
        intervention.setIdIntervention(cursor.getLong(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_ID)));
        intervention.setInterName(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_NAME)));
        intervention.setIntDescription(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_DESCRIPTION)));
        intervention.setDateBegin(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_DATE_BEGIN)));
        intervention.setLocalisation(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_LOCALISATION)));
        intervention.setIdTeam(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_ID_TEAM)));


        return intervention;
    }


    /**
     * Get all Interventions by Officer
     */
    public List<Intervention> getAllInterventionsByOfficer(long id) {
        List<Intervention> interventions = new ArrayList<Intervention>();
        String sql = "SELECT * FROM " + NewPoliceDB.TableIntervention.TABLE_INTERVENTION + " r, "
                + NewPoliceDB.TableOfficer.TABLE_OFFICER + " p"
                + " WHERE p." + NewPoliceDB.TableOfficer.OFFICER_ID + " = " + id
                + " ORDER BY " + NewPoliceDB.TableIntervention.INTERVENTION_NAME + " DESC";


        Cursor cursor = this.db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Intervention intervention = new Intervention();
                intervention.setIdIntervention(cursor.getLong(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_ID)));
                intervention.setInterName(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_NAME)));
                intervention.setIntDescription(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_DESCRIPTION)));
                intervention.setDateBegin(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_DATE_BEGIN)));
                intervention.setLocalisation(cursor.getString(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_LOCALISATION)));
                intervention.setIdTeam(cursor.getInt(cursor.getColumnIndex(NewPoliceDB.TableIntervention.INTERVENTION_ID_TEAM)));

                interventions.add(intervention);
            } while (cursor.moveToNext());
        }

        return interventions;
    }

    public long updateIntervention(Intervention intervention) {
        long id;
        ContentValues values = new ContentValues();
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_NAME, intervention.getInterName());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_DESCRIPTION, intervention.getIntDescription());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_DATE_BEGIN, intervention.getDateBegin());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_LOCALISATION, intervention.getLocalisation());
        values.put(NewPoliceDB.TableIntervention.INTERVENTION_ID_TEAM, intervention.getIdTeam());

        id = this.db.update(NewPoliceDB.TableIntervention.TABLE_INTERVENTION, values, NewPoliceDB.TableIntervention.INTERVENTION_ID + " = ?",
                new String[]{String.valueOf(intervention.getIdIntervention())});

        new InterventionAsyncTask(intervention.getIdIntervention(), intervention).execute();

        return id;
    }

    /**
     * Delete a Intervention
     */
    public void deleteIntervention(Intervention intervention) {

        //delete the officer
        this.db.delete(NewPoliceDB.TableIntervention.TABLE_INTERVENTION, NewPoliceDB.TableIntervention.INTERVENTION_ID + " = ?",
                new String[]{String.valueOf(intervention.getIdIntervention())});

        new InterventionAsyncTask(intervention.getIdIntervention()).execute();
    }


}
