package mango.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import mango.dto.VehicleDTO;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "DRIVERID", referencedColumnName = "id")
    private Driver driver;

    @Column(name = "VEHICLETYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type vehicleType;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @Column(name = "LICENSENUMBER", nullable = false)
    private String licenseNumber;

    @OneToOne
    @JoinColumn(name = "CURRENTLOCATION", referencedColumnName = "id")
    private Location currentLocation;

    @Column(name = "PASSENGERSEATS", nullable = false)
    private Integer passengerSeats;

    @Column(name = "BABYTRANSPORT", nullable = false)
    private boolean babyTransport;

    @Column(name = "PETTRANSPORT", nullable = false)
    private boolean petTransport;

    public enum Type{
        STANDARD, LUXURY, VAN
    }

    public Vehicle(Integer id, Driver driver, Type vehicleType, String model, String licenseNumber, Location currentLocation, Integer passengerSeats, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.driver = driver;
        this.vehicleType = vehicleType;
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.passengerSeats = passengerSeats;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public Vehicle() {
    }

    public Vehicle(VehicleDTO vehicleDTO){
        this.vehicleType = vehicleDTO.getVehicleType();
        this.model = vehicleDTO.getModel();
        this.licenseNumber = vehicleDTO.getLicenseNumber();
        this.currentLocation = new Location(vehicleDTO.getCurrentLocation());
        this.passengerSeats = vehicleDTO.getPassengerSeats();
        this.babyTransport = vehicleDTO.isBabyTransport();
        this.petTransport = vehicleDTO.isPetTransport();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
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