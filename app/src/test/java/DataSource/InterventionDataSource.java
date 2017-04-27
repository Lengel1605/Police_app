package DataSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import Constructors.Intervention;

/**
 * Created by David on 27.04.2017.
 */

public class InterventionDataSource {
    private SQLiteDatabase db;


    public InterventionDataSource(Context context) {
    }

    public List<Intervention> getAllInterventionsByOfficer(long id) {
    }
}
