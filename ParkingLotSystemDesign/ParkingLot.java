package ParkingLotSystemDesign;

import ParkingLotSystemDesign.VehicleType.Vehicle;
import ParkingLotSystemDesign.fee.FeeStrategy;
import ParkingLotSystemDesign.fee.FlatRateFeeStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingFloor> floors;
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
    private FeeStrategy feeStrategy;

    private ParkingLot(){
        floors = new ArrayList<>();
        feeStrategy = new FlatRateFeeStrategy();
    }

    public static synchronized ParkingLot getInstance(){
        if(instance == null){
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy){
        this.feeStrategy = feeStrategy;
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) throws Exception{
        for(ParkingFloor parkingFloor : floors){
            Optional<ParkingSpot> parkingSpot = parkingFloor.getAvailableSpot(vehicle.getVehicleType());
            if(parkingSpot.isPresent()){
                ParkingSpot spot = parkingSpot.get();
                if(spot.park(vehicle)){
                    String ticketId = UUID.randomUUID().toString();
                    Ticket ticket = new Ticket(ticketId, vehicle, spot);
                    activeTickets.put(ticketId, ticket);
                    return ticket;
                }
            }
        }
        throw new Exception("No available spot for " + vehicle.getVehicleType());
    }

    public synchronized double unparkVehicle(String ticketId) throws Exception {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) throw new Exception("Invalid ticket");

        ParkingSpot parkingSpot = ticket.getSpot();
        parkingSpot.unpark();

        ticket.setExitTimestamp();
        return feeStrategy.calculateFees(ticket);
    }


    public List<ParkingFloor> getParkingFloors() {
        return floors;
    }
}
