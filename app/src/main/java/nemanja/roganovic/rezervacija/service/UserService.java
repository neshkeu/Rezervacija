package nemanja.roganovic.rezervacija.service;

import nemanja.roganovic.rezervacija.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @POST("login.php")
    @FormUrlEncoded
    Call<User> login(@Field("username") String username, @Field("password") String password);
}