package mango.dto;

import mango.model.RideLocation;
import mango.model.Vehicle;

import java.util.List;


public class RideEstimatesDTO {

    private List<RideLocation> locations;

    private Vehicle.Type vehicleType;

    private boolean babyTransport;

    private boolean petTransport;


    public RideEstimatesDTO(List<RideLocation> locations, Vehicle.Type vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public RideEstimatesDTO(){}

    public List<RideLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocation> locations) {
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
