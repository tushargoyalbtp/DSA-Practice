package LiveShowBookingSystem;

import LiveShowBookingSystem.Exceptions.SlotConflictException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LiveShow {
    private String showName;
    private Genre genre;
    private Organizer organizer;
    private Map<TimeSlot, ShowSlot> timeSlotShowSlotMap;
    private int totalBookings;
    private double ratings;


    public LiveShow(String showName, Genre genre, Organizer organizer){
        this.showName = showName;
        this.genre = genre;
        this.organizer = organizer;
        timeSlotShowSlotMap = new HashMap<>();
        totalBookings = 0;
        ratings = 0.0;
    }

    public void addTimeSlot(TimeSlot timeSlot, int capacity) throws SlotConflictException {

        for(TimeSlot existingSlots : timeSlotShowSlotMap.keySet()){
            if(timeSlot.overlaps(existingSlots)) {
                throw new SlotConflictException("Time slot overlaps with existing slot: " + existingSlots);
            }
        }

        timeSlotShowSlotMap.put(timeSlot, new ShowSlot(this, timeSlot, capacity));
    }


    public ShowSlot getShowSlots(TimeSlot slot){
        return timeSlotShowSlotMap.get(slot);
    }

    public List<ShowSlot> getAvailableSlots(){
        return timeSlotShowSlotMap.values().stream().filter(slots -> slots.getAvailableCapacity() > 0).collect(Collectors.toList());
    }

    public void incrementBooking(){
        totalBookings++;
    }

    public void decrementBooking(){
        if(totalBookings > 0){
            totalBookings--;
        }
    }

    public String getShowName() {
        return showName;
    }

    public Genre getGenre() {
        return genre;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public Map<TimeSlot, ShowSlot> getTimeSlotShowSlotMap() {
        return timeSlotShowSlotMap;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public double getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "LiveShow{" +
                "showName='" + showName + '\'' +
                ", genre=" + genre +
                ", organizer=" + organizer +
                ", timeSlotShowSlotMap=" + timeSlotShowSlotMap +
                ", totalBookings=" + totalBookings +
                ", ratings=" + ratings +
                '}';
    }
}
