package com.example.Lionel.police_app.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Lionel on 24.04.2017.
 */

@Entity
public class Officer {

    @Id
    private long idOfficer;
    private String Firstname;
    private String Lastname;
    private String Phone;
    private String Type;
    private int idTeam;

    public Officer(){

    }

    public long getIdOfficer() {
        return idOfficer;
    }

    public void setIdOfficer(long idOfficer) {
        this.idOfficer = idOfficer;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }






}
