package com.example.david.police_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import Constructors.Intervention;
import DataSource.InterventionDataSource;
import DataSource.OfficerDataSource;
import DataSource.TeamDataSource;


public class DisplayInterventionsActivity extends AppCompatActivity {
    TeamDataSource tds;
    public static final String EXTRA_MESSAGE2 = "com.example.david.police_app.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interventions_activity);


        // Create a LinearLayout element
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);



        NewDataBaseHelper db = new NewDataBaseHelper(this);
        OfficerDataSource ods = new OfficerDataSource(this);
        TeamDataSource tds = new TeamDataSource(this);
        InterventionDataSource ids = new InterventionDataSource(this);
        List<Intervention> interventions = new ArrayList<Intervention>();


        //insert interventions

        //Intervention i1  = new Intervention(1, "a", "b", "c", "d", "e", "f", 3, 2);
        //ids.deleteIntervention(5);//createIntervention(i1);




        interventions=ids.getAllInterventions();

        for (int i = 0; i < interventions.size(); i++) {

            // Add Buttons
            Button button = new Button(this);
            String s = String.valueOf(interventions.get(i).getInterName());
           // String s = String.valueOf(interventions.get(i).getIdIntervention());
           // String s = String.valueOf(interventions.get(i).getIdTeam());
          //  String s = String.valueOf(interventions.get(i).getIdIntervention());
                    //.getInterName();
            button.setText(s);
           final String idIntervention = String.valueOf(interventions.get(i).getIdIntervention());

         /**   Intent intent = getIntent();
            String message = intent.getStringExtra(DisplayInterventionsActivity.EXTRA_MESSAGE2);
            Button b = new Button(this);
            b.setText(String.valueOf(((Button)view).getText()););**/

            for(int j=i+1;j<interventions.size();j++){
                if(interventions.get(i).getInterName().equals(interventions.get(j).getInterName())){

                    i=i+1;
                }

            }
            linearLayout.addView(button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfosIntervention(view, idIntervention);
                }
            });

        }

    }

    /** Called when the user taps the add_Intervention button**/
    public void showNewIntervention(View view){
        //show officers
        Intent intent = new Intent(this, DisplayNewInterventionActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the show_intervention button**/
    public void showInfosIntervention(View view, String idIntervention){
        //show interventions
        Intent intent = new Intent(this, DisplayInfoInterventionActivity.class);
        String message = String.valueOf(idIntervention);
        intent.putExtra(EXTRA_MESSAGE2, message);
        startActivity(intent);
    }

}
