package LiveShowBookingSystem;

import LiveShowBookingSystem.Exceptions.BookingException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShowSlot {
    private LiveShow liveShow;
    private TimeSlot timeSlot;
    private int totalCapacity;
    private int bookedCapacity;
    private List<Booking> bookingList;
    private Queue<WaitlistEntry> waitList;

    public ShowSlot(LiveShow liveShow,  TimeSlot timeSlot , int totalCapacity) {
        this.liveShow = liveShow;
        this.totalCapacity = totalCapacity;
        this.timeSlot = timeSlot;
        this.bookedCapacity = 0;
        bookingList = new ArrayList<>();
        waitList = new LinkedList<>();
    }

    public synchronized boolean canBook(int numberOfPersons) {
        return getAvailableCapacity() >= numberOfPersons;
    }

    public synchronized Booking book(Customer customer, int numberofpersons) throws BookingException {

        if (!canBook(numberofpersons)) {
            throw new BookingException("no seats available , can't proceeed with the booking systemn");
        }

        Booking booking = new Booking(customer, this, numberofpersons);
        bookingList.add(booking);
        bookedCapacity += numberofpersons;
        liveShow.incrementBooking();
        customer.addBooking(booking);
        return booking;
    }

    public synchronized void addToWaitList(Customer customer, int numberOfPersons) {
        waitList.offer(new WaitlistEntry(customer, numberOfPersons));
    }

    public synchronized void cancelBooking(Booking booking) {
        if (bookingList.remove(booking)) {
            bookedCapacity = bookedCapacity - booking.getNumberOfPersons();
            liveShow.decrementBooking();
            booking.getCustomer().removeBooking(booking);
            booking.setBookingStatus(BookingStatus.CANCELLED);


            processWaitList();
        }
    }

    public void processWaitList() {

        while (!waitList.isEmpty() && getAvailableCapacity() > 0) {
            WaitlistEntry waitlistEntry = waitList.peek();
            if (canBook(waitlistEntry.getNumberofpersons())) {
                waitList.poll();
                try {
                    book(waitlistEntry.getCustomer(), waitlistEntry.getNumberofpersons());
                } catch (BookingException be) {
                    System.err.println("Error processing waitlist: " + be.getMessage());
                }
            } else {
                break;
            }
        }
    }

    public int getAvailableCapacity() {
        return totalCapacity - bookedCapacity;
    }

    public LiveShow getLiveShow() {
        return liveShow;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getBookedCapacity() {
        return bookedCapacity;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public Queue<WaitlistEntry> getWaitList() {
        return waitList;
    }
}
