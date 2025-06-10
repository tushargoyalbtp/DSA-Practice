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
    public String toString() {
        return String.format("%d:00-%d:00", startHour, endHour);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) obj;
        return startHour == timeSlot.startHour && endHour == timeSlot.endHour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startHour, endHour);
    }


}
