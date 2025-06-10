package LiveShowBookingSystem;

import java.util.Objects;

public class TimeSlot {
    private final int startHour;
    private final int endHour;

    public TimeSlot(int startHour, int endHour) {
        if (startHour < 9 || startHour > 20 || endHour < 10 || endHour > 21 || startHour >= endHour){
            throw new IllegalArgumentException("Invalid time slot. Must be between 9 AM to 9 PM");
        }
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public boolean overlaps(TimeSlot other){
        return this.startHour < other.endHour && other.endHour < this.endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return startHour == timeSlot.startHour && endHour == timeSlot.endHour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startHour, endHour);
    }

    @Override
    public String toString() {
        return startHour + ":00 - " + endHour + ":00";
    }

}
