package nemanja.roganovic.rezervacija;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import nemanja.roganovic.rezervacija.adapter.HotelAdapter;
import nemanja.roganovic.rezervacija.adapter.RoomAdapter;
import nemanja.roganovic.rezervacija.model.Hotel;
import nemanja.roganovic.rezervacija.model.Room;
import nemanja.roganovic.rezervacija.service.HotelService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {
    RecyclerView hotelRec;
    HotelAdapter hotelAdapter;
    RecyclerView.LayoutManager viewManager;
    HotelService hotelService;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        hotelService = ApiUtils.getHotelService();

        viewManager = new LinearLayoutManager(this);
        hotelAdapter = new HotelAdapter();

        hotelRec = findViewById(R.id.hotel_list);
        btnLogout = findViewById(R.id.admin_log_out);

        hotelRec.setAdapter(hotelAdapter);
        hotelRec.setLayoutManager(viewManager);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        hotelService.getAllHotels().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(response.isSuccessful()) {
                    hotelAdapter.setHotels(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {

            }
        });
    }
}
