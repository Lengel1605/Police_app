package DataSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import Constructors.Team;

/**
 * Created by David on 27.04.2017.
 */

public class TeamDataSource {
    private SQLiteDatabase db;
    private Context context;


    public TeamDataSource(Context context) {
    }

    public List<Team> getAllTeamsByOfficer(long id) {
    }
}
