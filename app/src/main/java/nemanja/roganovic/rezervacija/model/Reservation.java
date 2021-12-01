package nemanja.roganovic.rezervacija.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reservation {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("soba")
    @Expose
    private String room;
    @SerializedName("korisnik")
    @Expose
    private String username;
    @SerializedName("datumod")
    @Expose
    private String dateFrom;
    @SerializedName("datumdo")
    @Expose
    private String dateTo;
    @SerializedName("cijena")
    @Expose
    private String cijenaDan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getCijenaDan() {
        return cijenaDan;
    }

    public void setCijenaDan(String cijenaDan) {
        this.cijenaDan = cijenaDan;
    }
}
