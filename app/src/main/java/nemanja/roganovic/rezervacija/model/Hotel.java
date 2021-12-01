package nemanja.roganovic.rezervacija.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("naziv")
    @Expose
    private String naziv;
    @SerializedName("zvjezdice")
    @Expose
    private String zvjezdice;
    @SerializedName("bazen")
    @Expose
    private String bazen;
    @SerializedName("spa")
    @Expose
    private String spa;
    @SerializedName("odcentra")
    @Expose
    private String odcentra;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZvjezdice() {
        return zvjezdice;
    }

    public void setZvjezdice(String zvjezdice) {
        this.zvjezdice = zvjezdice;
    }

    public String getBazen() {
        return bazen;
    }

    public void setBazen(String bazen) {
        this.bazen = bazen;
    }

    public String getSpa() {
        return spa;
    }

    public void setSpa(String spa) {
        this.spa = spa;
    }

    public String getOdcentra() {
        return odcentra;
    }

    public void setOdcentra(String odcentra) {
        this.odcentra = odcentra;
    }
}
