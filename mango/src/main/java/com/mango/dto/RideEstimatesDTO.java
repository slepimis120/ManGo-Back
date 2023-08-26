package com.mango.dto;

import com.mango.model.Vehicle;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public class RideEstimatesDTO {

    @NotEmpty (message = "Field Locations is required!")
    @NotNull
    private List<RideLocationDTO> locations;

    @NotEmpty (message = "Field Vehicle Type is required!")
    @NotNull
    private Vehicle.Type vehicleType;

    @NotEmpty (message = "Field Baby Transport is required!")
    @NotNull
    private boolean babyTransport;

    @NotEmpty(message = "Field Pet Transport is required!")
    @NotNull
    private boolean petTransport;


    public RideEstimatesDTO(List<RideLocationDTO> locations, Vehicle.Type vehicleType, boolean babyTransport, boolean petTransport) {
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public RideEstimatesDTO(){}

    public List<RideLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocationDTO> locations) {
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
