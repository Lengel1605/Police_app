package com.example.david.police_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lionel on 03.05.2017.
 */

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
