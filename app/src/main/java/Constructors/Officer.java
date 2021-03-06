package Constructors;

import java.io.Serializable;

/**
 * Created by David on 24.04.2017.
 */


public class Officer implements Serializable {
    private long idOfficer;
    private String Firstname;
    private String Lastname;
    private String Phone;
    private String Type;
    private int idTeam;

    public Officer() {

    }

    public Officer(long idOfficer, String Firstname, String Lastname,
                   String Phone, String Type, int idTeam) {

        this.idOfficer = idOfficer;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Phone = Phone;
        this.Type = Type;
        this.idTeam = idTeam;


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
