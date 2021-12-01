package nemanja.roganovic.rezervacija.service;

import java.util.List;

import nemanja.roganovic.rezervacija.model.Room;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RoomService {

    @GET("getAllRooms.php")
    Call<List<Room>> getAllRooms();

    @Multipart
    @POST("reservation.php")
    Call<ResponseBody> reserveRoom(@Part("idsobe") int idsobe, @Part("datefrom")
            RequestBody datefrom, @Part("dateto") RequestBody dateto, @Part("username") RequestBody username);
}