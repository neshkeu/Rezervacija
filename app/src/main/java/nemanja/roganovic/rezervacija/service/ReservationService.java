package nemanja.roganovic.rezervacija.service;

import java.util.List;

import nemanja.roganovic.rezervacija.model.Reservation;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface    ReservationService {

    @POST("getReservationsForUser.php")
    @FormUrlEncoded
    Call<List<Reservation>> getAllReservations(@Field("username") String username);

    @POST("cancelReservation.php")
    @FormUrlEncoded
    Call<ResponseBody> cancelReservation(@Field("id") String id);
}