package ParkingLotSystemDesign;

import ParkingLotSystemDesign.VehicleType.VehicleType;

import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private  final int floorNumber;
    private  final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpots) {
        this.floorNumber = floorNumber;
        this.parkingSpots = parkingSpots;
    }

    public synchronized Optional<ParkingSpot> getAvailableSpot(VehicleType vehicleType){
        return parkingSpots.stream().filter(parkingSpot ->
            parkingSpot.isAvailable() && parkingSpot.getVehicleType() == vehicleType).findFirst();
    }
    public int getFloorNumber(){
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpots(){
        return parkingSpots;
    }

    public List<Integer> getAvailableSpots(VehicleType vehicleType){
        return parkingSpots.stream()
                .filter(parkingSpot -> parkingSpot.isAvailable() && parkingSpot.getVehicleType() == vehicleType)
                .map(ParkingSpot::getSpotNumber).toList();
    }
}
