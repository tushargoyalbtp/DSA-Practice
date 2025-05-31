package ParkingLotSystemDesign.fee;

import ParkingLotSystemDesign.Ticket;

public interface FeeStrategy {
    double calculateFees(Ticket ticket);
}
