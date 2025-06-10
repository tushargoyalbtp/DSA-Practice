package LiveShowBookingSystem;

import LiveShowBookingSystem.Exceptions.BookingException;
import LiveShowBookingSystem.Exceptions.SlotConflictException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LiveShowBookingSystem {
    private Map<String, Customer> customerHashMap;
    private Map<String, Organizer> organizerHashMap;
    private Map<String, LiveShow> liveShowHashMap;
    private RankingStrategy rankingStrategy;

    public LiveShowBookingSystem() {
        this.customerHashMap = new ConcurrentHashMap<>();
        this.organizerHashMap = new ConcurrentHashMap<>();
        this.liveShowHashMap = new ConcurrentHashMap<>();
        this.rankingStrategy = new TimeBasedRanking();
    }

    //user management

    public Customer registerUser(String username, String email) {
        Customer customer = new Customer(username, email);
        customerHashMap.put(username, customer);
        return customer;
    }

    public Organizer registerOrganizer(String username, String email) {
        Organizer organizer = new Organizer(username, email);
        organizerHashMap.put(username, organizer);
        return organizer;
    }

    public Customer getCustomer(String username) {
        if (customerHashMap.containsKey(username)) {
            return customerHashMap.get(username);
        }
        return null;
    }

    public Organizer getOrganizer(String username) {
        if (organizerHashMap.containsKey(username)) {
            return organizerHashMap.get(username);
        }
        return null;
    }

    // show-management
    public LiveShow createLiveShow(String showName, Genre genreType, String organizerUserName) {
        Organizer organizer = organizerHashMap.get(organizerUserName);
        if (organizer == null) {
            throw new IllegalArgumentException("Organizer not found: " + organizerUserName);
        }
        LiveShow liveShow = new LiveShow(showName, genreType, organizer);
        liveShowHashMap.put(showName, liveShow);
        organizer.addShows(liveShow);
        return liveShow;
    }


    public void addShowTimeSlot(String showName, TimeSlot timeSlot, int capacity) throws SlotConflictException {

        LiveShow liveShow = liveShowHashMap.get(showName);
        if (Objects.isNull(liveShow)) {
            throw new IllegalArgumentException("Live show not found: " + showName);
        }
        liveShow.addTimeSlot(timeSlot, capacity);
    }


    //Searching and ranking
    public List<ShowSlot> searchShowByGenre(Genre genre) {
        List<ShowSlot> showSlotList = liveShowHashMap.values().stream().filter(liveShow -> liveShow.getGenre() == genre)
                .flatMap(liveShow -> liveShow.getAvailableSlots().stream()).toList();
        return showSlotList;
    }

    public List<ShowSlot> getAllAvailableShows(){
        List<ShowSlot> showSlotList = liveShowHashMap.values()
                .stream()
                .flatMap(liveShow -> liveShow.getAvailableSlots().stream())
                .toList();
        return showSlotList;
    }

    public void setRankingStrategy(RankingStrategy rankingStrategy){
        this.rankingStrategy = rankingStrategy;
    }

    //Booking Management
    public Booking bookShow(String customerUserName, String showName, TimeSlot timeSlot, int numberofpersons) throws BookingException {
        Customer customer = customerHashMap.get(customerUserName);
        if(customer == null){
            throw new IllegalArgumentException("Customer not found: " + customerUserName);
        }

        LiveShow liveShow = liveShowHashMap.get(showName);

        // Check for time slot conflicts
        if (hasTimeSlotConflict(customer, timeSlot)) {
            throw new BookingException("Customer already has a booking in this time slot");
        }

        if(liveShow == null){
            throw new IllegalArgumentException("Live show not found: " + showName);
        }

        ShowSlot showSlot = liveShow.getShowSlots(timeSlot);
        if(showSlot == null){
            throw new IllegalArgumentException("Time slot not available for this show");
        }

        if(showSlot.canBook(numberofpersons)){
            return showSlot.book(customer, numberofpersons);
        }
        else{
            showSlot.addToWaitList(customer, numberofpersons);
            throw new BookingException("Show is full. Added to waitlist.");
        }

    }

    private boolean hasTimeSlotConflict(Customer customer, TimeSlot newTimeSlot) {
        return customer.getTodaysBookings().stream()
                .anyMatch(booking -> booking.getShowSlot().getTimeSlot().overlaps(newTimeSlot));
    }

    public void cancelBooking(String bookingId) {
        for (Customer customer : customerHashMap.values()) {
            for (Booking booking : customer.getTodaysBookings()) {
                if (booking.getBookingId().equals(bookingId) &&
                        booking.getBookingStatus() == BookingStatus.CONFIRMED) {
                    booking.getShowSlot().cancelBooking(booking);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Booking not found: " + bookingId);
    }

    // Trending Shows
    public List<LiveShow> getTrendingShows(int limit) {
        return liveShowHashMap.values().stream()
                .sorted(Comparator.comparing(LiveShow::getTotalBookings).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }
    // View Bookings
    public List<Booking> getCustomerBookings(String customerUsername) {
        Customer customer = customerHashMap.get(customerUsername);
        return customer != null ? customer.getTodaysBookings() : new ArrayList<>();
    }

    public List<LiveShow> getOrganizerShows(String organizerUsername) {
        Organizer organizer = organizerHashMap.get(organizerUsername);
        return organizer != null ? organizer.getOrganizedShows() : new ArrayList<>();
    }

}
