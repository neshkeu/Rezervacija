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
import nemanja.roganovic.rezervacija.model.Room;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder> {
    private List<Room> rooms = new ArrayList<>();
    private RoomListener listener;

    public RoomAdapter(RoomListener listener) {
        this.listener = listener;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int i) {
        final Room room = rooms.get(i);
        roomViewHolder.roomHotel.setText(room.getHotel());
        roomViewHolder.roomNumber.setText(room.getBroj());
        roomViewHolder.noOfBeds.setText(room.getBrojkreveta());
        if(room.getTerasa().equals("0")) {
            roomViewHolder.balcony.setText("NO");
        } else {
            roomViewHolder.balcony.setText("YES");
        }
        roomViewHolder.price.setText(room.getCijena()+" €");

        roomViewHolder.reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.reserveClicked(room.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }
}

class RoomViewHolder extends RecyclerView.ViewHolder {
    public TextView roomHotel, roomNumber, noOfBeds, balcony, price, reserve;

    public RoomViewHolder(View view) {
        super(view);
        roomHotel = view.findViewById(R.id.room_hotel);
        roomNumber = view.findViewById(R.id.room_number);
        noOfBeds = view.findViewById(R.id.room_noOfBeds);
        balcony = view.findViewById(R.id.room_balcony);
        price = view.findViewById(R.id.room_price);
        reserve = view.findViewById(R.id.reserve);
    }
}
