package com.mango.dto;

import com.mango.model.Driver;
import com.mango.model.Passenger;
import com.mango.model.Ride;
import com.mango.model.RideLocation;
import com.mango.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseRideDTO {
    private int id;
    private Date startTime;
    private Date endTime;
    private Integer totalCost;
    private Driver driver;
    private List<RidePassengerDTO> passengers;
    private Integer estimatedTimeInMinutes;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private List<RideLocationDTO> locations;
    private Ride.Status status;
    private RejectionResponseDTO rejection;

    public ResponseRideDTO(Driver driver, List<RideLocationDTO> locations, List<RidePassengerDTO> passengers, String vehicleType, boolean babyTransport, boolean petTransport, RejectionResponseDTO rejection){
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

    public ResponseRideDTO(Ride ride){
        this.id = ride.getId();
        this.driver = ride.getDriver();
        this.locations = new ArrayList<>();
        for(RideLocation location : ride.getLocations()){
            this.locations.add(new RideLocationDTO(location));
        }
        this.passengers = new ArrayList<>();
        for(Passenger passenger : ride.getPassengers()){
            this.passengers.add(new RidePassengerDTO(passenger));
        }
        this.vehicleType = ride.getVehicleType().toString();
        this.babyTransport = ride.isBabyTransport();
        this.petTransport = ride.isPetTransport();
        this.startTime = ride.getStartTime();
        this.endTime = ride.getEndTime();
        this.totalCost = ride.getTotalCost();
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.status = ride.getStatus();
        if(ride.getRejection()!= null){
            this.rejection = new RejectionResponseDTO(ride.getRejection().getReason(), ride.getRejection().getTimeOfRejection());
        }else{
            this.rejection = new RejectionResponseDTO();
        }
    }

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

    public List<RidePassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<RidePassengerDTO> passengers) {
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

    public List<RideLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocationDTO> locations) {
        this.locations = locations;
    }

    public Ride.Status getStatus() {
        return status;
    }

    public void setStatus(Ride.Status status) {
        this.status = status;
    }

    public RejectionResponseDTO getRejection() {
        return rejection;
    }

    public void setRejection(RejectionResponseDTO rejection) {
        this.rejection = rejection;
    }
}
