package com.example.david.police_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by David on 25.04.2017.
 *
 * The goal of this class is to manage the Information
 */

public class DisplayInfoInterventionActivity extends AppCompatActivity{

    private EditText fn,ln,ph;
    private String type;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newofficer_activity);


    }

}