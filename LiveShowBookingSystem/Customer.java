package LiveShowBookingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends User {

    private List<Booking> bookingList;

    public Customer(String userName, String email) {
        super(userName, email);
        this.bookingList = new ArrayList<>();
    }

    public void addBooking(Booking booking){
        bookingList.add(booking);
    }

    public void removeBooking(Booking booking){
        bookingList.remove(booking);
    }

    public List<Booking> getTodaysBookings(){
        if (bookingList == null) return List.of(); // or Collections.emptyList()
        return bookingList.stream()
                .filter(b -> b.getBookingStatus() == BookingStatus.CONFIRMED)
                .collect(Collectors.toList());
    }
}
