package mango.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import mango.dto.GetFavoriteLocationsDTO;
import mango.dto.RideLocationDTO;
import mango.dto.RidePassengerDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FavoriteLocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FAVORITENAME", nullable = false)
    private String favoriteName;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<RideLocation> locations;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    private List<Passenger> passengers;

    @Column(name = "VEHICLETYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vehicle.Type vehicleType;

    @Column(name = "BABYTRANSPORT", nullable = false)
    private boolean babyTransport;

    @Column(name = "PETTRANSPORT", nullable = false)
    private boolean petTransport;

    @Column(name = "ISDELETED", nullable = false)
    private boolean isDeleted;


    public FavoriteLocations(Integer id, String favoriteName, List<RideLocation> locations, List<Passenger> passengers, Vehicle.Type vehicleType, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.favoriteName = favoriteName;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.isDeleted = false;
    }

    public FavoriteLocations() {

    }

    public FavoriteLocations(GetFavoriteLocationsDTO getFavoriteLocationsDTO){
        this.favoriteName = getFavoriteLocationsDTO.getFavoriteName();
        this.locations = new ArrayList<>();
        for(RideLocationDTO rideLocationDTO : getFavoriteLocationsDTO.getLocations()){
            this.locations.add(new RideLocation(rideLocationDTO));
        }
        this.passengers = new ArrayList<>();
        for(RidePassengerDTO ridePassengerDTO : getFavoriteLocationsDTO.getPassengers()){
            this.passengers.add(new Passenger(ridePassengerDTO));
        }
        this.vehicleType = Vehicle.Type.valueOf(getFavoriteLocationsDTO.getVehicleType());
        this.babyTransport = getFavoriteLocationsDTO.isBabyTransport();
        this.petTransport = getFavoriteLocationsDTO.isPetTransport();
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

    public List<RideLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocation> locations) {
        this.locations = locations;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
