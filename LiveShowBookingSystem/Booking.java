package LiveShowBookingSystem;

import java.time.LocalDateTime;

public class Booking {
    private static int bookingCounter = 1;
    private String bookingId;
    private Customer customer;
    private ShowSlot showSlot;
    private int numberOfPersons;
    private BookingStatus bookingStatus;
    private LocalDateTime localDateTime;


    public Booking(Customer customer, ShowSlot showSlot, int numberOfPersons) {
        this.bookingId = "LBKS" + String.format("%06D", bookingCounter++);
        this.customer = customer;
        this.showSlot = showSlot;
        this.numberOfPersons = numberOfPersons;
        bookingStatus = BookingStatus.CONFIRMED;
        localDateTime = LocalDateTime.now();
    }

    public static int getBookingCounter() {
        return bookingCounter;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ShowSlot getShowSlot() {
        return showSlot;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
