package mango.model;

public class RideLocation {
    private Location departure;

    private Location destination;


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
