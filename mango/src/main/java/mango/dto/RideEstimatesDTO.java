package mango.dto;

import mango.model.RideLocation;
import mango.model.Vehicle;

import java.util.ArrayList;

public class RideEstimatesDTO {

    private ArrayList<RideLocation> locations;

    private Vehicle.Type vehicleType;

    private boolean babyTransport;

    private boolean petTransport;


    public RideEstimatesDTO(ArrayList<RideLocation> locations, Vehicle.Type vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public RideEstimatesDTO(){}

    public ArrayList<RideLocation> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<RideLocation> locations) {
        this.locations = locations;
    }

    public Vehicle.Type getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Vehicle.Type vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }
}
