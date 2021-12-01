package nemanja.roganovic.rezervacija.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nemanja.roganovic.rezervacija.R;
import nemanja.roganovic.rezervacija.RoomListener;
import nemanja.roganovic.rezervacija.model.Hotel;

public class HotelAdapter extends RecyclerView.Adapter<HotelViewHolder> {
    private List<Hotel> hotels = new ArrayList<>();
    private RoomListener listener;

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder hotelViewHolder, int i) {
        final Hotel hotel = hotels.get(i);
        hotelViewHolder.name.setText(hotel.getNaziv());
        hotelViewHolder.stars.setText(hotel.getZvjezdice());
        if (hotel.getBazen().equals("0")) {
            hotelViewHolder.pool.setText("NO");
        } else {
            hotelViewHolder.pool.setText("YES");
        }

        if (hotel.getSpa().equals("0")) {
            hotelViewHolder.spa.setText("NO");
        } else {
            hotelViewHolder.spa.setText("YES");
        }
        hotelViewHolder.fromCentre.setText(hotel.getOdcentra());

    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }
}

class HotelViewHolder extends RecyclerView.ViewHolder {
    public TextView name, stars, pool, spa, fromCentre;

    public HotelViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.hotel_room);
        stars = view.findViewById(R.id.hotel_date_from);
        pool = view.findViewById(R.id.hotel_date_to);
        spa = view.findViewById(R.id.hotel_spa);
        fromCentre = view.findViewById(R.id.hotel_from_centre);
    }
}
