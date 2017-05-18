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
public class Team {

    @Id
    private long idTeam;
    private String TeamChief;
    private String teamComposant;


    public Team(){

    }

    public long getIdTeam() {
        return idTeam;
    }

    public String getTeamChief() {
        return TeamChief;
    }

    public void setTeamChief(String teamChief) {
        TeamChief = teamChief;
    }

    public String getTeamComposant() {
        return teamComposant;
    }

    public void setTeamComposant(String teamComposant) {
        this.teamComposant = teamComposant;
    }





}
