package com.example.david.police_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class DisplayOfficersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.officers_activity);

    }


    /** Called when the user taps the add_officers button**/
    public void showNewOfficer(View view){
        //show officers
        Intent intent = new Intent(this, DisplayNewOfficerActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the view_officers button**/
    public void showInfosOfficer(View view){
        //show officers
        Intent intent = new Intent(this, DisplayInfosOfficerActivity.class);
        startActivity(intent);
    }



}

