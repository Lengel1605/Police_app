package com.example.david.police_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lionel.police_app.backend.constructors.interventionApi.model.Intervention;
import com.example.lionel.police_app.backend.constructors.officerApi.model.Officer;

import java.util.ArrayList;
import java.util.List;

import DataSource.InterventionDataSource;
import DataSource.OfficerDataSource;
import DataSource.TeamDataSource;

/**
 * Created by David on 25.04.2017.
 */

public class DisplayInfosTeamActivity extends AppCompatActivity{



    private String teamchief,teamcomposants;
    //getTeamchief(); getTeamComposants
    private int id;
    private OfficerDataSource ods;

    private RadioGroup chief;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_team_activity);


        // Create a RadioGroup element
        chief = (RadioGroup) findViewById(R.id.chief);
        chief.setOrientation(RadioGroup.VERTICAL);


        NewDataBaseHelper db = new NewDataBaseHelper(this);
        ods = new OfficerDataSource(this);
        TeamDataSource tds = new TeamDataSource(this);
        InterventionDataSource ids = new InterventionDataSource(this);
        List<Officer> officers = new ArrayList<Officer>();


        officers = ods.getAllOfficers();

        for (int i = 0; i < officers.size(); i++) {

            // Add Buttons
            final RadioButton button = new RadioButton(this);
            long btnId = officers.get(i).getIdOfficer();
            String s = officers.get(i).getLastname();
            button.setText(s);
            button.setId((int)btnId);
            for (int j = i + 1; j < officers.size(); j++) {
                if (officers.get(i).getPhone().equals(officers.get(j).getPhone())) {
                    i = i + 1;
                }

            }

            chief.addView(button);
        }

        // Create a LinearLayout element
        LinearLayout composants = (LinearLayout) findViewById(R.id.composants);


        List<Officer> officers1 = new ArrayList<Officer>();


        officers1 = ods.getAllOfficers();

        for (int i = 0; i < officers1.size(); i++) {

            // Add Buttons
            final CheckBox button = new CheckBox(this);
            long btnId = officers.get(i).getIdOfficer();
            String s = officers1.get(i).getLastname();
            button.setText(s);
            button.setId((int)btnId);
            //Ajout de l'action listener

            //set onclick
            for (int j = i + 1; j < officers.size(); j++) {
                if (officers.get(i).getPhone().equals(officers.get(j).getPhone())) {
                    i = i + 1;
                }

            }
            composants.addView(button);
        }

        // Create a LinearLayout element
        LinearLayout inter = (LinearLayout) findViewById(R.id.inter);
        // interventionsLayout.setOrientation(LinearLayout.VERTICAL);

        List<Intervention> interventions = new ArrayList<Intervention>();


        //insert interventions

        interventions=ids.getAllInterventions();

        for (int i = 0; i < interventions.size(); i++) {

            // Add Buttons
            CheckBox button = new CheckBox(this);
            String s = interventions.get(i).getInterName();
            button.setText(s);
            button.setTransitionName(s+i);
            for(int j=i+1;j<interventions.size();j++){
                if(interventions.get(i).getInterName().equals(interventions.get(j).getInterName())){

                    i=i+1;
                }

            }
            inter.addView(button);



        }

    }
    public void deleteTeam(View view){


        Intent intent = new Intent(this, DisplayTeamsActivity.class);
        startActivity(intent);
    }
    public void updateTeam(View view){


        Intent intent = new Intent(this, DisplayTeamsActivity.class);
        startActivity(intent);
    }

}
