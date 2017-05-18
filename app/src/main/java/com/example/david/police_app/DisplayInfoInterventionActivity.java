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


public class DisplayInfoInterventionActivity extends AppCompatActivity {
    private EditText name, dateB,dateE,localisation,description;
    private String iname,idateB,idateE,ilocalisation,idescription;
    private int id;
   // private LinearLayout linearLayout,llt;
    private RadioGroup team;
    private List<Team> teams;
    private RadioButton hight, medium,low;
    private CheckBox button;
    private InterventionDataSource ids;
    private Intent intent;
    private List<Intervention> interventions;
    private Intervention intervention;
    private String type="";
    private List<CheckBox> rbl;
    private CheckBox cb;
    private String sel;
    private Intervention i1;
    private RadioGroup rgType;
    private TeamDataSource tds;
    private NewDataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.info_intervention_activity);

       ids = new InterventionDataSource(this);
        interventions = ids.getAllInterventions();
        Intent intent = getIntent();
        String message = intent.getStringExtra(DisplayInterventionsActivity.EXTRA_MESSAGE2);

          intervention = ids.getInterventionById(Integer.parseInt(message));



        name = (EditText) findViewById(R.id.name);
        name.setText(intervention.getInterName());

        description = (EditText) findViewById(R.id.description);
        description.setText(intervention.getIntDescription());

        dateB = (EditText) findViewById(R.id.dateB);
        dateB.setText(intervention.getDateBegin());

        localisation = (EditText) findViewById(R.id.localisation);
        localisation.setText(intervention.getLocalisation());


        LinearLayout llt = (LinearLayout) findViewById(R.id.teamI);


        NewDataBaseHelper db = new NewDataBaseHelper(this);
        ids = new InterventionDataSource(this);
        TeamDataSource tds = new TeamDataSource(this);
        InterventionDataSource ids = new InterventionDataSource(this);
        List<Intervention> interventions = new ArrayList<Intervention>();
        tds = new TeamDataSource(this);

        List<Intervention> interventionsToShow = new ArrayList<Intervention>();
        interventionsToShow=ids.getAllInterventions();

        List<Team> teams = new ArrayList<Team>();
        teams=tds.getAllTeams();

        //boucle to show teams
        for (int i = 0; i < teams.size(); i++) {

            // Add RadioButtons
            CheckBox button = new CheckBox(this);
            String s = String.valueOf(teams.get(i).getIdTeam());
            button.setText(s);

           llt.addView(button);

            if (s.equals(String.valueOf(intervention.getIdTeam()))) {
                button.setChecked(true);


            }
            else{
                //boucle to click all teams to the officer
                for(int k=(int)intervention.getIdTeam(); k<interventionsToShow.size();k++) {
                    if(interventionsToShow.get(k).getInterName().equals(intervention.getInterName())){
                        if (s.equals(String.valueOf(interventionsToShow.get(k).getIdTeam()))) {
                            button.setChecked(true);

                        }
                    }
                }
            }


        }

    }


    public void deleteIntervention(View view){
ids.deleteIntervention(intervention);

        Intent intent = new Intent(this, DisplayInterventionsActivity.class);
        startActivity(intent);
    }
    public void updateIntervention(View view){

        intent = new Intent(this,DisplayInterventionsActivity.class);
        interventions = new ArrayList<Intervention>();
        interventions = ids.getAllInterventions();

        name = (EditText) findViewById(R.id.name);
        String iname = name.getText().toString();

        description = (EditText) findViewById(R.id.description);
        String idescription = description.getText().toString();

        dateB = (EditText) findViewById(R.id.dateB);
        String idateB = dateB.getText().toString();

        localisation = (EditText) findViewById(R.id.localisation);
        String ilocalisation = localisation.getText().toString();

        if (name.getText().length()==0){
            name.setError("Please enter a name for the intervention");
            return;
        }

        if (description.getText().length()==0){
            description.setError("Please enter a description");
            return;
        }

        if (dateB.getText().length()==0){
            dateB.setError("Please enter a date");
            return;
        }
        if (localisation.getText().length()==0){
            localisation.setError("Please enter a localisation");
            return;
        }

        rbl = new ArrayList<CheckBox>();

        LinearLayout llt = (LinearLayout) findViewById(R.id.teamI);

        for(int i = 0; i < llt.getChildCount(); i++){
            cb = (CheckBox)llt.getChildAt(i);
            sel = cb.getText().toString();


            int idTeam = Integer.parseInt(sel);

            if(cb.isChecked()){
                i1 = new Intervention(intervention.getIdIntervention(), iname, idescription,
                        idateB, ilocalisation,idTeam);

                ids.updateIntervention(i1);

            }

        }

        startActivity(intent);

    }

}
