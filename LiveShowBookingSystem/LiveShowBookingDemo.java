package LiveShowBookingSystem;

import LiveShowBookingSystem.Exceptions.BookingException;

import java.util.List;

public class LiveShowBookingDemo {
    public static void main(String[] args) {
        LiveShowBookingSystem system = new LiveShowBookingSystem();

        try {
            // Register users
            Customer customer1 = system.registerUser("john_doe", "john@email.com");
            Customer customer2 = system.registerUser("jane_smith", "jane@email.com");
            Organizer organizer1 = system.registerOrganizer("comedy_central", "comedy@email.com");

            // Create shows
            LiveShow comedyShow = system.createLiveShow("Stand Up Night", Genre.COMEDY, "comedy_central");
            LiveShow techShow = system.createLiveShow("Tech Talk", Genre.TECH, "comedy_central");

            // Add time slots
            system.addShowTimeSlot("Stand Up Night", new TimeSlot(19, 20), 50); // 7-8 PM
            system.addShowTimeSlot("Stand Up Night", new TimeSlot(20, 21), 50); // 8-9 PM
            system.addShowTimeSlot("Tech Talk", new TimeSlot(10, 11), 30); // 10-11 AM
            system.addShowTimeSlot("Tech Talk", new TimeSlot(11, 15), 20); // 11 AM - 3 PM (newly added)

            // Search shows
            System.out.println("Comedy Shows Available:");
            List<ShowSlot> comedySlots = system.searchShowByGenre(Genre.COMEDY);
            comedySlots.forEach(System.out::println);

            // Book tickets
            Booking booking1 = system.bookShow("john_doe", "Stand Up Night", new TimeSlot(19, 20), 2);
            System.out.println("Booking successful: " + booking1);

            // Book new non-conflicting slot (11–15)
            Booking booking3 = system.bookShow("john_doe", "Tech Talk", new TimeSlot(11, 15), 1);
            System.out.println("Booking successful: " + booking3);

            // Book different non-conflicting slot
            Booking booking2 = system.bookShow("john_doe", "Tech Talk", new TimeSlot(10, 11), 1);
            System.out.println("Booking successful: " + booking2);

            // View customer bookings
            System.out.println("\nJohn's Bookings:");
            system.getCustomerBookings("john_doe").forEach(System.out::println);

            // Trending shows
            System.out.println("\nTrending Shows:");
            system.getTrendingShows(5).forEach(show ->
                    System.out.println(show + " - Bookings: " + show.getTotalBookings()));

            // Cancel booking
            system.cancelBooking(booking1.getBookingId());
            System.out.println("Booking cancelled: " + booking1.getBookingId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
