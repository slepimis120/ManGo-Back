package mango.dto;

import mango.model.Location;
import mango.model.RideLocation;

public class RideLocationDTO {

    private LocationDTO departure;

    private LocationDTO destination;


    public RideLocationDTO(LocationDTO departure, LocationDTO destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public RideLocationDTO(){}

    public RideLocationDTO(RideLocation rideLocation){
        this.departure = new LocationDTO(rideLocation.getDeparture());
        this.destination = new LocationDTO(rideLocation.getDestination());
    }

    public LocationDTO getDeparture() {
        return departure;
    }

    public void setDeparture(LocationDTO departure) {
        this.departure = departure;
    }

    public LocationDTO getDestination() {
        return destination;
    }

    public void setDestination(LocationDTO destination) {
        this.destination = destination;
    }
}
