package nemanja.roganovic.rezervacija;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import nemanja.roganovic.rezervacija.adapter.ReservationAdapter;
import nemanja.roganovic.rezervacija.adapter.RoomAdapter;
import nemanja.roganovic.rezervacija.model.Reservation;
import nemanja.roganovic.rezervacija.model.Room;
import nemanja.roganovic.rezervacija.service.ReservationService;
import nemanja.roganovic.rezervacija.service.RoomService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity implements RoomListener, ReservationListener {

    RecyclerView roomRec;
    RoomAdapter roomAdapter;
    RecyclerView.LayoutManager roomViewManager;
    RoomService roomService;
    Button btnLogout;
    TextView userName;

    RecyclerView resRec;
    ReservationAdapter resAdapter;
    RecyclerView.LayoutManager resViewManager;
    ReservationService resService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        roomService = ApiUtils.getRoomService();
        resService = ApiUtils.getReservationService();

        roomViewManager = new LinearLayoutManager(this);
        resViewManager = new LinearLayoutManager(this);

        roomAdapter = new RoomAdapter(this);
        resAdapter = new ReservationAdapter(this);

        userName = findViewById(R.id.user_name);
        userName.setText("Welcome " + ApiUtils.getCurrentUser().getIme() + " " +
                ApiUtils.getCurrentUser().getPrezime() + "!");

        roomRec = findViewById(R.id.room_list);
        resRec = findViewById(R.id.reservation_list);
        btnLogout = findViewById(R.id.user_log_out);

        roomRec.setAdapter(roomAdapter);
        roomRec.setLayoutManager(roomViewManager);

        resRec.setAdapter(resAdapter);
        resRec.setLayoutManager(resViewManager);

        resRec.setNestedScrollingEnabled(false);
        roomRec.setNestedScrollingEnabled(false);

        roomService.getAllRooms().enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    roomAdapter.setRooms(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Toast.makeText(UserActivity.this, "No internet connection!", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loadReservations();
    }

    public void loadReservations() {
        resService.getAllReservations(ApiUtils.getCurrentUser().getUsername()).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful()) {
                    resAdapter.setReservations(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {

            }
        });
    }

    @Override
    public void reserveClicked(final String roomNumber) {
        final Calendar currentDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(UserActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int fromYear, int fromMonth, int fromDayOfMonth) {
                final RequestBody fromDate = RequestBody.create(MediaType.parse("text/plain"), fromDayOfMonth + "/" + fromMonth + "/" + fromYear);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int toYear, int toMonth, int toDayOfMonth) {
                        RequestBody toDate = RequestBody.create(MediaType.parse("text/plain"), (toDayOfMonth + 1) + "/" + toMonth + "/" + toYear);
                        RequestBody user = RequestBody.create(MediaType.parse("text/plain"), ApiUtils.getCurrentUser().getUsername());
                        roomService.reserveRoom(Integer.parseInt(roomNumber), fromDate, toDate, user)
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        Toast.makeText(UserActivity.this, "Reservation added!", Toast.LENGTH_SHORT).show();
                                        loadReservations();
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });
                    }
                },
                        fromYear, fromMonth, fromDayOfMonth);
                datePickerDialog.show();
            }
        },
                currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void cancelClicked(String reservationId) {
        resService.cancelReservation(reservationId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadReservations();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
