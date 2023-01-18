package mango.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import mango.dto.RideLocationDTO;

import java.util.List;

@Entity
public class RideLocation {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="DEPARTURE", unique=false, nullable=true)
    private Location departure;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="DESTINATION", unique=false, nullable=true)
    private Location destination;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;

    @JsonBackReference
    @ManyToMany(mappedBy = "locations")
    private List<FavoriteLocations> favoriteLocations;

    public RideLocation(Location departure, Location destination, Ride ride) {
        this.departure = departure;
        this.destination = destination;
        this.ride = ride;
    }

    public RideLocation(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public RideLocation(RideLocationDTO rideLocationDTO){
        this.destination = new Location(rideLocationDTO.getDestination());
        this.departure = new Location(rideLocationDTO.getDeparture());
    }
}
