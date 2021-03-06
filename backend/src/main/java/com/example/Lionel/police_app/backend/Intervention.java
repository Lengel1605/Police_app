package com.example.Lionel.police_app.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Lionel on 27.04.2017.
 */

@Entity
public class Intervention {

    @Id
    private long idIntervention;
    private String interName;
    private String intDescription;
    private String dateBegin;
    private String localisation;
    private int idTeam;


    public Intervention(){

    }

    public long getIdIntervention() {
        return idIntervention;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getIntDescription() {
        return intDescription;
    }

    public void setIntDescription(String intDescription) {
        this.intDescription = intDescription;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }



}
