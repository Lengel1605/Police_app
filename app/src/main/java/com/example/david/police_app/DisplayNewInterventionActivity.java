package com.example.david.police_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import Constructors.Intervention;
import Constructors.Team;
import DataSource.InterventionDataSource;
import DataSource.TeamDataSource;

public class DisplayNewInterventionActivity extends AppCompatActivity {
    private EditText name, dateB, dateE, localisation, description;
    private String iname, idateB, idateE, ilocalisation, idescription;
    private int id;
    private LinearLayout linearLayout, llt;
    private RadioGroup team;
    private List<Team> teams;
    private NewDataBaseHelper db;
    private CheckBox button;
    private Intent intent;
    private List<Intervention> interventions;
    private String type = "";
    private List<CheckBox> rbl;
    private CheckBox cb;
    private String sel;
    private Intervention i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newintervention_activity);

        db = new NewDataBaseHelper(this);

        TeamDataSource tds = new TeamDataSource(this);

        linearLayout = (LinearLayout) findViewById(R.id.team);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        teams = new ArrayList<Team>();
        teams = tds.getAllTeams();

        for (int i = 0; i < teams.size(); i++) {
            button = new CheckBox(this);
            String s = String.valueOf(teams.get(i).getIdTeam());
            button.setText(s);
            linearLayout.addView(button);
        }
    }

    /**
     * Called when officer click submit button
     */
    public void submitIntervention(View view) {
        intent = new Intent(this, DisplayInterventionsActivity.class);

        InterventionDataSource ids = new InterventionDataSource(this);

        interventions = new ArrayList<Intervention>();

        interventions = ids.getAllInterventions();

        id = interventions.size();

        name = (EditText) findViewById(R.id.name);
        iname = name.getText().toString();

        description = (EditText) findViewById(R.id.description);
        idescription = description.getText().toString();

        dateB = (EditText) findViewById(R.id.dateB);
        idateB = dateB.getText().toString();


        localisation = (EditText) findViewById(R.id.localisation);
        ilocalisation = localisation.getText().toString();


        if (name.getText().length() == 0) {
            name.setError("Please enter a name for the intervention");
            return;
        }

        if (description.getText().length() == 0) {
            description.setError("Please enter a description");
            return;
        }

        if (dateB.getText().length() == 0) {
            dateB.setError("Please enter a date");
            return;
        }
        if (localisation.getText().length() == 0) {
            localisation.setError("Please enter a localisation");
            return;
        }


        llt = (LinearLayout) findViewById(R.id.team);

        for (int i = 0; i < llt.getChildCount(); i++) {
            cb = (CheckBox) llt.getChildAt(i);
            sel = cb.getText().toString();


            int idTeam = Integer.parseInt(sel);

            if (cb.isChecked()) {
                i1 = new Intervention(id, iname, idescription,
                        idateB, ilocalisation, idTeam);

                ids.createIntervention(i1);

            }

        }

        startActivity(intent);

    }


}
