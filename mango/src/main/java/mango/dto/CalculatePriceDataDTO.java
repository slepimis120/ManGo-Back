package mango.dto;

import mango.model.Vehicle;

public class CalculatePriceDataDTO {
    Float distance;
    Vehicle.Type vehicleType;

    public CalculatePriceDataDTO(Float distance, Vehicle.Type vehicleType) {
        this.distance = distance;
        this.vehicleType = vehicleType;
    }

    public CalculatePriceDataDTO() {
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Vehicle.Type getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Vehicle.Type vehicleType) {
        this.vehicleType = vehicleType;
    }
}
