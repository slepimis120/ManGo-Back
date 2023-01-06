package mango.dto;

import mango.model.Location;
import mango.model.Vehicle;
import mango.model.Vehicle.Type;

public class VehicleDTO {

    private Integer id;

    private Integer driverId;

    private Vehicle.Type vehicleType;

    private String model;

    private String licenseNumber;

    private LocationDTO currentLocation;

    private Integer passengerSeats;

    private boolean babyTransport;

    private boolean petTransport;


    public VehicleDTO (Vehicle vehicle){
        this.id = vehicle.getId();
        this.driverId = vehicle.getDriver().getId();
        this.vehicleType = vehicle.getVehicleType();
        this.model = vehicle.getModel();
        this.licenseNumber = vehicle.getLicenseNumber();
        this.currentLocation = new LocationDTO(vehicle.getCurrentLocation());
        this.passengerSeats = vehicle.getPassengerSeats();
        this.babyTransport = vehicle.isBabyTransport();
        this.petTransport = vehicle.isPetTransport();
    }

    public VehicleDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Type getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Type vehicleType) {
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
