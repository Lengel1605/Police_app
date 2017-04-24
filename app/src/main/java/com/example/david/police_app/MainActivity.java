package com.example.david.police_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user taps the officers button**/
    public void showOfficers(View view){
        //show officers
        Intent intent = new Intent(this, DisplayOfficersActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the team button**/
    public void showTeams(View view){
        //show teams
        Intent intent = new Intent(this, DisplayTeamsActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the intervention button**/
    public void showInterventions(View view){
        //show interventions
        Intent intent = new Intent(this, DisplayInterventionsActivity.class);
        startActivity(intent);
    }
}
