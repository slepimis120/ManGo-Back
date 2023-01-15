package mango.dto;

import java.util.List;

public class GetFavoriteLocationsDTO {

    private String favoriteName;

    private List<RideLocationDTO> locations;

    private List<RidePassengerDTO> passengers;

    private String vehicleType;

    private boolean babyTransport;

    private boolean petTransport;

    public GetFavoriteLocationsDTO(String favoriteName, List<RideLocationDTO> locations, List<RidePassengerDTO> passengers, String vehicleType, boolean babyTransport, boolean petTransport) {
        this.favoriteName = favoriteName;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
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
