package com.mango.dto;

import com.mango.model.FavoriteLocations;
import com.mango.model.Passenger;
import com.mango.model.RideLocation;

import java.util.ArrayList;
import java.util.List;

public class SendFavoriteLocationsDTO {

    private Integer id;

    private String favoriteName;

    private List<RideLocationDTO> locations;

    private List<RidePassengerDTO> passengers;

    private String vehicleType;

    private boolean babyTransport;

    private boolean petTransport;

    public SendFavoriteLocationsDTO(Integer id, String favoriteName, List<RideLocationDTO> locations, List<RidePassengerDTO> passengers, String vehicleType, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.favoriteName = favoriteName;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public SendFavoriteLocationsDTO(FavoriteLocations favoriteLocations){
        this.id = favoriteLocations.getId();
        this.favoriteName = favoriteLocations.getFavoriteName();
        this.locations = new ArrayList<>();
        for(RideLocation rideLocation : favoriteLocations.getLocations()){
            this.locations.add(new RideLocationDTO(rideLocation));
        }
        this.passengers = new ArrayList<>();
        for(Passenger passenger : favoriteLocations.getPassengers()){
            this.passengers.add(new RidePassengerDTO(passenger));
        }
        this.vehicleType = favoriteLocations.getVehicleType().toString();
        this.babyTransport = favoriteLocations.isBabyTransport();
        this.petTransport = favoriteLocations.isPetTransport();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public List<RideLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocationDTO> locations) {
        this.locations = locations;
    }

    public List<RidePassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<RidePassengerDTO> passengers) {
        this.passengers = passengers;
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
}
