package Constructors;

/**
 * Created by David on 24.04.2017.
 */

public class Officer {

    private int id_Officer;
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
    public void setIdOfficer(int Firstname){
        this.Firstname=Firstname;
    }
    public String getIdOfficer(){
        return Firstname;
    }
    public void setFirstname(String Firstname){
        this.Firstname=Firstname;
    }
    public String getFirstname(){
        return Firstname;
    }
    public void setLastname(String Firstname){
        this.Firstname=Firstname;
    }
    public String getLastname(){
        return Firstname;
    }
    public void setPhone(String Firstname){
        this.Firstname=Firstname;
    }
    public String getPhone(){
        return Firstname;
    }
    public void setType(String Firstname){
        this.Firstname=Firstname;
    }
    public String getType(){
        return Firstname;
    }


}
