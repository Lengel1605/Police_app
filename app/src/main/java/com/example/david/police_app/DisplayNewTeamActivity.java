package com.example.david.police_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import Constructors.Intervention;
import Constructors.Officer;
import Constructors.Team;
import DataSource.InterventionDataSource;
import DataSource.OfficerDataSource;
import DataSource.TeamDataSource;


public class DisplayNewTeamActivity extends AppCompatActivity {


    private String teamchief,teamcomposants;
    //getTeamchief(); getTeamComposants
    private int id;
    private OfficerDataSource ods;

    private RadioGroup chief;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newteam_activity);


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
            int btnId = (int) officers.get(i).getId_Officer();
            String s = officers.get(i).getLastname();
            button.setText(s);
            button.setId(btnId);
            //Ajout de l'action listener

            //set onclick

            chief.addView(button);
        }

        // Create a LinearLayout element
        LinearLayout composants = (LinearLayout) findViewById(R.id.composants);


        List<Officer> officers1 = new ArrayList<Officer>();


        officers1 = ods.getAllOfficers();

        for (int i = 0; i < officers1.size(); i++) {

            // Add Buttons
            final RadioButton button = new RadioButton(this);
            int btnId = (int) officers.get(i).getId_Officer();
            String s = officers1.get(i).getLastname();
            button.setText(s);
            button.setId(btnId);
            //Ajout de l'action listener

            //set onclick

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
            RadioButton button = new RadioButton(this);
            String s = interventions.get(i).getInterName();
            button.setText(s);
           button.setTransitionName(s+i);

            inter.addView(button);



        }

    }

    /** Called when officer click submit intervention button */
    public void submitTeam(View view) {

        Intent intent = new Intent(this, DisplayTeamsActivity.class);

        TeamDataSource tds = new TeamDataSource(this);



        chief = (RadioGroup) findViewById(R.id.chief);
        RadioButton chiefButton;
        String name="";
        int idChief;
        Officer officer = new Officer();

        int count = chief.getChildCount();
       // ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
        for (int i=0;i<count;i++) {
            chiefButton = (RadioButton)chief.getChildAt(i);
            if (chiefButton.isChecked()) {
                name = chiefButton.getText().toString();

            }
        }

        List<Team> teams = new ArrayList<Team>();
        teams = tds.getAllTeams();
        id = teams.size()+10;


        Team t1 = new Team(id,name,"David");

        tds.createTeam(t1);

        startActivity(intent);


    }


}