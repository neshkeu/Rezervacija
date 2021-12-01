package nemanja.roganovic.rezervacija;

import nemanja.roganovic.rezervacija.model.User;
import nemanja.roganovic.rezervacija.service.HotelService;
import nemanja.roganovic.rezervacija.service.ReservationService;
import nemanja.roganovic.rezervacija.service.RoomService;
import nemanja.roganovic.rezervacija.service.UserService;

public class ApiUtils {

    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ApiUtils.currentUser = currentUser;
    }

    public static final String BASE_URL = "http://10.0.2.2/recepcija/api/";

    public static RoomService getRoomService() {
        return RetrofitClient.getClient(BASE_URL).create(RoomService.class);
    }

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

    public static HotelService getHotelService() {
        return RetrofitClient.getClient(BASE_URL).create(HotelService.class);
    }

    public static ReservationService getReservationService() {
        return RetrofitClient.getClient(BASE_URL).create(ReservationService.class);
    }
}