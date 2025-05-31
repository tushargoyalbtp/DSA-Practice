package ParkingLotSystemDesign;

import ParkingLotSystemDesign.VehicleType.Vehicle;
import ParkingLotSystemDesign.VehicleType.VehicleType;

public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType vehicleType;
    private Vehicle vehicle;
    private boolean isOccupied;

    public ParkingSpot(int spotNumber, VehicleType vehicleType){
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
        isOccupied = false;
    }

    public synchronized boolean isAvailable(){
        return !isOccupied;
    }

    public synchronized boolean park(Vehicle vehicle){
        if(isOccupied || vehicle.getVehicleType() != vehicleType){
            return false;
        }
        this.vehicle = vehicle;
        isOccupied = true;
        return true;
    }

    public synchronized void unpark() {
        vehicle = null;
        isOccupied = false;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}
