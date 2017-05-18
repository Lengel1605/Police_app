package DataSource;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lionel.police_app.backend.interventionApi.InterventionApi;
import com.example.lionel.police_app.backend.interventionApi.model.Intervention;
import com.example.lionel.police_app.backend.officerApi.OfficerApi;
import com.example.lionel.police_app.backend.officerApi.model.Officer;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lionel on 16.05.2017.
 */

public class InterventionAsyncTask extends AsyncTask<Void, Void, List<Intervention>> {
    private static InterventionApi interventionApi = null;
    private static final String TAG = InterventionAsyncTask.class.getName();
    private Intervention intervention;
    private long idIntervention;

    public InterventionAsyncTask() {
    }

    public InterventionAsyncTask(long idIntervention) {
        this.idIntervention = idIntervention;
    }

    public InterventionAsyncTask(long idIntervention, Constructors.Intervention interContent) {
        this.intervention = new Intervention();
        this.intervention.setIdIntervention(interContent.getIdIntervention());
        this.intervention.setDateBegin(interContent.getDateBegin());
        this.intervention.setIntDescription(interContent.getIntDescription());
        this.intervention.setInterName(interContent.getInterName());
        this.intervention.setLocalisation(interContent.getLocalisation());
    }

    public InterventionAsyncTask(Constructors.Intervention intervention) {
        this.intervention = new Intervention();
        this.intervention.setIdIntervention(intervention.getIdIntervention());
        this.intervention.setDateBegin(intervention.getDateBegin());
        this.intervention.setIntDescription(intervention.getIntDescription());
        this.intervention.setInterName(intervention.getInterName());
        this.intervention.setLocalisation(intervention.getLocalisation());
    }

    @Override
    protected List<Intervention> doInBackground(Void... params) {

        if (interventionApi == null) {
            // Only do this once
            InterventionApi.Builder builder = new InterventionApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://policeapp-167213.appspot.com/_ah/api/");
            interventionApi = builder.build();
        }

        try {
            // Call here the wished methods on the Endpoints
            // For instance insert
            if (idIntervention == 0 && intervention != null) {
                interventionApi.insert(intervention).execute();
                Log.i(TAG, "insert intervention");
            }

            if (idIntervention != 0 && intervention == null) {
                interventionApi.remove(idIntervention).execute();
                Log.i(TAG, "delete intervention");
            }

            if (idIntervention != 0 && intervention != null) {
                interventionApi.update(idIntervention, intervention).execute();
                Log.i(TAG, "update intervention");
            }

            // and for instance return the list of all employees
            return interventionApi.list().execute().getItems();

        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return new ArrayList<Intervention>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Intervention> result) {

        if (result != null) {
            for (Intervention intervention : result) {
                Log.i(TAG, "Intervention name: " + intervention.getInterName() + "Description: "
                        + intervention.getIntDescription());

            }
        }
    }
}
