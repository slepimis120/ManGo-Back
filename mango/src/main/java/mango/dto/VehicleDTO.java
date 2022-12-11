package mango.dto;

import mango.model.Location;
import mango.model.Vehicle;
import mango.model.Vehicle.Type;

public class VehicleDTO {

    private Integer id;

    private Integer driverId;

    private Vehicle.Type vehicleType;

    private String model;

    private String licenseNumber;

    private Location currentLocation;

    private Integer passengerSeats;

    private boolean babyTransport;

    private boolean petTransport;


    public VehicleDTO (Vehicle vehicle){
        //this.driverId = vehicle.getDriverId();
        this.vehicleType = vehicle.getVehicleType();
        this.model = vehicle.getModel();
        this.licenseNumber = vehicle.getLicenseNumber();
        this.currentLocation = vehicle.getCurrentLocation();
        this.passengerSeats = vehicle.getPassengerSeats();
        this.babyTransport = vehicle.isBabyTransport();
        this.petTransport = vehicle.isPetTransport();
    }

}
