package com.example.david.police_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class DisplayInterventionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interventions_activity);


    }

    /** Called when the user taps the add_intervention button**/
    public void showNewIntervention(View view){
        //show officers
        Intent intent = new Intent(this, DisplayNewInterventionActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the show_intervention button**/
    public void showInfosIntervention(View view){
        //show intervention
        Intent intent = new Intent(this, DisplayInfoInterventionActivity.class);
        startActivity(intent);
    }

}
