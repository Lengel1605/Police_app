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

    /** Called when the user taps the officers button**/
    public void showNewIntervention(View view){
        //show officers
        Intent intent = new Intent(this, DisplayNewInterventionActivity.class);
        startActivity(intent);
    }

}
