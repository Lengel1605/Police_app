package Constructors;

/**
 * Created by David on 27.04.2017.
 */

public class Intervention {

    private long idIntervention;
    private String interName;
    private String intDescription;
    private String dateBegin;
    private String localisation;
    private int idTeam;


    public Intervention() {

    }

    public Intervention(long idIntervention, String interName, String intDescription, String DateBegin, String Localisation, int idTeam) {

        this.idIntervention = idIntervention;
        this.interName = interName;
        this.intDescription = intDescription;
        this.dateBegin = DateBegin;
        this.localisation = Localisation;
        this.idTeam = idTeam;
    }

    public long getIdIntervention() {
        return idIntervention;
    }

    public void setIdIntervention(long idIntervention) {
        this.idIntervention = idIntervention;
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
