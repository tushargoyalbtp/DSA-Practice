package LiveShowBookingSystem.Exceptions;

public class SlotConflictException extends Exception{
    public SlotConflictException(String message){
        super(message);
    }
}
