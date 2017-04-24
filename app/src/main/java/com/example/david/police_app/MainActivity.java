package com.example.david.police_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   DBHandler db = new DBHandler(this);
/**
        // Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");
        db.addOfficer(new Officer(1,"David", "Cano","021","special" ));
        db.addOfficer(new Officer(2,"Toto", "Toto","021","special" ));
        db.addOfficer(new Officer(3,"Tata", "tata","021","special" ));
        db.addOfficer(new Officer(4,"tete", "tete","021","special" ));
        db.addOfficer(new Officer(5,"titi", "titi","021","special" ));

// Reading all shops
        Log.d("Reading: ", "Reading all officers..");
        List<Officer> officers = db.getAllOfficers();

        for (Officer officer : officers) {
            String log = "Id: " + officer.getOfficerId() + " ,Name: " + officer.getOfficerFirstname() +
                    "Lastname"+officer.getOfficerLastname()+ " Phone"+ officer.getOfficerPhone()+
                    " Type " + officer.getOfficerType();
// Writing shops to log
            Log.d("Officer: : ", log);
        }
 **/
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
