package com.mango.dto;

import com.mango.model.Vehicle;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class VehicleDTO {

    @NotNull
    private Vehicle.Type vehicleType;

    @NotNull
    @Length(max = 100)
    private String model;

    @NotNull
    @Length(max = 20)
    private String licenseNumber;

    private LocationDTO currentLocation;

    @NotNull
    @Min(value = 1)
    @Max(value = 20)
    private Integer passengerSeats;

    private boolean babyTransport;

    private boolean petTransport;


    public VehicleDTO (Vehicle vehicle){
        this.vehicleType = vehicle.getVehicleType();
        this.model = vehicle.getModel();
        this.licenseNumber = vehicle.getLicenseNumber();
        this.currentLocation = new LocationDTO(vehicle.getCurrentLocation());
        this.passengerSeats = vehicle.getPassengerSeats();
        this.babyTransport = vehicle.isBabyTransport();
        this.petTransport = vehicle.isPetTransport();
    }

    public VehicleDTO(){}

    public Vehicle.Type getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Vehicle.Type vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocationDTO getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LocationDTO currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Integer getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(Integer passengerSeats) {
        this.passengerSeats = passengerSeats;
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
