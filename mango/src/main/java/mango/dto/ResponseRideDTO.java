package mango.dto;

import mango.model.*;

import java.util.ArrayList;
import java.util.Date;

public class ResponseRideDTO {
    private int id;
    private Date startTime;
    private Date endTime;
    private Integer totalCost;
    private Driver driver;
    private ArrayList<RidePassengerDTO> passengers;
    private Integer estimatedTimeInMinutes;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private ArrayList<RideLocationDTO> locations;
    private Ride.Status status;
    private Rejection rejection;

    public ResponseRideDTO(Driver driver, ArrayList<RideLocationDTO> locations, ArrayList<RidePassengerDTO> passengers, String vehicleType, boolean babyTransport, boolean petTransport, Rejection rejection){
        this.id = 0;
        this.driver = driver;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.startTime = null;
        this.endTime = null;
        this.totalCost = null;
        this.estimatedTimeInMinutes = null;
        this.status = Ride.Status.pending;
        this.rejection = rejection;
    }

    public ResponseRideDTO(){}

    public int getId() {
        return id;
    }

    public void setRideId(int rideId) {
        this.id = rideId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ArrayList<RidePassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<RidePassengerDTO> passengers) {
        this.passengers = passengers;
    }

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
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

    public ArrayList<RideLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<RideLocationDTO> locations) {
        this.locations = locations;
    }

    public Ride.Status getStatus() {
        return status;
    }

    public void setStatus(Ride.Status status) {
        this.status = status;
    }

    public Rejection getRejection() {
        return rejection;
    }

    public void setRejection(Rejection rejection) {
        this.rejection = rejection;
    }
}
