package mango.model;

import jakarta.persistence.*;

@Entity
public class RideLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="DEPARTURE", unique=false, nullable=true)
    private Location departure;

    @ManyToOne
    @JoinColumn(name="DESTINATION", unique=false, nullable=true)
    private Location destination;

    @ManyToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;


    public RideLocation(Location departure, Location destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public RideLocation(){}

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
}
