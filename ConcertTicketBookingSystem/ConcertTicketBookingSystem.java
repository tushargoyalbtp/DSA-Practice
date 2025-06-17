package ConcertTicketBookingSystem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ConcertTicketBookingSystem {
    private static ConcertTicketBookingSystem instance;
    private final Map<String, Concert> concertMap;
    private final Map<String, Booking> bookingMap;
    private final Object lock = new Object();

    public ConcertTicketBookingSystem() {
        this.concertMap = new ConcurrentHashMap<>();
        this.bookingMap = new ConcurrentHashMap<>();
    }


    public static synchronized ConcertTicketBookingSystem getInstance(){
        if (instance == null){
            instance = new ConcertTicketBookingSystem();
        }
        return instance;
    }

    public void addConvert(Concert concert){
        concertMap.put(concert.getId(), concert);
    }

    public void addBooking(Booking booking){
        bookingMap.put(booking.getId(), booking);
    }

    public Concert getConcert(String concertId) {
        return concertMap.get(concertId);
    }

    public List<Concert> searConvert(String artist, String venue, LocalDateTime localDateTime){
        return concertMap.values().stream().filter(concert -> concert.getArtist().equalsIgnoreCase(artist) &&
                concert.getVenue().equalsIgnoreCase(venue) &&
                        concert.getLocalDateTime().equals(localDateTime))
                .collect(Collectors.toList());
    }

    public Booking bookTickets(User user, Concert concert, List<Seat> seatList){
        synchronized (lock) {
            for(Seat seat : seatList){
                if(seat.getSeatStatus() != SeatStatus.AVAILABLE){
                    throw new SeatNotAvailableException("Seat" + seat.getSeatNumber() + "is not available");
                }
            }

            seatList.forEach(Seat::book);

            String bookingId = generateBookingId();
            Booking booking = new Booking(bookingId, user, concert, seatList);
            bookingMap.put(bookingId, booking);

            //process payment
            processPayment(booking);

            // confirmed
            booking.confirmBooking();

            System.out.println("Booking " + booking.getId() + " - " + booking.getSeatList().size() + " seats booked");

            return booking;
        }
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookingMap.get(bookingId);
        if (booking != null) {
            booking.cancelBooking();
            bookingMap.remove(bookingId);
            // Notify user about booking cancellation
            notifyUserCancellation(booking);
        }
    }

    private void processPayment(Booking booking) {
// Simulating payment processing
        boolean paymentSuccessful = true; // Assuming payment is successful
        if (paymentSuccessful) {
            System.out.println("Payment processed successfully for booking " + booking.getId());
        } else {
            throw new PaymentFailureException("Payment failed for booking " + booking.getId());
        }
    }

    private String generateBookingId() {
        return "BKG" + UUID.randomUUID();
    }


    private void notifyUser(Booking booking) {
        // Simulate sending email or SMS to the user
        System.out.println("Notification: Dear " + booking.getUser().getName() +
                ", your booking with ID " + booking.getId() + " for " + booking.getConcert().getArtist() +
                " concert at " + booking.getConcert().getVenue() + " has been confirmed.");
    }

    private void notifyUserCancellation(Booking booking) {
        // Simulate sending cancellation notification to the user
        System.out.println("Notification: Dear " + booking.getUser().getName() +
                ", your booking with ID " + booking.getId() + " has been cancelled.");
    }

}
