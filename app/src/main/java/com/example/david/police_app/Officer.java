package com.example.david.police_app;

/**
 * Created by David on 24.04.2017.
 */

public class Officer {

    private int id_Officer;
    private String OfficerFirstname;
    private String OfficerLastname;
    private String OfficerPhone;
    private String OfficerType;

    public Officer(){

    }
    public Officer(int id_Officer, String OfficerFirstname, String OfficerLastname,
                   String OfficerPhone, String OfficerType){

        this.id_Officer = id_Officer;
        this.OfficerFirstname = OfficerFirstname;
        this.OfficerLastname = OfficerLastname;
        this.OfficerPhone = OfficerPhone;
        this.OfficerType = OfficerType;

    }
    public void setOfficerId(String OfficerFirstname){
        this.OfficerFirstname=OfficerFirstname;
    }
    public String getOfficerId(){
        return OfficerFirstname;
    }
    public void setOfficerFirstname(String OfficerFirstname){
        this.OfficerFirstname=OfficerFirstname;
    }
    public String getOfficerFirstname(){
        return OfficerFirstname;
    }
    public void setOfficerLastname(String OfficerFirstname){
        this.OfficerFirstname=OfficerFirstname;
    }
    public String getOfficerLastname(){
        return OfficerFirstname;
    }
    public void setOfficerPhone(String OfficerFirstname){
        this.OfficerFirstname=OfficerFirstname;
    }
    public String getOfficerPhone(){
        return OfficerFirstname;
    }
    public void setOfficerType(String OfficerFirstname){
        this.OfficerFirstname=OfficerFirstname;
    }
    public String getOfficerType(){
        return OfficerFirstname;
    }

}
