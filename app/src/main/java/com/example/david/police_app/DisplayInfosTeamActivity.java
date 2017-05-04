package com.example.david.police_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import DataSource.TeamDataSource;

/**
 * Created by David on 25.04.2017.
 */

public class DisplayInfosTeamActivity extends AppCompatActivity{

    TeamDataSource tds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_team_activity);

        tds = new TeamDataSource(this);
    }

    public void displayMembers(View view){
        //List<Officer> teamMembers = tds.
    }

}
