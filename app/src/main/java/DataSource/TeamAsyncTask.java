package DataSource;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lionel.police_app.backend.officerApi.OfficerApi;
import com.example.lionel.police_app.backend.officerApi.model.Officer;
import com.example.lionel.police_app.backend.teamApi.TeamApi;
import com.example.lionel.police_app.backend.teamApi.model.Team;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionel on 16.05.2017.
 */

public class TeamAsyncTask extends AsyncTask<Void, Void, List<Team>> {
    private static TeamApi teamApi = null;
    private static final String TAG = OfficerAsyncTask.class.getName();
    private Team team;
    private long idTeam;

    public TeamAsyncTask() {
    }

    public TeamAsyncTask(long idTeam) {
        this.idTeam = idTeam;
    }

    public TeamAsyncTask(long idTeam, Constructors.Team teamContent) {
        this.team = new Team();
        this.team.setIdTeam(teamContent.getIdTeam());
        this.team.setTeamChief(teamContent.getTeamChief());
        this.team.setTeamComposant(teamContent.getTeamComposant());
    }

    public TeamAsyncTask(Constructors.Team team) {
        this.team = new Team();
        this.team.setIdTeam(team.getIdTeam());
        this.team.setTeamChief(team.getTeamChief());
        this.team.setTeamComposant(team.getTeamComposant());
    }

    @Override
    protected List<Team> doInBackground(Void... params) {

        if (teamApi == null) {
            // Only do this once
            TeamApi.Builder builder = new TeamApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://policeapp-167213.appspot.com/_ah/api/");
            teamApi = builder.build();
        }

        try {
            // Call here the wished methods on the Endpoints
            // For instance insert
            if (team != null) {
                teamApi.insert(team).execute();
                Log.i(TAG, "insert team");
            }
            if (idTeam != 0 && team == null) {
                teamApi.remove(idTeam).execute();
                Log.i(TAG, "delete team");
            }

            if (idTeam != 0 && team != null) {
                teamApi.update(idTeam, team).execute();
                Log.i(TAG, "update team");
            }

            // and for instance return the list of all employees
            return teamApi.list().execute().getItems();

        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return new ArrayList<Team>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Team> result) {

        if (result != null) {
            for (Team team : result) {
                Log.i(TAG, "Boss: " + team.getTeamChief() + " Composants: "
                        + team.getTeamComposant());

            }
        }
    }
}
