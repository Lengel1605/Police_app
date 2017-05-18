package com.example.david.police_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Constructors.Team;
import DataSource.InterventionDataSource;
import DataSource.OfficerDataSource;
import DataSource.TeamDataSource;


public class DisplayTeamsActivity extends AppCompatActivity {

    TeamDataSource tds;
    public static final String EXTRA_MESSAGE1 = "com.example.david.police_app.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity);


        // Create a LinearLayout element
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        NewDataBaseHelper db = new NewDataBaseHelper(this);
        OfficerDataSource ods = new OfficerDataSource(this);
        TeamDataSource tds = new TeamDataSource(this);
        InterventionDataSource ids = new InterventionDataSource(this);
        List<Team> teams = new ArrayList<Team>();

        teams = tds.getAllTeams();
        Team team = new Team(0, "Default", "Default");
        tds.createTeam(team);

        for (int i = 0; i < teams.size(); i++) {

            // Add Buttons
            Button button = new Button(this);
            String s = String.valueOf(teams.get(i).getIdTeam());


            button.setText(s);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfosTeam(view);
                }
            });

            linearLayout.addView(button);

        }

    }

    /**
     * Called when the user taps the add_team button
     **/
    public void showNewTeam(View view) {
        //show officers
        Intent intent = new Intent(this, DisplayNewTeamActivity.class);

        startActivity(intent);
    }

    /**
     * Called when the user taps the show_team button
     **/
    public void showInfosTeam(View view) {
        //show teams
        Intent intent = new Intent(this, DisplayInfosTeamActivity.class);
        String message = String.valueOf(((Button) view).getText());
        intent.putExtra(EXTRA_MESSAGE1, message);
        startActivity(intent);
    }

}
