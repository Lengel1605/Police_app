package com.example.david.police_app;


import android.provider.BaseColumns;


public class NewPoliceDB {


    public static abstract class TableOfficer implements BaseColumns {

        //Table name
        public static final String TABLE_NAME_OFFICER = "Officer";

        //TABLE FOR OFFICERS
        public static final String OFFICER_ID = "idOfficer";
        public static final String OFFICER_FIRSTNAME = "officer_firstname";
        public static final String OFFICER_LASTNAME = "officer_lastname";
        public static final String OFFICER_PHONE = "officer_phone";
        public static final String OFFICER_TYPE = "officer_type";

        public static final String OFFICER_ID_TEAM = "IdTeam";
        public static final String OFFICER_ID_INTERVENTION = "IdIntervention";
        public static final String OFFICER_ID_DETAILS = "IdDetail";


        //Table officer create statement

        public static final String CREATE_TABLE_OFFICER = "CREATE TABLE "
                + TABLE_NAME_OFFICER + "("
                + TableOfficer.OFFICER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TableOfficer.OFFICER_FIRSTNAME + " TEXT, "
                + TableOfficer.OFFICER_LASTNAME + " TEXT, "
                + TableOfficer.OFFICER_PHONE + " TEXT, "
                + TableOfficer.OFFICER_TYPE + " TEXT, "
                + TableOfficer.OFFICER_ID_TEAM + " INTEGER, "
                + TableOfficer.OFFICER_ID_INTERVENTION + " INTEGER "
                + TableOfficer.OFFICER_ID_DETAILS + " INTEGER "
                + ");";

    }

    public static abstract class TableTeam implements BaseColumns {
        //Table name
        public static final String TABLE_NAME_TEAM = "Team";

        //TABLE FOR TEAM
        public static final String TEAM_ID = "idTeam";
        public static final String TEAM_CHIEF = "team_chief";
        public static final String TEAM_COMPOSANTS = "team_composants";

        public static final String TEAM_ID_OFFICER = "IdOfficer";
        public static final String TEAM_ID_INTERVENTION = "IdIntervention";
        public static final String TEAM_ID_DETAILS = "IdDetail";


        //Table team create statement
        public static final String CREATE_TABLE_TEAM = "CREATE TABLE "
                + TABLE_NAME_TEAM + "("
                + TableTeam.TEAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TableTeam.TEAM_CHIEF + " TEXT, "
                + TableTeam.TEAM_COMPOSANTS + " INTEGER "
                + TableTeam.TEAM_ID_OFFICER + " INTEGER, "
                + TableTeam.TEAM_ID_INTERVENTION + " INTEGER "
                + TableTeam.TEAM_ID_DETAILS + " INTEGER "

                + ");";

    }



    public static abstract class TableIntervention implements BaseColumns {
        //Table name
        public static final String TABLE_NAME_INTERVENTION = "Intervention";


        //TABLE FOR INTERVENTION
        public static final String INTERVENTION_ID = "IdIntervention";
        public static final String INTERVENTION_NAME = "Intervention_name";
        public static final String INTERVENTION_PRIORITY = "Intervention_priority";

        public static final String INTERVENTION_ID_OFFICER = "IdOfficer";
        public static final String INTERVENTION_ID_TEAM = "IdTeam";
        public static final String INTERVENTION_ID_DETAILS = "IdDetail";

        //Table intervention create statement
        public static final String CREATE_TABLE_INTERVENTION = "CREATE TABLE "
                + TABLE_NAME_INTERVENTION + "("
                + TableIntervention.INTERVENTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TableIntervention.INTERVENTION_NAME + " TEXT, "
                + TableIntervention.INTERVENTION_PRIORITY + " TEXT, "
                + TableIntervention.INTERVENTION_ID_OFFICER + " INTEGER, "
                + TableIntervention.INTERVENTION_ID_TEAM + " INTEGER "
                + TableIntervention.INTERVENTION_ID_DETAILS + " INTEGER "
                + " );";


    }


    public static abstract class TableDetail implements BaseColumns {
        //Table name
        public static final String TABLE_NAME_DETAIL = "Detail";


        //TABLE FOR DETAILS
        public static final String DETAIL_ID = "IdDetail";
        public static final String DETAIL_NAME = "Detail_name";
        public static final String DETAIL_DESCRIPTION = "Detail_description";
        public static final String DETAIL_DATE_BEGIN = "Detail_date_begin";
        public static final String DETAIL_DATE_END = "Detail_date_end";
        public static final String DETAIL_LOCALISTION = "Detail_localisation";

        public static final String DETAIL_ID_OFFICER = "IdOfficer";
        public static final String DETAIL_ID_TEAM = "IdTeam";
        public static final String DETAIL_ID_INTERVENTION = "IdIntervention";

        //Table detail create statement
        public static final String CREATE_TABLE_DETAIL = "CREATE TABLE "
                + TABLE_NAME_DETAIL + "("
                + TableDetail.DETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + TableDetail.DETAIL_NAME + " TEXT, "
                + TableDetail.DETAIL_DESCRIPTION + " TEXT, "
                + TableDetail.DETAIL_DATE_BEGIN + " TEXT, "
                + TableDetail.DETAIL_DATE_END + " TEXT, "
                + TableDetail.DETAIL_ID_OFFICER + " INTEGER, "
                + TableDetail.DETAIL_ID_TEAM + " INTEGER "
                + TableDetail.DETAIL_ID_INTERVENTION + " INTEGER "
                + " );";



    }


}