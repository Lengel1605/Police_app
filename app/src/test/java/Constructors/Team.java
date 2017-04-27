package Constructors;

import java.io.Serializable;

/**
 * Created by David on 24.04.2017.
 */

public class Team implements Serializable {

    public long getId_Officer() {
        return id_Officer;
    }

    public void setId_Team(long id_Officer) {
        this.id_Officer = id_Officer;
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

    private long id_Officer;
    private String Firstname;
    private String Lastname;
    private String Phone;
    private String Type;

    public Officer(){

    }
    public Officer(int id_Officer, String Firstname, String Lastname,
                   String Phone, String Type){

        this.id_Officer = id_Officer;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Phone = Phone;
        this.Type = Type;

    }



}
