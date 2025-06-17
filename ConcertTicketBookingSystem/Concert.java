package ConcertTicketBookingSystem;

import java.time.LocalDateTime;
import java.util.List;

public class Concert {
    private final String id;
    private final String artist;
    private final String venue;
    private final LocalDateTime localDateTime;
    private final List<Seat> seatList;


    public Concert(String id, String artist, String venue, LocalDateTime localDateTime, List<Seat> seatList) {
        this.id = id;
        this.artist = artist;
        this.venue = venue;
        this.localDateTime = localDateTime;
        this.seatList = seatList;
    }

    public String getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }
}
