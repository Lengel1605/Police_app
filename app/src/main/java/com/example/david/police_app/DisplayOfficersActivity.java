package com.example.david.police_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Constructors.Officer;
import DataSource.InterventionDataSource;
import DataSource.OfficerDataSource;
import DataSource.TeamDataSource;


public class DisplayOfficersActivity extends AppCompatActivity {

    OfficerDataSource ods;
    public static final String EXTRA_MESSAGE = "com.example.david.police_app.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.officers_activity);


        // Create a LinearLayout element
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        NewDataBaseHelper db = new NewDataBaseHelper(this);
        ods = new OfficerDataSource(this);
        TeamDataSource tds = new TeamDataSource(this);
        InterventionDataSource ids = new InterventionDataSource(this);
        List<Officer> officers = new ArrayList<Officer>();


        officers = ods.getAllOfficers();


        for (int i = 0; i < officers.size(); i++) {
            final Button button = new Button(this);
            int btnId = (int) officers.get(i).getIdOfficer();
            String s = officers.get(i).getLastname();
            button.setText(s);
            button.setId(btnId);
            //Ajout de l'action listener

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showOfficer(view);
                }
            });
            for (int j = i + 1; j < officers.size(); j++) {
                if (officers.get(i).getPhone().equals(officers.get(j).getPhone())) {

                    i = i + 1;
                }

            }

            linearLayout.addView(button);

        }
    }

    /**
     * Called when the user taps a officers button
     **/
    public void showOfficer(View view) {

        Intent intent = new Intent(this, DisplayInfosOfficerActivity.class);
        String message = String.valueOf(view.getId());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    /**
     * Called when the user taps the add_officers button
     **/
    public void showNewOfficer(View view) {

        Intent intent = new Intent(this, DisplayNewOfficerActivity.class);
        startActivity(intent);
    }


}