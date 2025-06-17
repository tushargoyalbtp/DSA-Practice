package ConcertTicketBookingSystem;

import java.util.List;

public class Booking {
    private final String id;
    private final User user;
    private final Concert concert;
    private final List<Seat> seatList;
    private final double price;
    private BookingStatus bookingStatus;

    public Booking(String id, User user, Concert concert, List<Seat> seatList) {
        this.id = id;
        this.user = user;
        this.concert = concert;
        this.seatList = seatList;
        this.price = calculatePrice();
        this.bookingStatus = BookingStatus.PENDING;
    }

    public void confirmBooking() {
        if (bookingStatus == BookingStatus.PENDING) {
            bookingStatus = BookingStatus.CONFIRMED;

            // Notify user (e.g., send email or SMS)
            notifyUserBookingConfirmed();

            System.out.printf("Booking %s confirmed for user %s (%s)%n",
                    id, user.getName(), user.getEmail());
        } else {
            System.out.printf("Booking %s could not be confirmed. Current status: %s%n",
                    id, bookingStatus);
        }
    }

    public void cancelBooking() {
        if (bookingStatus == BookingStatus.CONFIRMED) {
            bookingStatus = BookingStatus.CANCELLED;

            // Release the booked seats
            seatList.forEach(Seat::release);

            // Notify user about cancellation
            notifyUserBookingCancelled();

            System.out.printf("Booking %s cancelled for user %s (%s)%n",
                    id, user.getName(), user.getEmail());
        } else {
            System.out.printf("Booking %s cannot be cancelled. Current status: %s%n",
                    id, bookingStatus);
        }
    }

    // Example notification methods
    private void notifyUserBookingConfirmed() {
        System.out.printf("Notification: Dear %s, your booking %s is confirmed!%n",
                user.getName(), id);
        // Here you could integrate email, SMS, push notifications etc.
    }

    private void notifyUserBookingCancelled() {
        System.out.printf("Notification: Dear %s, your booking %s has been cancelled.%n",
                user.getName(), id);
        // Here you could integrate email, SMS, push notifications etc.
    }


    public double calculatePrice(){
        return seatList.stream().mapToDouble(Seat::getPrice).sum();
    }


    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Concert getConcert() {
        return concert;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public double getPrice() {
        return price;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
}
