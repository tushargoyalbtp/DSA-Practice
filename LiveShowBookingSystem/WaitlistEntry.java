package LiveShowBookingSystem;

import java.time.LocalDateTime;

public class WaitlistEntry {
    private Customer customer;
    private int numberofpersons;
    private LocalDateTime waitlisttime;

    public WaitlistEntry(Customer customer, int numberofpersons) {
        this.customer = customer;
        this.numberofpersons = numberofpersons;
        this.waitlisttime = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getNumberofpersons() {
        return numberofpersons;
    }

    public LocalDateTime getWaitlisttime() {
        return waitlisttime;
    }
}
