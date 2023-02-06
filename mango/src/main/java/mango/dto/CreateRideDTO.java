package mango.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateRideDTO {

    @NotNull
    private List<RidePassengerDTO> passengers;

    @NotNull
    @Length(max = 50)
    private String vehicleType;

    @NotNull
    private boolean babyTransport;

    @NotNull
    private boolean petTransport;

    @NotNull
    private List<RideLocationDTO> locations;

    private Date scheduledTime;


    public CreateRideDTO(List<RideLocationDTO> locations, List<RidePassengerDTO> passengers, String vehicleType, boolean babyTransport, boolean petTransport, Date scheduledTime){
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.scheduledTime = scheduledTime;
    }

    public CreateRideDTO(){}

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

    public List<RideLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RideLocationDTO> locations) {
        this.locations = locations;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
