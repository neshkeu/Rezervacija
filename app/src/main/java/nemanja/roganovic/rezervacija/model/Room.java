package nemanja.roganovic.rezervacija.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("hotel")
    @Expose
    private String hotel;
    @SerializedName("broj")
    @Expose
    private String broj;
    @SerializedName("brojkreveta")
    @Expose
    private String brojkreveta;
    @SerializedName("terasa")
    @Expose
    private String terasa;
    @SerializedName("cijena")
    @Expose
    private String cijena;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getBrojkreveta() {
        return brojkreveta;
    }

    public void setBrojkreveta(String brojkreveta) {
        this.brojkreveta = brojkreveta;
    }

    public String getTerasa() {
        return terasa;
    }

    public void setTerasa(String terasa) {
        this.terasa = terasa;
    }

    public String getCijena() {
        return cijena;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

}