package nemanja.roganovic.rezervacija.service;

import java.util.List;

import nemanja.roganovic.rezervacija.model.Hotel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HotelService {

    @GET("getAllHotells.php")
    Call<List<Hotel>> getAllHotels();
}