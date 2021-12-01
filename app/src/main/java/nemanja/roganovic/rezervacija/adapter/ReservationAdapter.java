package nemanja.roganovic.rezervacija.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nemanja.roganovic.rezervacija.R;
import nemanja.roganovic.rezervacija.ReservationListener;
import nemanja.roganovic.rezervacija.RoomListener;
import nemanja.roganovic.rezervacija.model.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationViewHolder> {
    private List<Reservation> reservations = new ArrayList<>();
    private ReservationListener listener;

    public ReservationAdapter(ReservationListener listener) {
        this.listener = listener;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder reservationViewHolder, int i) {
        final Reservation reservation = reservations.get(i);
        reservationViewHolder.room.setText(reservation.getRoom());
        reservationViewHolder.dateFrom.setText(reservation.getDateFrom());
        reservationViewHolder.dateTo.setText(reservation.getDateTo());

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = formatter.parse(reservation.getDateFrom());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startTime = startDate.getTime();

        Date endDate = null;
        try {
            endDate = formatter.parse(reservation.getDateTo());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long endTime = endDate.getTime();

        int cijenaUkupno = (int) ((endTime-startTime)/86400000)*Integer.parseInt(reservation.getCijenaDan());

        reservationViewHolder.price.setText(cijenaUkupno+" €");

        reservationViewHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancelClicked(reservation.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }
}

class ReservationViewHolder extends RecyclerView.ViewHolder {
    public TextView room, dateFrom, dateTo, price;
    public Button cancel;

    public ReservationViewHolder(View view) {
        super(view);
        room = view.findViewById(R.id.reservation_room);
        dateFrom = view.findViewById(R.id.reservation_date_from);
        dateTo = view.findViewById(R.id.reservation_date_to);
        price = view.findViewById(R.id.reservation_price);
        cancel = view.findViewById(R.id.reservation_cancel);
    }
}
