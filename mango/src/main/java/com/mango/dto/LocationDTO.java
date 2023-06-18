package com.mango.dto;

import com.mango.model.Location;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class LocationDTO {

    private String address;

    @NotNull
    @Min(value = -90)
    @Max(value = 90)
    private Float latitude;

    @NotNull
    @Min(value = -180)
    @Max(value = 180)
    private Float longitude;

    public LocationDTO(Location location){
        this.address = location.getAddress();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public LocationDTO(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}