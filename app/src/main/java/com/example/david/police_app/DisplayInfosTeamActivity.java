package com.example.david.police_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import Constructors.Officer;
import Constructors.Team;
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
    private TeamDataSource tds;
    private List<Team> teams;
    private Team team;
    private List<CheckBox> rbl,rbl1;
    private CheckBox cb;
    private String sel;

    private RadioGroup rg;
    private String sel1;

    private RadioGroup chief;
    private LinearLayout composants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_team_activity);


        tds = new TeamDataSource(this);
        teams=tds.getAllTeams();
        Intent intent = getIntent();
        String message = intent.getStringExtra(DisplayTeamsActivity.EXTRA_MESSAGE1);
        team = tds.getTeamById(Integer.parseInt(message));


        id= (int)team.getIdTeam();

        // Create a RadioGroup element
        chief = (RadioGroup) findViewById(R.id.chief);
        chief.setOrientation(RadioGroup.VERTICAL);
        String teamChief;


        NewDataBaseHelper db = new NewDataBaseHelper(this);
        ods = new OfficerDataSource(this);
        List<Officer> officers = new ArrayList<Officer>();
        officers=ods.getAllOfficers();

        List<Team> teamsToShow = new ArrayList<Team>();
        teamsToShow=tds.getAllTeams();


        //boucle to show chiefs
        for (int i = 0; i < officers.size(); i++) {

            // Add RadioButtons
            RadioButton button = new RadioButton(this);
            String s =String.valueOf(officers.get(i).getLastname());

            button.setText(s.toUpperCase());
            String chiefString = team.getTeamChief();

            if(chiefString.toUpperCase().equals(s.toUpperCase()))
                button.setChecked(true);
            //boucle to show just one each officer
            for(int j=i+1;j<officers.size();j++){
                if(officers.get(i).getPhone().equals(officers.get(j).getPhone())){

                    i=i+1;
                }

            }
            chief.addView(button);

        }

        // Create a LinearLayout element
        composants = (LinearLayout) findViewById(R.id.composants);

//boucle to show composants
        for (int i = 0; i < officers.size(); i++) {

            // Add RadioButtons
            CheckBox button = new CheckBox(this);
            String s =String.valueOf(officers.get(i).getLastname());
            button.setId((int) officers.get(i).getIdOfficer());
            button.setText(s.toUpperCase());
            //String composant = team.getTeamComposant();

            if(officers.get(i).getIdTeam() == team.getIdTeam()) {
                button.setChecked(true);
                Button delete = (Button) findViewById(R.id.button5);
                delete.setEnabled(false);
            }
            //boucle to show just one each officer
            for (int j = i + 1; j < officers.size(); j++) {
                if (officers.get(i).getPhone().equals(officers.get(j).getPhone())) {
                    i = i + 1;
                }

            }
            composants.addView(button);

        }

    }
    public void deleteTeam(View view){

        //long id = team.getIdTeam();


        tds.deleteTeam(id);


        Intent intent = new Intent(this, DisplayTeamsActivity.class);

        startActivity(intent);
    }

    public void updateTeam(View view){

        Intent intent = new Intent(this,DisplayTeamsActivity.class);
        teams = new ArrayList<Team>();
        teams = tds.getAllTeams();

        rbl = new ArrayList<CheckBox>();
        rbl1 = new ArrayList<CheckBox>();

        LinearLayout llt = (LinearLayout) findViewById(R.id.composants);

        RadioGroup rg = (RadioGroup) findViewById(R.id.chief);

        String chiefString="";
        sel = "";

        ArrayList<Officer> selComposants = new ArrayList<Officer>();

        for(int i = 0; i < llt.getChildCount(); i++){
            cb = (CheckBox)llt.getChildAt(i);

            Officer off = ods.getOfficerById(cb.getId());
            off.setIdTeam(0);
            if(cb.isChecked()){
                sel = cb.getText().toString();
                off.setIdTeam((int) team.getIdTeam());
            }
            selComposants.add(off);

        }
        for(int i = 0; i < rg.getChildCount(); i++){

            RadioButton rb = (RadioButton) rg.getChildAt(i);

            if(rb.isChecked()){
                chiefString = rb.getText().toString();

            }

        }


        for(int i = 0; i < selComposants.size();i++){
            ods.updateOfficer(selComposants.get(i));
        }

        Team t1 = new Team(team.getIdTeam(), chiefString,sel);

        tds.updateTeam(t1);
        startActivity(intent);
    }

}
