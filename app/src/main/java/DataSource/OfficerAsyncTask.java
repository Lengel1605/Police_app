package DataSource;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lionel.police_app.backend.officerApi.OfficerApi;
import com.example.lionel.police_app.backend.officerApi.model.Officer;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionel on 16.05.2017.
 */

public class OfficerAsyncTask extends AsyncTask<Void, Void, List<Officer>> {
    private static OfficerApi officerApi = null;
    private static final String TAG = OfficerAsyncTask.class.getName();
    private Officer officer;
    private long idOfficer;

    public OfficerAsyncTask() {
    }

    public OfficerAsyncTask(long idOfficer) {
        this.idOfficer = idOfficer;
    }

    public OfficerAsyncTask(long idOfficer, Constructors.Officer offContent) {
        this.officer = new Officer();
        this.officer.setIdOfficer(offContent.getIdOfficer());
        this.officer.setFirstname(offContent.getFirstname());
        this.officer.setLastname(offContent.getLastname());
        this.officer.setPhone(offContent.getPhone());
        this.officer.setType(offContent.getType());
        this.officer.setIdTeam(offContent.getIdTeam());
    }

    public OfficerAsyncTask(Constructors.Officer officer) {
        this.officer = new Officer();
        this.officer.setIdOfficer(officer.getIdOfficer());
        this.officer.setFirstname(officer.getFirstname());
        this.officer.setLastname(officer.getLastname());
        this.officer.setPhone(officer.getPhone());
        this.officer.setType(officer.getType());
        this.officer.setIdTeam(officer.getIdTeam());
    }

    @Override
    protected List<Officer> doInBackground(Void... params) {

        if (officerApi == null) {
            // Only do this once
            OfficerApi.Builder builder = new OfficerApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://policeapp-167213.appspot.com/_ah/api/");
            officerApi = builder.build();
        }

        try {
            // Call here the wished methods on the Endpoints
            // For instance insert
            if (idOfficer == 0 && officer != null) {
                officerApi.insert(officer).execute();
                Log.i(TAG, "insert officer");
            }

            if (idOfficer != 0 && officer == null) {
                officerApi.remove(idOfficer).execute();
                Log.i(TAG, "delete officer");
            }

            if (idOfficer != 0 && officer != null) {
                officerApi.update(idOfficer, officer).execute();
                Log.i(TAG, "update officer");
            }

            // and for instance return the list of all employees
            return officerApi.list().execute().getItems();

        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return new ArrayList<Officer>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Officer> result) {

        if (result != null) {
            for (Officer officer : result) {
                Log.i(TAG, "First name: " + officer.getFirstname() + " Last name: "
                        + officer.getLastname());

            }
        }
    }
}
